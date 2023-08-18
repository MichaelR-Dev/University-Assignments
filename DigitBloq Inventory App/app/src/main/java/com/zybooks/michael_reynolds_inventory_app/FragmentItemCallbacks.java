package com.zybooks.michael_reynolds_inventory_app;

public interface FragmentItemCallbacks {

    void AddItem(ItemModel itemModel);

    void RemoveItem(ItemModel itemModel);

    void UpdateItem(ItemModel itemModel, int ItemQuantity);
}
