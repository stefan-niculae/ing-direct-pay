package bytes.smart.ingdirectpay.views;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;

import bytes.smart.ingdirectpay.R;
import bytes.smart.ingdirectpay.interfaces.mvc.OnChangeListener;
import bytes.smart.ingdirectpay.managers.AccountsManager;
import bytes.smart.ingdirectpay.models.TransactionModel;
import bytes.smart.ingdirectpay.utils.IBANUtils;
import bytes.smart.ingdirectpay.utils.StringUtils;
import bytes.smart.ingdirectpay.utils.ViewUtils;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class TransactionLayout extends RelativeLayout implements OnChangeListener<TransactionModel> {

    private final String TAG = "TransactionLayout";

    private TransactionModel model;
    private ViewListener viewListener;

    private AutoCompleteTextView accountAutoCompleteTextView;
    private EditText sumEditText;
    private EditText explanationEditText;
    private TextInputLayout accountAutoCompleteTextInputLayout;
    private TextInputLayout sumTextInputLayout;
    private TextInputLayout explanationTextInputLayout;

    private AccountsAdapter accountsAdapter;

    private Toolbar toolbar;

    public interface ViewListener {
        void onDoneButtonClicked();
        void saveDataToModel();
        void sendDataToBackend();
    }

    public TransactionLayout(Context context) {
        super(context);
    }

    public TransactionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TransactionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initLayout();
        initToolbar();
    }

    private void initLayout()
    {
        accountAutoCompleteTextInputLayout = (TextInputLayout) findViewById(R.id.activity_transaction_account_textinputlayout);
        accountAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.activity_transaction_account_autocompletetextview);
        accountAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getViewListener().saveDataToModel();
                updateView();
            }
        });

        accountsAdapter = new AccountsAdapter(getContext(), AccountsManager.getAccountsManager().getAccounts());
        accountAutoCompleteTextView.setAdapter(accountsAdapter);
        accountAutoCompleteTextView.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateAccount();
                }
            }
        });

        sumTextInputLayout = (TextInputLayout) findViewById(R.id.activity_transaction_sum_textinputlayout);
        sumEditText = (EditText) findViewById(R.id.activity_transaction_sum_edittext);
        sumEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validateSum();
                }
            }
        });

        explanationTextInputLayout = (TextInputLayout) findViewById(R.id.activity_transaction_explanation_textinputlayout);
        explanationEditText = (EditText) findViewById(R.id.activity_transaction_explanation_edittext);
        explanationEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validateExplanation();
                }
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.activity_transaction_toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ViewUtils.setActionBarTitle(getContext(), "New Transaction", true);
    }

    public String getAccount()
    {
        return StringUtils.trimStartEnd(accountAutoCompleteTextView.getText().toString());
    }

    public String getSum()
    {
        return StringUtils.trimStartEnd(sumEditText.getText().toString());
    }

    public String getExplanation()
    {
        return StringUtils.trimStartEnd(explanationEditText.getText().toString());
    }

    public boolean validateInput() {
        boolean ok = true;

        if(!validateAccount() || !validateSum() || !validateExplanation())
        {
            ok = false;
        }

        return ok;
    }

    private boolean validateAccount()
    {
        accountAutoCompleteTextInputLayout.setError(null);
        boolean ok = true;

        if (getAccount().length() == 0) {
            ok = false;
            accountAutoCompleteTextInputLayout.setError("Can't be empty!");
        }
        else {
            if(!IBANUtils.validateIBAN(getAccount())){
                ok = false;
                accountAutoCompleteTextInputLayout.setError("IBAN is invalid!");
            }
        }

        return ok;
    }

    private boolean validateSum()
    {
        sumTextInputLayout.setError(null);
        boolean ok = true;

        if (getSum().length() == 0) {
            ok = false;
            sumTextInputLayout.setError("Can't be empty!");
        } else {
            try {
                double sum = Double.parseDouble(getSum());
                if (sum <= 0) {
                    ok = false;
                    sumTextInputLayout.setError("Sum can't be empty!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                ok = false;
                sumTextInputLayout.setError("Number not valid!");
            }
        }

        return ok;
    }

    private boolean validateExplanation()
    {
        boolean ok = true;
        explanationTextInputLayout.setError(null);

        if (getExplanation().length() == 0) {
            ok = false;
            explanationTextInputLayout.setError("Can't be empty!");
        }

        return ok;
    }

    private void updateView() {

    }

    @Override
    public void onChange() {
        updateView();
    }

    public TransactionModel getModel() {
        return model;
    }

    public void setModel(TransactionModel model) {
        this.model = model;
        this.model.addListener(this);
        updateView();
    }

    public ViewListener getViewListener() {
        return viewListener;
    }

    public void setViewListener(ViewListener viewListener) {
        this.viewListener = viewListener;
    }

}
