package com.example.progettoletturadati;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.progettoletturadati.expandableListdir.Child;
import com.example.progettoletturadati.expandableListdir.CustomExpandableListAdapter;
import com.example.progettoletturadati.expandableListdir.Group;
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

    

                        child.setChildName(nestedKey);

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


            expandableListView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.turchese)));
    }

}