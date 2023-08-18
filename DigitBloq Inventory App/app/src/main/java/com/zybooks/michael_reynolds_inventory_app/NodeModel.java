package com.zybooks.michael_reynolds_inventory_app;

import java.util.UUID;

public class NodeModel{
    private int empty_count;
    private int low_count;

    //Members

    private String node_name;
    private String node_addr;
    private String node_UUID;

    //Constructor

    public NodeModel(String name, String addr, int empty_count, int low_count){
        this.node_name = name;
        this.node_addr = addr;
        this.empty_count = empty_count;
        this.low_count = low_count;
        this.node_UUID = UUID.randomUUID().toString();
    }

    public NodeModel(String name, String addr, int empty_count, int low_count, String uuid){
        this.node_name = name;
        this.node_addr = addr;
        this.empty_count = empty_count;
        this.low_count = low_count;
        this.node_UUID = uuid;
    }

    //Getters & Setters


    public String getNode_name() {
        return node_name;
    }

    public void setNode_name(String node_name) {
        this.node_name = node_name;
    }

    public String getNode_addr() {
        return node_addr;
    }

    public void setNode_addr(String node_addr) {
        this.node_addr = node_addr;
    }

    public int getEmpty_count() {
        return empty_count;
    }

    public void setEmpty_count(int empty_count) {
        this.empty_count = empty_count;
    }

    public int getLow_count() {
        return low_count;
    }

    public void setLow_count(int low_count) {
        this.low_count = low_count;
    }

    public String getNode_UUID() {
        return node_UUID;
    }

    public void setNode_UUID(String node_UUID) {
        this.node_UUID = node_UUID;
    }
}
