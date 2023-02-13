package com.example.progettoletturadati;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Group> groups;

    TextView childNameTextView;
    TextView childValueTextView;

    public CustomExpandableListAdapter(Context context, List<Group> groups) {
        this.context = context;
        this.groups = groups;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).getChildren().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).getChildren().get(childPosition);
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
        return false;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_group, parent, false);
        }
        Group group = (Group) getGroup(groupPosition);
        TextView groupNameTextView = convertView.findViewById(R.id.groupTitle);
        TextView groupValueTextView=convertView.findViewById(R.id.groupValue);
        groupNameTextView.setText(group.getGroupName());
        groupValueTextView.setText((CharSequence) group.getGroupValue());

        return convertView;
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item2, parent, false);
        }

        Child child = (Child) getChild(groupPosition, childPosition);
        childNameTextView = convertView.findViewById(R.id.childTitle);
        childValueTextView = convertView.findViewById(R.id.childValue);
        //Toast.makeText(context,child.getChildName()+"--->"+child.getChildValue(),Toast.LENGTH_LONG).show();
        childNameTextView.setText(child.getChildName());
        childValueTextView.setText(child.getChildValue());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }




}
