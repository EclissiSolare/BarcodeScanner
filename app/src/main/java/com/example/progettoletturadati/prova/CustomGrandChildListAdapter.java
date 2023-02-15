package com.example.progettoletturadati.prova;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progettoletturadati.R;

import java.util.List;

public class CustomGrandChildListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<GrandChild> grandChildren;

    public CustomGrandChildListAdapter(Context context, List<GrandChild> grandChildren) {
        this.context = context;
        this.grandChildren = grandChildren;
    }


    @Override
    public int getGroupCount() {
        return grandChildren.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return grandChildren.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_grandchild, null);
        }

        // Get the GrandChild object for the current position
        GrandChild grandChild = grandChildren.get(i);
        // Set the name and value for the GrandChild
        TextView grandChildNameTextView = view.findViewById(R.id.grandChildTitle);
        if (grandChild.getGrandChildValue() != null) {
            grandChildNameTextView.setText(grandChild.getGrandChildName() + ": " + grandChild.getGrandChildValue());
        } else {
            grandChildNameTextView.setText(grandChild.getGrandChildName() + ":");
        }


        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
