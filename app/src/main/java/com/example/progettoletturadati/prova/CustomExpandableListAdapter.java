package com.example.progettoletturadati.prova;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.progettoletturadati.R;

import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Group> groups;

    TextView childNameTextView;


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

        if (group.getGroupValue() != null) {
            groupNameTextView.setText(group.getGroupName() + ": " + group.getGroupValue());
        } else {
            groupNameTextView.setText(group.getGroupName()+":");
        }

        ImageView img= (ImageView) convertView.findViewById(R.id.imageView);

        if(isExpanded==true){
            img.setRotation(90);
        }else if (isExpanded==false){
            img.setRotation(0);
        }

        if (group.getGroupValue() == null) {
            img.setVisibility(View.VISIBLE);
        } else {
            img.setVisibility(View.GONE);

        }

        return convertView;
    }



    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_child, parent, false);
        }

        Child child = (Child) getChild(groupPosition, childPosition);
        childNameTextView = convertView.findViewById(R.id.childTitle);
        childNameTextView.setText(child.getChildName()+":  "+child.getChildValue());

        // Gestione grandChild
        ConstraintLayout grandChildLayout = convertView.findViewById(R.id.layoutGrandChild);


        List<GrandChild> grandChildren = child.getGrandChildren();
        if (grandChildren != null) {
            for (GrandChild grandChild : grandChildren) {
                View grandChildView = LayoutInflater.from(context).inflate(R.layout.list_grandchild, null);
                TextView grandChildNameTextView = grandChildView.findViewById(R.id.grandChildTitle);


                grandChildNameTextView.setText(grandChild.getGrandChildName() + ": "+grandChild.getGrandChildValue());

                grandChildLayout.addView(grandChildView);
            }
        }

        return convertView;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }





}
