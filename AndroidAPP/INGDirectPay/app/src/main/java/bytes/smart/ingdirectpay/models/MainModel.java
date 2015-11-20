package bytes.smart.ingdirectpay.models;

import java.util.ArrayList;

import bytes.smart.ingdirectpay.interfaces.mvc.SimpleObservable;
import bytes.smart.ingdirectpay.models.POJO.PaymentRequest;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class MainModel extends SimpleObservable<MainModel> {

    private ArrayList<PaymentRequest> payments;

    public void update(boolean... notify)
    {
        if(notify.length > 0 && notify[0])
        {
            notifyObservers();
        }
    }

    public ArrayList<PaymentRequest> getPayments() {
        if(payments == null)
        {
            payments = new ArrayList<>();
        }
        return payments;
    }

    public void setPayments(ArrayList<PaymentRequest> payments, boolean... notify) {
        this.payments = payments;
        update(notify);
    }
}
