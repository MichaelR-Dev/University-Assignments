package com.zybooks.michael_reynolds_inventory_app;

public class ItemModel{

    //Members

    public String sku;
    public String name;
    private String node_UUID;
    public int quantity;

    //Constructor

    public ItemModel(String sku, String name, String node_UUID, int quantity){
        this.sku = sku;
        this.name = name;
        this.node_UUID = node_UUID;
        this.quantity = quantity;
    }

    //Getters & Setters

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(){this.quantity++; };

    public void minusQuantity(){this.quantity--; };

    public String getNode_UUID() {
        return node_UUID;
    }

    public void setNode_UUID(String node_UUID) {
        this.node_UUID = node_UUID;
    }
}
