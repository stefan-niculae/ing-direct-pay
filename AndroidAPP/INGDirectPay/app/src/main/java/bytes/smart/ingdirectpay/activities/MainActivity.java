package bytes.smart.ingdirectpay.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import bytes.smart.ingdirectpay.R;
import bytes.smart.ingdirectpay.models.MainModel;
import bytes.smart.ingdirectpay.models.TransactionModel;
import bytes.smart.ingdirectpay.views.MainLayout;
import bytes.smart.ingdirectpay.views.TransactionLayout;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "TransactionActivity";

    private Activity activity;

    private MainModel model;
    private MainLayout layout;
    private MainLayout.ViewListener viewListener = new MainLayout.ViewListener() {
        @Override
        public void onAddTransactionClicked() {

        }

        @Override
        public void onTransactionClicked(int position) {

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
        layout = (MainLayout) View.inflate(activity, R.layout.activity_main, null);
        layout.setModel(model);
        layout.setViewListener(viewListener);
    }

    private void initModel()
    {
        model = new MainModel();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
