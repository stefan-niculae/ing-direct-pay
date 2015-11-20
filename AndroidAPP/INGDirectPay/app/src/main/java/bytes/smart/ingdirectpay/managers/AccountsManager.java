package bytes.smart.ingdirectpay.managers;

import java.util.ArrayList;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class AccountsManager {

    private static AccountsManager instance;

    private ArrayList<String> accounts;

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

    public ArrayList<String> getAccounts()
    {
        return accounts;
    }

    public void setAccounts(ArrayList<String> accounts)
    {
        this.accounts = accounts;
    }

}
