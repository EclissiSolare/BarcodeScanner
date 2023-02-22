package com.example.progettoletturadati.expandableListdir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.progettoletturadati.R;

import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Group> groups;
    ExpandableListView expandableListView;
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

    public Object getGrandChild(int groupPosition, int childPosition, int grandChildPosition) {
        return groups.get(groupPosition).getChildren().get(childPosition).getGrandChildren().get(grandChildPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    public long getGrandChildId(int groupPosition, int childPosition, int grandChildPosition) {
        return grandChildPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_group, parent, false);
        }
        Group group = (Group) getGroup(groupPosition);
        TextView groupNameTextView = convertView.findViewById(R.id.groupTitle);

        if (group.getGroupValue() != null) {
            groupNameTextView.setText(group.getGroupName() + ": " + group.getGroupValue());
        } else {
            groupNameTextView.setText(group.getGroupName() + ":");
        }

        ImageView img = convertView.findViewById(R.id.imageView);

        if (isExpanded) {
            img.setRotation(90);
        } else {
            img.setRotation(0);
        }

        if (group.getGroupValue() == null) {
            img.setVisibility(View.VISIBLE);
        } else {
            img.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_child, parent, false);
        }

        Child child = (Child) getChild(groupPosition, childPosition);
        TextView childNameTextView = convertView.findViewById(R.id.childTitle);


        if (child.getChildValue() != null) {
            childNameTextView.setText(child.getChildName() + ": " + child.getChildValue());

        } else {
            childNameTextView.setText(child.getChildName() + ":");
        }

        ImageView img = convertView.findViewById(R.id.imageViewgrandchild);

        if (isLastChild) {
            img.setRotation(90);
        } else {
            img.setRotation(0);
        }

        if (child.getChildValue() == null) {
            img.setVisibility(View.VISIBLE);
        } else {
            img.setVisibility(View.GONE);
        }

        return convertView;

    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}