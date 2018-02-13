package com.example.vianneyribeiro.meuinventario;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vianneyribeiro.meuinventario.data.EstoqueContract;

/**
 * Created by vianneyribeiro on 12/02/18.
 */

public class EstoqueCursorAdapter extends CursorAdapter{


    private final MainActivity activity;

    public EstoqueCursorAdapter(MainActivity context, Cursor c) {
        super(context, c, 0);
        this.activity = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.product_name);
        TextView quantityTextView = view.findViewById(R.id.quantity);
        TextView priceTextView = view.findViewById(R.id.price);
        ImageView sale = view.findViewById(R.id.sale);
        ImageView image = view.findViewById(R.id.image_view);

        String name = cursor.getString(cursor.getColumnIndex(EstoqueContract.EstoqueEntry.COLUMN_NAME));
        final int quantity = cursor.getInt(cursor.getColumnIndex(EstoqueContract.EstoqueEntry.COLUMN_QUANTITY));
        String price = cursor.getString(cursor.getColumnIndex(EstoqueContract.EstoqueEntry.COLUMN_PRICE));

        image.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex(EstoqueContract.EstoqueEntry.COLUMN_IMAGE))));

        nameTextView.setText(name);
        quantityTextView.setText(String.valueOf(quantity));
        priceTextView.setText(price);

        final long id = cursor.getLong(cursor.getColumnIndex(EstoqueContract.EstoqueEntry._ID));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnViewItem(id);
            }
        });

        sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.clickOnSale(id,
                        quantity);
            }
        });
    }

}
