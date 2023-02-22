package com.example.progettoletturadati;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.amrdeveloper.treeview.TreeViewAdapter;
import com.amrdeveloper.treeview.TreeViewHolderFactory;
import com.example.progettoletturadati.TreeViewDir.CustomViewHolder;
import com.example.progettoletturadati.TreeViewDir.CustomViewHolderFour;
import com.example.progettoletturadati.TreeViewDir.CustomViewHolderThree;
import com.example.progettoletturadati.TreeViewDir.CustomViewHolderTwo;
import com.example.progettoletturadati.TreeViewDir.TreeNodeParser;
import com.example.progettoletturadati.TreeViewDir.TreeNodes;
import com.example.progettoletturadati.APIDir.Singleton;
import com.example.progettoletturadati.APIDir.Singleton2;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

        String jsonData = Singleton2.getInstance().getJSON();

        System.out.println(jsonData);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TreeNodes.class, new TreeNodeParser())
                .create();

        TreeNodes treeNodes = gson.fromJson(jsonData, TreeNodes.class);

        treeViewAdapter.updateTreeNodes(treeNodes.getTreeNodes());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int menuId = item.getItemId();
        if (menuId == R.id.expand_all_action) {
            treeViewAdapter.expandAll();
        }
        else if (menuId == R.id.collapse_all_action) {
            treeViewAdapter.collapseAll();
        }
        return super.onOptionsItemSelected(item);
    }

}