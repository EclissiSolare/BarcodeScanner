package com.example.progettoletturadati.TreeViewDir;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amrdeveloper.treeview.TreeNode;
import com.amrdeveloper.treeview.TreeViewHolder;
import com.example.progettoletturadati.R;

public class CustomViewHolderFour extends TreeViewHolder {

    private TextView fileName;



    public CustomViewHolderFour(@NonNull View itemView) {
        super(itemView);
        initViews();
    }

    private void initViews() {
        fileName = itemView.findViewById(R.id.grandGrandChild);

    }

    @Override
    public void bindTreeNode(TreeNode node) {
        super.bindTreeNode(node);

        String fileNameStr = node.getValue().toString();
        fileName.setText(fileNameStr);

    }


}