package bytes.smart.ingdirectpay.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import bytes.smart.ingdirectpay.R;
import bytes.smart.ingdirectpay.interfaces.mvc.OnChangeListener;
import bytes.smart.ingdirectpay.models.TransactionModel;
import bytes.smart.ingdirectpay.utils.ViewUtils;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class TransactionLayout extends RelativeLayout implements OnChangeListener<TransactionModel> {

    private final String TAG = "TransactionLayout";

    private TransactionModel model;
    private ViewListener viewListener;

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

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.activity_transaction_toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ViewUtils.setActionBarTitle(getContext(), "", false);
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
