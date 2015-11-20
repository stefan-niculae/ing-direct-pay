package bytes.smart.ingdirectpay.models;

import bytes.smart.ingdirectpay.interfaces.mvc.SimpleObservable;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class TransactionModel extends SimpleObservable<TransactionModel> {
    private String account;
    private double sum;
    private String explanation;

    public void update(boolean... notify)
    {
        if(notify.length > 0 && notify[0])
        {
            notifyObservers();
        }
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account, boolean... notify) {
        this.account = account;
        update(notify);
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum, boolean... notify) {
        this.sum = sum;
        update(notify);
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation, boolean... notify) {
        this.explanation = explanation;
        update(notify);
    }
}
