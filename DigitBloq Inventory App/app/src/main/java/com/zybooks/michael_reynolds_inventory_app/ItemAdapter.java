package com.zybooks.michael_reynolds_inventory_app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<ItemModel> data;
    public InventoryDatabase inventoryDatabase;
    private FragmentManager fragmentManager;

    private Context context;

    public ItemAdapter(List<ItemModel> data, InventoryDatabase inventoryDatabase, FragmentManager fragmentManager, Context context) {
        this.data = data;
        this.inventoryDatabase = inventoryDatabase;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView itemSKU;
        TextView itemCount;
        AppCompatButton removeButton;
        ImageView minusButton;
        ImageView plusButton;
        NodeModel nodeRef;

        public ViewHolder(View itemView, ItemAdapter adapter) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.card_item_image);
            itemName = itemView.findViewById(R.id.card_item_name);
            itemSKU = itemView.findViewById(R.id.card_item_sku);
            itemCount = itemView.findViewById(R.id.card_item_count);
            removeButton = itemView.findViewById(R.id.card_item_remove);
            minusButton = itemView.findViewById(R.id.card_item_minus);
            plusButton = itemView.findViewById(R.id.card_item_plus);


            removeButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int positionToRemove = getBindingAdapterPosition();

                    if (positionToRemove != RecyclerView.NO_POSITION) {
                        adapter.removeItem(positionToRemove);
                    }
                }
            });

            minusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ItemModel itemModel = data.get(getBindingAdapterPosition());

                    itemModel.minusQuantity();

                    inventoryDatabase.changeQuantity(itemModel);
                    data.set(getBindingAdapterPosition(), itemModel);
                    notifyItemChanged(getBindingAdapterPosition());

                    CheckQuantity(itemModel);
                }
            });

            plusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ItemModel itemModel = data.get(getBindingAdapterPosition());

                    itemModel.addQuantity();

                    inventoryDatabase.changeQuantity(itemModel);
                    data.set(getBindingAdapterPosition(), itemModel);
                    notifyItemChanged(getBindingAdapterPosition());
                }
            });

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(itemView, this);
    }

    public void removeItem(int position) {
        if (position >= 0 && position < data.size()) {
            inventoryDatabase.removeItem(data.get(position));

            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addItem(ItemModel itemModel) {
        inventoryDatabase.addItem(itemModel);
        data.add(itemModel);
        notifyItemInserted(data.size() - 1);
    }

    public void CheckQuantity(ItemModel itemModel){
        if(itemModel.getQuantity() < 10) {

            Toast.makeText(context, itemModel.getName().toString() + " is running low: Quantity " + itemModel.getQuantity(), Toast.LENGTH_SHORT).show();

            String phoneNumber = "(650) 555-1212";
            String message = itemModel.getName().toString() + " is running low: Quantity " + itemModel.getQuantity();

            if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.SEND_SMS)
                    == PackageManager.PERMISSION_GRANTED) {
                sendSMS(phoneNumber, message);
            }
        }
    }

    private void sendSMS(String phoneNumber, String message) {
        SmsManager smsManager = SmsManager.getDefault();

        smsManager.sendTextMessage(phoneNumber, null, message, null, null);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemModel currentItem = data.get(position);

        //TODO: Add image per node

        holder.removeButton.setText("REMOVE");
        holder.itemImage.setImageResource(R.drawable.digitbloq_items);
        holder.itemName.setText(currentItem.getName());
        holder.itemSKU.setText(currentItem.getSku());
        holder.itemCount.setText(String.valueOf(currentItem.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

