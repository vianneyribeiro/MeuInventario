package com.example.vianneyribeiro.meuinventario.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vianneyribeiro on 12/02/18.
 */

public class InventarioDbHelper extends SQLiteOpenHelper{

    public final static String DB_NAME = "inventario.db";
    public final static int DB_VERSION = 1;
 //   public final static String LOG_TAG = InventarioDbHelper.class.getCanonicalName();

    public InventarioDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EstoqueContract.EstoqueEntry.CREATE_TABLE_STOCK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertItem(EstoqueItem item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EstoqueContract.EstoqueEntry.COLUMN_NAME, item.getProductName());
        values.put(EstoqueContract.EstoqueEntry.COLUMN_PRICE, item.getPrice());
        values.put(EstoqueContract.EstoqueEntry.COLUMN_QUANTITY, item.getQuantity());
        values.put(EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_NAME, item.getSupplierName());
        values.put(EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_PHONE, item.getSupplierPhone());
        values.put(EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_EMAIL, item.getSupplierEmail());
        values.put(EstoqueContract.EstoqueEntry.COLUMN_IMAGE, item.getImage());
        long id = db.insert(EstoqueContract.EstoqueEntry.TABLE_NAME, null, values);
    }

    public Cursor readStock() {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                EstoqueContract.EstoqueEntry._ID,
                EstoqueContract.EstoqueEntry.COLUMN_NAME,
                EstoqueContract.EstoqueEntry.COLUMN_PRICE,
                EstoqueContract.EstoqueEntry.COLUMN_QUANTITY,
                EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_NAME,
                EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_PHONE,
                EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_EMAIL,
                EstoqueContract.EstoqueEntry.COLUMN_IMAGE
        };
        Cursor cursor = db.query(
                EstoqueContract.EstoqueEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public Cursor readItem(long itemId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                EstoqueContract.EstoqueEntry._ID,
                EstoqueContract.EstoqueEntry.COLUMN_NAME,
                EstoqueContract.EstoqueEntry.COLUMN_PRICE,
                EstoqueContract.EstoqueEntry.COLUMN_QUANTITY,
                EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_NAME,
                EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_PHONE,
                EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_EMAIL,
                EstoqueContract.EstoqueEntry.COLUMN_IMAGE
        };
        String selection = EstoqueContract.EstoqueEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };

        Cursor cursor = db.query(
                EstoqueContract.EstoqueEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        return cursor;
    }

    public void updateItem(long currentItemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EstoqueContract.EstoqueEntry.COLUMN_QUANTITY, quantity);
        String selection = EstoqueContract.EstoqueEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(currentItemId) };
        db.update(EstoqueContract.EstoqueEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }

    public void sellOneItem(long itemId, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        int newQuantity = 0;
        if (quantity > 0) {
            newQuantity = quantity -1;
        }
        ContentValues values = new ContentValues();
        values.put(EstoqueContract.EstoqueEntry.COLUMN_QUANTITY, newQuantity);
        String selection = EstoqueContract.EstoqueEntry._ID + "=?";
        String[] selectionArgs = new String[] { String.valueOf(itemId) };
        db.update(EstoqueContract.EstoqueEntry.TABLE_NAME,
                values, selection, selectionArgs);
    }
}
