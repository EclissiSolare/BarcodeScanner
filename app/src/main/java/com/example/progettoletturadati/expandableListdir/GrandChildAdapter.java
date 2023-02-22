package com.example.progettoletturadati.expandableListdir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.progettoletturadati.ActivityJSONLIST;
import com.example.progettoletturadati.R;
import com.example.progettoletturadati.expandableListdir.Child;
import com.example.progettoletturadati.expandableListdir.GrandChild;

import java.util.List;

public class GrandChildAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Child> childList;

    public GrandChildAdapter(Context context, List<Child> childList) {
        this.context = context;
        this.childList = childList;
    }

    public GrandChildAdapter(ActivityJSONLIST context, List<GrandChild> grandChildren) {
    }


    @Override
    public int getGroupCount() {
        return childList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<GrandChild> grandChildren = childList.get(groupPosition).getGrandChildren();
        return grandChildren != null ? grandChildren.size() : 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return childList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).getGrandChildren().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_child, parent, false);
        }

        TextView groupTextView = convertView.findViewById(R.id.childTitle);
        groupTextView.setText(childList.get(groupPosition).getChildName());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_grandchild, parent, false);
        }

        TextView grandChildTextView = convertView.findViewById(R.id.grandChildTitle);
        grandChildTextView.setText(childList.get(groupPosition).getGrandChildren().get(childPosition).getGrandChildName());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
