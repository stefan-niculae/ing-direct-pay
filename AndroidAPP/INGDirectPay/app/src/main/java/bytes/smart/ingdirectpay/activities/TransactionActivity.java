package bytes.smart.ingdirectpay.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;

import bytes.smart.ingdirectpay.R;
import bytes.smart.ingdirectpay.models.TransactionModel;
import bytes.smart.ingdirectpay.views.TransactionLayout;

public class TransactionActivity extends AppCompatActivity {

    private final String TAG = "TransactionActivity";

    private Activity activity;

    private TransactionModel model;
    private TransactionLayout layout;
    private TransactionLayout.ViewListener viewListener = new TransactionLayout.ViewListener() {
        @Override
        public void onDoneButtonClicked() {
            if(layout.validateInput())
            {
                saveDataToModel();
            }
            else{
                Toast.makeText(activity, "Validating input failed!", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void saveDataToModel() {
            if(layout.validateInput())
            {
                model.setAccount(layout.getAccount());
                model.setExplanation(layout.getExplanation());
                model.setSum(Double.parseDouble(layout.getSum()));
            }
        }

        @Override
        public void sendDataToBackend() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;

        initModel();
        initLayout();

        setContentView(layout);
    }

    private void initLayout()
    {
        layout = (TransactionLayout) View.inflate(activity, R.layout.activity_transaction, null);
        layout.setModel(model);
        layout.setViewListener(viewListener);
    }

    private void initModel()
    {
        model = new TransactionModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_transaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
//            viewListener.onSettingsClicked();
            return true;
        }

        if (id == R.id.menu_new_transaction_done_action)
        {
            viewListener.onDoneButtonClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
