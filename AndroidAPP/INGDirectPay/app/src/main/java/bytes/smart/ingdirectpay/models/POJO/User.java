package bytes.smart.ingdirectpay.models.POJO;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class User {
    private String email;
    private String name;
    private Map<String, PaymentRequest> transactions;
    private String userType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, PaymentRequest> getTransactions() {
        return transactions;
    }

    public void setTransactions(Map<String, PaymentRequest> transactions) {
        this.transactions = transactions;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString()
    {
        String response = email + ' ' + name;

        for(PaymentRequest paymentRequest : transactions.values()) {
            response += ' ' + paymentRequest.toString() + '\n';
        }

        return response;
    }

}
