package bytes.smart.ingdirectpay.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import bytes.smart.ingdirectpay.R;
import bytes.smart.ingdirectpay.activities.MainActivity;
import bytes.smart.ingdirectpay.interfaces.mvc.OnChangeListener;
import bytes.smart.ingdirectpay.models.MainModel;
import bytes.smart.ingdirectpay.models.POJO.PaymentRequest;
import bytes.smart.ingdirectpay.models.POJO.QRCode;
import bytes.smart.ingdirectpay.models.TransactionModel;
import bytes.smart.ingdirectpay.utils.QRCodeUtils;
import bytes.smart.ingdirectpay.utils.StringUtils;
import bytes.smart.ingdirectpay.utils.ViewUtils;
import bytes.smart.ingdirectpay.views.adapters.PaymentsAdapter;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class MainLayout extends RelativeLayout implements OnChangeListener<MainModel> {

    private final String TAG = "MainLayout";

    private MainModel model;
    private ViewListener viewListener;

    private ListView paymentsListView;
    private PaymentsAdapter paymentsAdapter;

    private FloatingActionButton addTransactionButton;

    private Toolbar toolbar;

    public interface ViewListener {
        void onAddTransactionClicked();
        void onTransactionClicked(int position);
    }

    public MainLayout(Context context) {
        super(context);
    }

    public MainLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
        addTransactionButton = (FloatingActionButton) findViewById(R.id.activity_main_add_transaction_button);
        addTransactionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewListener().onAddTransactionClicked();
            }
        });

        paymentsListView = (ListView) findViewById(R.id.activity_main_payments_listview);
        paymentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAddSongDialog(position);
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ViewUtils.setActionBarTitle(getContext(), getContext().getResources().getString(R.string.app_name), false);
    }

    private void updateView() {

        if(paymentsAdapter == null)
        {
            paymentsAdapter = new PaymentsAdapter(getContext(), getModel().getPayments());
            paymentsListView.setAdapter(paymentsAdapter);
        }
        else
        {
            paymentsAdapter.setCurrentItems(getModel().getPayments());
            paymentsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onChange() {
        updateView();
    }

    public void showAddSongDialog(int position)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        RelativeLayout dialogLayout = (RelativeLayout) View.inflate(getContext(), R.layout.dialog_qrcode, null);

        final ImageView qrCodeImageView = (ImageView) dialogLayout.findViewById(R.id.dialog_qrcode_imageview);

        alertDialog.setView(dialogLayout);
        alertDialog.setTitle("Transaction QR code");
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        QRCode qrCode = new QRCode();
        qrCode.setTransactionId(getModel().getPayments().get(position).getTransactionId());
        qrCode.setUserId(MainActivity.firebaseId);

        qrCodeImageView.setImageBitmap(QRCodeUtils.generateQRCode(new Gson().toJson(qrCode)));
//                .setNegativeButton("Cancel", null);
        try {
            alertDialog.create().show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public MainModel getModel() {
        return model;
    }

    public void setModel(MainModel model) {
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
