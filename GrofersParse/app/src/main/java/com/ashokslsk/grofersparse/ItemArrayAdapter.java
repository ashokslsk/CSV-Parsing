package com.ashokslsk.grofersparse;

/**
 * Created by Ashu on 07/08/15.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter<String[]> {
    private List<String[]> scoreList = new ArrayList<String[]>();
    NiftyDialogBuilder dialogBuilder;
    private Context mContext;


    static class ItemViewHolder {
        TextView User_id;
        TextView Signup_ts;
        TextView cart_id;
        TextView order_place_ts;
        TextView order_scheduled_ts;
        TextView product_id;
        TextView product_name;
        TextView unit_price;
        TextView quantity;

    }

    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        this.mContext = context;
    }

    @Override
    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.scoreList.size();
    }

    @Override
    public String[] getItem(int index) {
        return this.scoreList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.User_id = (TextView) row.findViewById(R.id.uid);
            viewHolder.Signup_ts = (TextView) row.findViewById(R.id.sign);
            viewHolder.cart_id = (TextView) row.findViewById(R.id.cart);
            viewHolder.order_place_ts = (TextView) row.findViewById(R.id.order);
            viewHolder.order_scheduled_ts = (TextView) row.findViewById(R.id.shedule);
            viewHolder.product_id = (TextView) row.findViewById(R.id.product_id);
            viewHolder.product_name = (TextView) row.findViewById(R.id.name);
            viewHolder.unit_price = (TextView) row.findViewById(R.id.uprice);
            viewHolder.quantity = (TextView) row.findViewById(R.id.quanti);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)row.getTag();
        }

        final String[] stat = getItem(position);
        viewHolder.User_id.setText("User ID :"+stat[0]);
        viewHolder.Signup_ts.setText("Sign up ts :"+stat[1]);
        viewHolder.cart_id.setText("Cart ID :"+stat[2]);
        viewHolder.order_place_ts.setText("Order placed ts :"+stat[3]);
        viewHolder.order_scheduled_ts.setText("Order Scheduled ts :"+stat[4]);
        viewHolder.product_id.setText("Product id :"+stat[5]);
        viewHolder.product_name.setText("Product Name :"+stat[6]);
        viewHolder.unit_price.setText("Unit Price :"+stat[7]);
        viewHolder.quantity.setText("Quantity :"+stat[8]);
        return row;

    }
}
