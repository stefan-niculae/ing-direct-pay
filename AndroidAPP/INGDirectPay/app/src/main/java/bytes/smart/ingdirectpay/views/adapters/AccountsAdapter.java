package bytes.smart.ingdirectpay.views.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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

/**
 * Created by Alexandru on 22-May-15.
 */
public class AccountsAdapter extends BaseAdapter implements Filterable {

    private LayoutInflater layoutInflater;

    private List<String> currentItems;
    private List<String> initialItems;

    private Context context;

    public AccountsAdapter(Context context, List<String> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.currentItems = items;
        this.initialItems = items;
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

        holder.accountTextView.setText(currentItems.get(i));

        return view;
    }

    public void setCurrentItems(List<String> items) {
        this.currentItems = items;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // Now we have to inform the adapter about the new list filtered
                if (results.count == 0)
                    notifyDataSetInvalidated();
                else {
                    currentItems = (List<String>) results.values;
                    notifyDataSetChanged();
                }
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                // We implement here the filter logic
                if (constraint == null || constraint.length() == 0) {
                    // No filter implemented we return all the list
                    results.values = initialItems;
                    results.count = initialItems.size();
                }
                else {
                    // We perform filtering operation
                    List<String> nPlanetList = new ArrayList<>();

                    for (String p : initialItems) {
                        if (p.toUpperCase().contains(constraint.toString().toUpperCase()))
                            nPlanetList.add(p);
                    }

                    results.values = nPlanetList;
                    results.count = nPlanetList.size();

                }
                return results;
            }
        };
    }

    private static class ViewHolder {
        TextView accountTextView;
    }
}

