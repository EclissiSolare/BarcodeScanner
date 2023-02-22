package com.example.progettoletturadati.expandableListdir;

import com.amrdeveloper.treeview.TreeNode;
import com.example.progettoletturadati.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TreeNodeParser implements JsonDeserializer<TreeNodes> {

    @Override
    public TreeNodes deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<String, TreeNode> createdNodes = new HashMap<>();
        List<TreeNode> roots = new ArrayList<>();

        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
            for (Map.Entry<String, JsonElement> entry : entrySet) {
                String key = entry.getKey();
                JsonElement value = entry.getValue();
                TreeNode parentNode = createChildNode(key, value, createdNodes, 0);
                addChildNodes(parentNode, value, createdNodes, 1);
                if (parentNode.getParent() == null) {
                    roots.add(parentNode);
                }
            }
        }

        return new TreeNodes(roots);
    }

    private static TreeNode createChildNode(String key, JsonElement value, Map<String, TreeNode> createdNodes, int level) {
        String nodeValue = value.isJsonPrimitive() ? value.getAsString() : "";
        String nodeKey = key + nodeValue;
        TreeNode childNode = createdNodes.get(nodeKey);
        if (childNode == null) {
            int layoutResourceId = R.layout.list_item_three;
            if (level == 0) {
                layoutResourceId = R.layout.list_item_one;
            } else if (level == 2) {
                layoutResourceId = R.layout.list_item_two;
            }else if(level == 3 ){
                layoutResourceId=R.layout.list_item_four;
            }
            childNode = new TreeNode(key, layoutResourceId);
            childNode.setValue(key + ": " + nodeValue);
            createdNodes.put(nodeKey, childNode);
        }
        return childNode;
    }

    private static void addChildNodes(TreeNode parentNode, JsonElement element, Map<String, TreeNode> createdNodes, int level) {
        if (element.isJsonObject()) {
            JsonObject jsonObject = element.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
            for (Map.Entry<String, JsonElement> entry : entrySet) {
                String key = entry.getKey();
                JsonElement value = entry.getValue();
                boolean childAdded = false;
                for (TreeNode child : parentNode.getChildren()) {
                    if (key.equals(child.getLevel())) {
                        addChildNodes(child, value, createdNodes, level + 1);
                        childAdded = true;
                        break;
                    }
                }
                if (!childAdded) {
                    TreeNode childNode = createChildNode(key, value, createdNodes, level + 1);
                    addChildNodes(childNode, value, createdNodes, level + 2);
                    if (!(childNode.getValue() == null && childNode.getChildren().isEmpty())) {
                        parentNode.addChild(childNode);
                    }
                }
            }
        } else if (element.isJsonArray()) {
            JsonArray jsonArray = element.getAsJsonArray();
            for (JsonElement arrayElement : jsonArray) {
                TreeNode childNode = createChildNode(null, arrayElement, createdNodes, level + 1);
                addChildNodes(childNode, arrayElement, createdNodes, level + 2);
                if (!(childNode.getValue() == null && childNode.getChildren().isEmpty())) {
                    parentNode.addChild(childNode);
                }
            }
        }
    }

}


