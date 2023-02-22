package com.example.progettoletturadati.TreeViewDir;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewHolder;
import com.example.progettoletturadati.R;

public class CustomViewHolder extends TreeViewHolder {

    private TextView fileName;
    private ImageView img;


    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        fileName = itemView.findViewById(R.id.groupTitle);
        img = itemView.findViewById(R.id.imageView);

    }

    @Override
    public void bindTreeNode(TreeNode node) {
        super.bindTreeNode(node);

        String fileNameStr = node.getValue().toString();
        fileName.setText(fileNameStr);


        if (node.getChildren().isEmpty()) {
            img.setVisibility(View.INVISIBLE);
        } else {
            img.setVisibility(View.VISIBLE);
        }

        if(node.isExpanded()){
            img.setRotation(90);
        }else{
            img.setRotation(0);
        }
    }

}