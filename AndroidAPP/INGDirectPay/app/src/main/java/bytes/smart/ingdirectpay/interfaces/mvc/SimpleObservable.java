package bytes.smart.ingdirectpay.interfaces.mvc;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc

/**
 * The Class SimpleObservable.
 *
 * @param <T> the generic type
 * @author alexandru.buicescu
 */
public class SimpleObservable<T> implements EasyObservable<T> {

    private static int UPDATE_MESSAGE = 1234;
    private final Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            notifyObservers();
        }
    };
    /**
     * The listeners.
     */
    private final ArrayList<OnChangeListener<T>> listeners = new ArrayList<OnChangeListener<T>>();
    private long updateThresholdTime;
    private long lastUpdated;

    public void addListener(OnChangeListener<T> listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    public void removeListener(OnChangeListener<T> listener) {
        synchronized (listeners) {
            listeners.remove(listener);
        }
    }

    /**
     * Notify observers. If no param is provided, the notification is mandatory. If the current notification is of low importance the
     * boolean field should be transmitted as false,
     *
     * @param model                   the model
     * @param isNotificationMandatory the is notification mandatory
     */
    protected void notifyObservers(final boolean... isNotificationMandatory) {
        long currentTime = System.currentTimeMillis();
        final long timeSinceLastUpdate = currentTime - this.lastUpdated;
        if (this.lastUpdated == 0 || this.updateThresholdTime == 0
                || (timeSinceLastUpdate >= this.updateThresholdTime)
                || (isNotificationMandatory.length > 0 && isNotificationMandatory[0])) {
            this.lastUpdated = currentTime;
            ArrayList<OnChangeListener<T>> list = (ArrayList<OnChangeListener<T>>) listeners.clone();
            synchronized (listeners) {
                for (OnChangeListener<T> listener : list) {
                    listener.onChange();
                }
            }
        } else if (!updateHandler.hasMessages(UPDATE_MESSAGE)) {
            updateHandler.sendEmptyMessageDelayed(UPDATE_MESSAGE, updateThresholdTime - timeSinceLastUpdate + 1);
        }
    }

    protected long getUpdateThresholdTime() {
        return updateThresholdTime;
    }

    protected void setUpdateThresholdTime(long updateThresholdTime) {
        this.updateThresholdTime = updateThresholdTime;
    }

}
