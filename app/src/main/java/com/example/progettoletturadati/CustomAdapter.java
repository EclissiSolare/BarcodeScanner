package com.example.progettoletturadati;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<DataModel> {

    private final LayoutInflater mInflater;
    private final List<DataModel> mData;

    public CustomAdapter(Context context, List<DataModel> data) {
        super(context, R.layout.list_item, data);
        mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.text1 = (TextView) convertView.findViewById(R.id.textView1);
            holder.text2 = (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DataModel item = mData.get(position);
        holder.text1.setText(item.getData1());
        holder.text2.setText(item.getData2());

        return convertView;
    }

    static class ViewHolder {
        TextView text1;
        TextView text2;
    }
}
