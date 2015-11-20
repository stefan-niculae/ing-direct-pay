package bytes.smart.ingdirectpay.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bytes.smart.ingdirectpay.R;
import bytes.smart.ingdirectpay.models.POJO.PaymentRequest;

/**
 * Created by Alexandru on 22-May-15.
 */
public class PaymentsAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    private List<PaymentRequest> currentItems;

    private Context context;

    public PaymentsAdapter(Context context, List<PaymentRequest> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.currentItems = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return currentItems.size();
    }

    @Override
    public Object getItem(int i) {
        return currentItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.row_account, viewGroup, false);

            holder = new ViewHolder();
            holder.accountTextView = (TextView) view.findViewById(R.id.row_account_textview);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.accountTextView.setText(currentItems.get(i).getAccount() + "");

        return view;
    }

    public void setCurrentItems(List<PaymentRequest> items) {
        this.currentItems = items;
    }

    private static class ViewHolder {
        TextView accountTextView;
        TextView dateTextView;
        TextView sumTextView;
        TextView explanationTextView;
    }
}

