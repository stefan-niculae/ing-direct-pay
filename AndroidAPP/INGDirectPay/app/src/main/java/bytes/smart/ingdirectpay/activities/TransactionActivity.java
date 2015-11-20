package bytes.smart.ingdirectpay.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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

        }

        @Override
        public void saveDataToModel() {

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
}
