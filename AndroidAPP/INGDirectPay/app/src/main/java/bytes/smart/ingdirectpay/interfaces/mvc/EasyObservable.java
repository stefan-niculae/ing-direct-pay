package bytes.smart.ingdirectpay.interfaces.mvc;

/**
 * The Interface EasyObservable.
 *
 * @param <T> the generic type
 * @author alexandru.buicescu
 */
public interface EasyObservable<T> {

    /**
     * Adds the listener.
     *
     * @param listener the listener
     */
    void addListener(OnChangeListener<T> listener);

    /**
     * Removes the listener.
     *
     * @param listener the listener
     */
    void removeListener(OnChangeListener<T> listener);

}
