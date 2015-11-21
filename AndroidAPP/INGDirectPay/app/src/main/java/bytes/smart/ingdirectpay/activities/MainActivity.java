package bytes.smart.ingdirectpay.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import bytes.smart.ingdirectpay.R;
import bytes.smart.ingdirectpay.managers.AccountsManager;
import bytes.smart.ingdirectpay.models.MainModel;
import bytes.smart.ingdirectpay.models.POJO.PaymentRequest;
import bytes.smart.ingdirectpay.models.POJO.User;
import bytes.smart.ingdirectpay.models.TransactionModel;
import bytes.smart.ingdirectpay.views.MainLayout;
import bytes.smart.ingdirectpay.views.TransactionLayout;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private Activity activity;

    private Firebase myFirebaseRef;
    public static final String firebaseId = "id1";

    private MainModel model;
    private MainLayout layout;
    private MainLayout.ViewListener viewListener = new MainLayout.ViewListener() {
        @Override
        public void onAddTransactionClicked() {
            Intent intent = new Intent(activity, TransactionActivity.class);
            startActivity(intent);
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

        initFirebase();

        initLayout();

        setContentView(layout);
    }

    private void initFirebase()
    {
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://directpaying.firebaseio.com/users").child(firebaseId);
        myFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                ArrayList<PaymentRequest> paymentRequests = new ArrayList<>();
                for(String key : user.getTransactions().keySet())
                {
                    user.getTransactions().get(key).setTransactionId(key);
                    paymentRequests.add(user.getTransactions().get(key));
                }

                Collections.sort(paymentRequests, new Comparator<PaymentRequest>() {
                    @Override
                    public int compare(PaymentRequest lhs, PaymentRequest rhs) {
                        if (lhs.getDate() > rhs.getDate()) {
                            return -1;
                        } else if (lhs.getDate() < rhs.getDate()) {
                            return 1;
                        }
                        return 0;
                    }
                });

                if(paymentRequests.size() - model.getPayments().size() == 1)
                {
                    model.setPayments(new ArrayList<>(paymentRequests), true);
                    layout.showAddSongDialog(0);
                }else{
                    model.setPayments(new ArrayList<>(paymentRequests), true);
                }
                AccountsManager.getAccountsManager().setAccounts(paymentRequests);
                Log.i(TAG, user.toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
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
