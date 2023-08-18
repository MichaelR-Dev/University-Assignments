package com.zybooks.michael_reynolds_inventory_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NodeAdapter extends RecyclerView.Adapter<NodeAdapter.ViewHolder> {
    private List<NodeModel> data;
    public InventoryDatabase inventoryDatabase;
    private FragmentManager fragmentManager;

    public NodeAdapter(List<NodeModel> data, InventoryDatabase inventoryDatabase, FragmentManager fragmentManager) {
        this.data = data;
        this.inventoryDatabase = inventoryDatabase;
        this.fragmentManager = fragmentManager;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView nodeImage;
        TextView nodeName;
        TextView nodeAddr;
        TextView lowCount;
        TextView emptyCount;
        AppCompatButton removeButton;

        NodeModel nodeModel;

        public ViewHolder(View nodeView, NodeAdapter adapter) {
            super(nodeView);

            nodeImage = itemView.findViewById(R.id.card_inventory_image);
            nodeName = itemView.findViewById(R.id.card_inventory_name);
            nodeAddr = itemView.findViewById(R.id.card_inventory_addr);
            lowCount = itemView.findViewById(R.id.card_inventory_low_count);
            emptyCount = itemView.findViewById(R.id.card_inventory_empty_count);
            removeButton = itemView.findViewById(R.id.card_inventory_remove);

            removeButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int positionToRemove = getBindingAdapterPosition();


                    if (positionToRemove != RecyclerView.NO_POSITION) {
                        adapter.removeItem(positionToRemove);
                    }
                }
            });

            nodeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int positionToView = getBindingAdapterPosition();

                    if (positionToView != RecyclerView.NO_POSITION) {
                        OnViewNode(view, data.get(positionToView));
                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View nodeView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventory_card, parent, false);
        return new ViewHolder(nodeView, this);
    }

    private void removeItem(int position) {
        if (position >= 0 && position < data.size()) {
            inventoryDatabase.removeNode(data.get(position));

            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NodeModel currentItem = data.get(position);

        //TODO: Add image per node

        holder.removeButton.setText("REMOVE");
        holder.nodeImage.setImageResource(R.drawable.digitbloq_naked);
        holder.nodeName.setText(currentItem.getNode_name());
        holder.nodeAddr.setText(currentItem.getNode_addr());
        holder.emptyCount.setText(String.valueOf(currentItem.getEmpty_count()));
        holder.lowCount.setText(String.valueOf(currentItem.getLow_count()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void OnViewNode(View view, NodeModel nodeModel){
        FragmentViewNode fragment = new FragmentViewNode(new FragmentItemCallbacks() {

            @Override
            public void AddItem(ItemModel itemModel) {

            }

            @Override
            public void RemoveItem(ItemModel itemModel) {

            }

            @Override
            public void UpdateItem(ItemModel itemModel, int ItemQuantity) {

            }
        }, nodeModel, inventoryDatabase);

                fragmentManager.beginTransaction()
                .add(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }
}

