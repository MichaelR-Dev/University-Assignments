package com.zybooks.michael_reynolds_inventory_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class InventoryDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "inventory.db";
    private static final int VERSION = 1;

    public InventoryDatabase(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    private static final class ItemTable {
        private static final String TABLE = "items";
        private static final String COL_ID = "_id";
        private static final String COL_NODE = "node_uuid";
        private static final String COL_NAME = "name";
        private static final String COL_SKU = "sku";
        private static final String COL_QUANTITY = "quantity";
    }

    private static final class NodeTable {
        private static final String TABLE = "nodes";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";

        private static final String COL_ADDR = "address";
        private static final String COL_UUID = "uuid";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ItemTable.TABLE + " (" +
                ItemTable.COL_ID + " integer primary key autoincrement, " +
                ItemTable.COL_NODE + " text, " +
                ItemTable.COL_NAME + " text, " +
                ItemTable.COL_SKU + " text, " +
                ItemTable.COL_QUANTITY + " integer)");

        db.execSQL("create table " + NodeTable.TABLE + " (" +
                NodeTable.COL_ID + " integer primary key autoincrement, " +
                NodeTable.COL_NAME + " text, " +
                NodeTable.COL_ADDR + " text, " +
                NodeTable.COL_UUID + " text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + ItemTable.TABLE);
        db.execSQL("drop table if exists " + NodeTable.TABLE);
        onCreate(db);
    }

    public void newNode(NodeModel nodeModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NodeTable.COL_NAME, nodeModel.getNode_name());
        cv.put(NodeTable.COL_ADDR, nodeModel.getNode_addr());
        cv.put(NodeTable.COL_UUID, nodeModel.getNode_UUID().toString());

        db.insert(NodeTable.TABLE, null, cv);
        db.close();
    };

    public void removeNode(NodeModel nodeModel){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(NodeTable.TABLE, "uuid=?", new String[]{nodeModel.getNode_UUID().toString()});
        db.close();
    };

    public void addItem(ItemModel itemModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ItemTable.COL_NODE, itemModel.getNode_UUID());
        cv.put(ItemTable.COL_NAME, itemModel.getName());
        cv.put(ItemTable.COL_SKU, itemModel.getSku());
        cv.put(ItemTable.COL_QUANTITY, itemModel.getQuantity());

        db.insert(ItemTable.TABLE, null, cv);
        db.close();
    };

    public void changeQuantity(ItemModel itemModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ItemTable.COL_QUANTITY, itemModel.getQuantity());

        db.update(ItemTable.TABLE, cv, "sku=? AND node_uuid=?", new String[]{itemModel.getSku(), itemModel.getNode_UUID()});
        db.close();
    };

    public void removeItem(ItemModel itemModel){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(ItemTable.TABLE, "sku=? AND node_uuid=?", new String[]{itemModel.getSku(), itemModel.getNode_UUID()});
        db.close();
    };

    public List<NodeModel> findAllNodes(String table) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + table, null);
        List<NodeModel> nodeList = new ArrayList<>();

        if (cursor != null) {
            try {
                // Iterate through the cursor to process each row
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String addr = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                    String uuid = cursor.getString(cursor.getColumnIndexOrThrow("uuid"));

                    // Run the function on each row's data
                    nodeList.add(createNodeFromRow(name, addr, uuid));
                }
            } finally {
                cursor.close();
                db.close();
            }
        }

        return nodeList;
    }

    public List<ItemModel> findAllItems(NodeModel nodeRef) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + "items" + " WHERE node_uuid=? ", new String[]{nodeRef.getNode_UUID()});
        List<ItemModel> itemList = new ArrayList<>();

        if (cursor != null) {
            try {
                // Iterate through the cursor to process each row
                while (cursor.moveToNext()) {
                    String sku = cursor.getString(cursor.getColumnIndexOrThrow("sku"));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String uuid = cursor.getString(cursor.getColumnIndexOrThrow("node_uuid"));
                    int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));

                    // Run the function on each row's data
                    itemList.add(createItemFromRow(sku, name, uuid, quantity));
                }
            } finally {
                cursor.close();
                db.close();
            }
        }

        return itemList;
    }

    private NodeModel createNodeFromRow(String name, String addr, String uuid) {
        return new NodeModel(name, addr, 0, 0, uuid);
    }

    private ItemModel createItemFromRow(String sku, String name, String uuid, int Quantity) {
        return new ItemModel(sku, name, uuid, Quantity);
    }
}
