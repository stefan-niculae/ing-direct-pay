package bytes.smart.ingdirectpay.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            view = layoutInflater.inflate(R.layout.row_payment, viewGroup, false);

            holder = new ViewHolder();
            holder.accountTextView = (TextView) view.findViewById(R.id.row_payment_account_textview);
            holder.dateTextView = (TextView) view.findViewById(R.id.row_payment_date_textview);
            holder.sumTextView = (TextView) view.findViewById(R.id.row_payment_sum_textview);
            holder.explanationTextView = (TextView) view.findViewById(R.id.row_payment_explanation_textview);
            holder.statusTextView = (TextView) view.findViewById(R.id.row_payment_status_textview);
            holder.nameInitialsTextView = (TextView) view.findViewById(R.id.row_payment_avatar_textview);
            holder.avatarImageView = (ImageView) view.findViewById(R.id.row_payment_avatar_imageview);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.accountTextView.setText(currentItems.get(i).getAccount());
        holder.dateTextView.setText(new SimpleDateFormat("HH:mm dd/MM/yyyy").format(new Date(currentItems.get(i).getDate())));
        holder.sumTextView.setText(currentItems.get(i).getSum() + "");
        holder.explanationTextView.setText(currentItems.get(i).getExplanation());
        holder.statusTextView.setText(currentItems.get(i).getStatus());
        if(currentItems.get(i).getNamePayer() != null && currentItems.get(i).getNamePayer().length() > 0)
        {
            holder.nameInitialsTextView.setText(currentItems.get(i).getNamePayer().charAt(0) + "");
        }
        else
        {
            holder.nameInitialsTextView.setText("A");
        }
        if(currentItems.get(i).getStatus().equalsIgnoreCase("PENDING"))
        {
            holder.avatarImageView.setColorFilter(context.getResources().getColor(R.color.custom_color_4));
        }
        else
        if(currentItems.get(i).getStatus().equalsIgnoreCase("ACCEPTED"))
        {
            holder.avatarImageView.setColorFilter(context.getResources().getColor(R.color.custom_color_10));
        }
        else
        if(currentItems.get(i).getStatus().equalsIgnoreCase("REJECTED"))
        {
            holder.avatarImageView.setColorFilter(context.getResources().getColor(R.color.custom_color_2));
        }

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
        TextView statusTextView;
        TextView nameInitialsTextView;
        ImageView avatarImageView;
    }
}

