package bytes.smart.ingdirectpay.views;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import bytes.smart.ingdirectpay.R;
import bytes.smart.ingdirectpay.interfaces.mvc.OnChangeListener;
import bytes.smart.ingdirectpay.models.MainModel;
import bytes.smart.ingdirectpay.models.TransactionModel;
import bytes.smart.ingdirectpay.utils.ViewUtils;

/**
 * Created by alexbuicescu on 20.11.2015.
 */
public class MainLayout extends RelativeLayout implements OnChangeListener<MainModel> {

    private final String TAG = "MainLayout";

    private MainModel model;
    private ViewListener viewListener;

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

    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        ((AppCompatActivity)getContext()).setSupportActionBar(toolbar);
        ViewUtils.setActionBarTitle(getContext(), getContext().getResources().getString(R.string.app_name), false);
    }

    private void updateView() {

    }

    @Override
    public void onChange() {
        updateView();
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
