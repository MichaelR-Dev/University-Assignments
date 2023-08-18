package com.zybooks.michael_reynolds_inventory_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    private ImageView imageViewNotification;
    private AppCompatButton newNodeButton;
    private RecyclerView recyclerView;
    private List<NodeModel> nodeList = new ArrayList<>();
    private NodeAdapter nodeAdapter;
    private InventoryDatabase inventoryDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        inventoryDatabase = new InventoryDatabase(InventoryActivity.this);

        newNodeButton = findViewById(R.id.buttonAdd);
        recyclerView = findViewById(R.id.recyclerView);

        nodeAdapter = new NodeAdapter(nodeList, inventoryDatabase, getSupportFragmentManager());

        recyclerView.setAdapter(nodeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PopulateExistingNodes();

        imageViewNotification = findViewById(R.id.imageViewNotification);

        imageViewNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void PopulateExistingNodes(){
        List<NodeModel> existingNodes = inventoryDatabase.findAllNodes("nodes");

        existingNodes.forEach((nodeModel) -> {
            nodeList.add(nodeModel);
            nodeAdapter.notifyItemInserted(nodeList.size() - 1);
        });
    }

    public void CreateNode(NodeModel nodeModel){
        inventoryDatabase.newNode(nodeModel);
        nodeList.add(nodeModel);

        nodeAdapter.notifyItemInserted(nodeList.size() - 1);
    }

    public void OnCreateNode(View view){

        FragmentEditNode fragment = new FragmentEditNode(new FragmentCallback() {
            @Override
            public void onNodeCreated(NodeModel newNode) {
                inventoryDatabase.newNode(newNode);
                nodeList.add(newNode);

                nodeAdapter.notifyItemInserted(nodeList.size() - 1);
            }
        });

        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void OnCreateItem(View view){
        Toast.makeText(InventoryActivity.this, "Item Created Successfully", Toast.LENGTH_SHORT).show();
    }

}