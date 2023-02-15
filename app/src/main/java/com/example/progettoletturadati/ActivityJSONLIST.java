package com.example.progettoletturadati;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.progettoletturadati.prova.Child;
import com.example.progettoletturadati.prova.CustomExpandableListAdapter;
import com.example.progettoletturadati.prova.CustomGrandChildListAdapter;
import com.example.progettoletturadati.prova.GrandChild;
import com.example.progettoletturadati.prova.GrandChildAdapter;
import com.example.progettoletturadati.prova.Group;
import com.example.progettoletturadati.prova.Singleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActivityJSONLIST extends AppCompatActivity {

    private String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonlist);

        try {
            jsonString= Singleton.getInstance().getJSON();

            // Parse the JSON object
            JSONObject json = new JSONObject(jsonString);

            // Get all the keys in the JSON object
            Iterator<String> keys = json.keys();

            // Create a list of Group objects
            List<Group> groupList = new ArrayList<>();

            // Loop through all the keys
            while (keys.hasNext()) {
                // Get the key
                String key = keys.next();

                // Get the value for the key
                Object value = json.get(key);

                // Create a Group object for the key/value pair
                Group group = new Group();

                // Check if the value is another JSON object
                if (value instanceof JSONObject) {
                    // Create a list for the Child objects
                    List<Child> childData = new ArrayList<>();

                    // Get all the keys in the nested JSON object
                    Iterator<String> nestedKeys = ((JSONObject) value).keys();

                    // Loop through all the keys in the nested JSON object
                    while (nestedKeys.hasNext()) {
                        // Get the key
                        String nestedKey = nestedKeys.next();

                        // Get the value for the key
                        Object nestedValue = ((JSONObject) value).get(nestedKey);

                        // Create a Child object for the nested key/value pair
                        Child child = new Child();
                        List<GrandChild> grandChildData = new ArrayList<>();

                        if (nestedValue instanceof JSONObject) {
                            // Get all the keys in the nested nested JSON object
                            Iterator<String> nestedNestedKeys = ((JSONObject) nestedValue).keys();

                            // Loop through all the keys in the nested nested JSON object
                            while (nestedNestedKeys.hasNext()) {
                                // Get the key
                                String nestedNestedKey = nestedNestedKeys.next();

                                // Get the value for the key
                                Object nestedNestedValue = ((JSONObject) nestedValue).get(nestedNestedKey);

                                // Create a GrandChild object for the nested nested key/value pair
                                GrandChild grandChild = new GrandChild();
                                grandChild.setGrandChildName(nestedNestedKey);
                                grandChild.setGrandChildValue(nestedNestedValue.toString());
                                grandChildData.add(grandChild);
                            }
                            // Set the child's name and grandChildren
                            child.setChildName(nestedKey);
                            child.setGrandChildren(grandChildData);
                        } else {
                            child.setChildName(nestedKey);
                            child.setChildValue(nestedValue.toString());
                        }
                        // Add the Child object to the child data list
                        childData.add(child);
                    }
                    // Set the group's name and children
                    group.setGroupName(key);
                    group.setChildren(childData);
                } else {
                    // Set the group's name and value
                    group.setGroupName(key);
                    group.setGroupValue(value.toString());
                }
                // Add the Group object to the group list
                groupList.add(group);
            }
            ExpandableListView expandableListView = findViewById(R.id.listView);

            expandableListView.setGroupIndicator(null);



            // Create an adapter for the ExpandableListView
            CustomExpandableListAdapter adapter = new CustomExpandableListAdapter(this, groupList);

            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    // Get the clicked Child object
                    Child child = groupList.get(groupPosition).getChildren().get(childPosition);

                    // Check if the Child object has GrandChildren
                    if (child.getGrandChildren() != null && !child.getGrandChildren().isEmpty()) {
                        // Create a new adapter for the GrandChildren
                        // Create an adapter for the GrandChildren
                        CustomGrandChildListAdapter grandChildAdapter = new CustomGrandChildListAdapter(ActivityJSONLIST.this, child.getGrandChildren());

                        // Create a new ExpandableListView for the GrandChildren
                        ExpandableListView grandChildListView = new ExpandableListView(ActivityJSONLIST.this);

                        // Set the adapter for the GrandChildren ExpandableListView
                        grandChildListView.setAdapter(grandChildAdapter);

                        // Set the height of the GrandChildren ExpandableListView to WRAP_CONTENT
                        grandChildListView.setIndicatorBoundsRelative(parent.getWidth() - 150, parent.getWidth() - 100);
                        grandChildListView.setChildIndicator(null);
                        grandChildListView.setGroupIndicator(null);
                        grandChildListView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                        // Create an AlertDialog and set the GrandChildren ExpandableListView as the view
                        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityJSONLIST.this);
                        builder.setView(grandChildListView);

                        // Show the AlertDialog
                        AlertDialog dialog = builder.create();
                        builder.setPositiveButton("Close", null);
                        builder.show();

                    }

                    return false;
                }
            });

            expandableListView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.turchese)));
    }

}