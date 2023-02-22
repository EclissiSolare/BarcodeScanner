package com.example.progettoletturadati;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.amrdeveloper.treeview.TreeViewAdapter;
import com.amrdeveloper.treeview.TreeViewHolderFactory;
import com.example.progettoletturadati.expandableListdir.CustomViewHolder;
import com.example.progettoletturadati.expandableListdir.CustomViewHolderFour;
import com.example.progettoletturadati.expandableListdir.CustomViewHolderThree;
import com.example.progettoletturadati.expandableListdir.CustomViewHolderTwo;
import com.example.progettoletturadati.expandableListdir.TreeNodeParser;
import com.example.progettoletturadati.expandableListdir.TreeNodes;
import com.example.progettoletturadati.prova.Singleton;
import com.example.progettoletturadati.prova.Singleton2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActivityJSONLIST extends AppCompatActivity {

    private TreeViewAdapter treeViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonlist);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        TreeViewHolderFactory factory = (v, layout) -> {
            if (layout == R.layout.list_item_one) return new CustomViewHolder(v);
            else if (layout == R.layout.list_item_two) return new CustomViewHolderTwo(v);
            else if(layout == R.layout.list_item_three) return new CustomViewHolderThree(v);
            else if(layout==R.layout.list_item_four)return new CustomViewHolderFour(v);
            return null;
        };

        treeViewAdapter = new TreeViewAdapter(factory);
        recyclerView.setAdapter(treeViewAdapter);

        final String jsonData = Singleton2.getInstance().getJSON();

        System.out.println(jsonData);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TreeNodes.class, new TreeNodeParser())
                .create();

        TreeNodes treeNodes = gson.fromJson(jsonData, TreeNodes.class);

        treeViewAdapter.updateTreeNodes(treeNodes.getTreeNodes());
    }

}