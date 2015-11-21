package bytes.smart.ingdirectpay.managers;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import bytes.smart.ingdirectpay.models.POJO.PaymentRequest;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class AccountsManager {

    private static AccountsManager instance;

    private ArrayList<PaymentRequest> accounts;

    private AccountsManager() {
        accounts = new ArrayList<>();
    }

    public static AccountsManager getAccountsManager()
    {
        if(instance == null)
        {
            instance = new AccountsManager();
        }

        return instance;
    }

    public ArrayList<PaymentRequest> getAccounts()
    {
        return accounts;
    }

    public void setAccounts(ArrayList<PaymentRequest> accounts)
    {
        Set<PaymentRequest> setPayments = new HashSet<>(accounts);
//        this.accounts = new ArrayList<>(setPayments);
        Log.i("info", accounts.size() + " " + this.accounts.size());
        ArrayList<PaymentRequest> uniques = new ArrayList<>();
        for (PaymentRequest element : accounts) {
            if (!uniques.contains(element)) {
                uniques.add(element);
            }
        }
        this.accounts = uniques;
//        Iterator<PaymentRequest> iterator = accounts.iterator();
//        while(iterator.hasNext())
//        {
//            PaymentRequest paymentRequest = iterator.next();
//
//            for(PaymentRequest paymentRequest1 : accounts)
//            {
//                if(paymentRequest.getAccount().equals(paymentRequest1.getAccount()))
//                {
//                    iterator.remove();
//                    break;
//                }
//            }
//        }
//        this.accounts = accounts;
    }

}
