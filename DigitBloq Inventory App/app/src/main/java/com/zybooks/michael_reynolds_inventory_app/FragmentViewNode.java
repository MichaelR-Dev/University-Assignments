package com.zybooks.michael_reynolds_inventory_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewNode extends Fragment {

    private TextView nodeName;
    private TextView itemName;
    private TextView itemSKU;
    private AppCompatButton addButton;
    private AppCompatButton backButton;
    public NodeModel nodeRef;

    private List<ItemModel> itemList;

    private RecyclerView recyclerView;

    private ItemAdapter itemAdapter;

    public FragmentItemCallbacks callbacks;

    public InventoryDatabase inventoryDatabase;


    public FragmentViewNode(FragmentItemCallbacks callbacks, NodeModel nodeRef, InventoryDatabase inventoryDatabase) {
        this.callbacks = callbacks;
        this.nodeRef = nodeRef;
        this.inventoryDatabase = inventoryDatabase;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewnode, container, false);

        nodeName = view.findViewById(R.id.node_name);
        addButton = view.findViewById(R.id.buttonAddItem);
        backButton = view.findViewById(R.id.backButton);
        itemName = view.findViewById(R.id.new_item_name_input);
        itemSKU = view.findViewById(R.id.new_item_sku_input);

        nodeName.setText(nodeRef.getNode_name());

        recyclerView = view.findViewById(R.id.itemsRecyclerView);

        itemList = new ArrayList<>();

        itemAdapter = new ItemAdapter(itemList, inventoryDatabase, getParentFragmentManager(), this.getContext());

        PopulateExistingItems();

        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnBack(view);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemAdapter.addItem(new ItemModel(itemSKU.getText().toString(), itemName.getText().toString(), nodeRef.getNode_UUID(), 0));
            }
        });

        return view;
    }

    public void PopulateExistingItems() {
        List<ItemModel> existingItems = inventoryDatabase.findAllItems(nodeRef);

        existingItems.forEach((itemModel) -> {
            itemList.add(itemModel);
            itemAdapter.notifyItemInserted(itemList.size() - 1);
        });
    }

    public void OnBack(View view){
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        this.getView().clearFocus();

        transaction.remove(this);
        transaction.commit();
    }
}
