package bytes.smart.ingdirectpay.models.POJO;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class PaymentRequest {

    private String account;
    private double sum;
    private String explanation;
//    private String code;
    private long date;
    /**
     * possible values: pending, accepted, rejected
     */
    private String status;
    private String transactionId;
    private String namePayer;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString()
    {
        String response = getAccount();

        return response;
    }

    @Override
    public boolean equals(Object object)
    {
        if(object != null)
        {
            if(object instanceof PaymentRequest)
            {
                if(getAccount().equals(((PaymentRequest) object).getAccount()))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public String getNamePayer() {
        return namePayer;
    }

    public void setNamePayer(String namePayer) {
        this.namePayer = namePayer;
    }
}
