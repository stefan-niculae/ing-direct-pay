package bytes.smart.ingdirectpay.models.POJO;

/**
 * Created by alexbuicescu on 21.11.2015.
 */
public class QRCode {

    private String userId;
    private String transactionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
