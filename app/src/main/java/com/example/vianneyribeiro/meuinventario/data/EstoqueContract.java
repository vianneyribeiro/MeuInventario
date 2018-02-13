package com.example.vianneyribeiro.meuinventario.data;

import android.provider.BaseColumns;

/**
 * Created by vianneyribeiro on 12/02/18.
 */

public class EstoqueContract {

    public EstoqueContract() {
    }

    public static final class EstoqueEntry implements BaseColumns {

        public static final String TABLE_NAME = "estoque";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "nome";
        public static final String COLUMN_PRICE = "preco";
        public static final String COLUMN_QUANTITY = "quantidade";
        public static final String COLUMN_SUPPLIER_NAME = "fornecedor";
        public static final String COLUMN_SUPPLIER_PHONE = "fornecedor_phone";
        public static final String COLUMN_SUPPLIER_EMAIL = "fornecedor_email";
        public static final String COLUMN_IMAGE = "imagem";

        public static final String CREATE_TABLE_STOCK = "CREATE TABLE " +
                EstoqueContract.EstoqueEntry.TABLE_NAME + "(" +
                EstoqueContract.EstoqueEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                EstoqueContract.EstoqueEntry.COLUMN_NAME + " TEXT NOT NULL," +
                EstoqueContract.EstoqueEntry.COLUMN_PRICE + " TEXT NOT NULL," +
                EstoqueContract.EstoqueEntry.COLUMN_QUANTITY + " INTEGER NOT NULL DEFAULT 0," +
                EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_NAME + " TEXT NOT NULL," +
                EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_PHONE + " TEXT NOT NULL," +
                EstoqueContract.EstoqueEntry.COLUMN_SUPPLIER_EMAIL + " TEXT NOT NULL," +
                EstoqueEntry.COLUMN_IMAGE + " TEXT NOT NULL" + ");";
    }
}
