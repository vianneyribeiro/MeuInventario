package com.example.vianneyribeiro.meuinventario;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vianneyribeiro.meuinventario.data.EstoqueItem;
import com.example.vianneyribeiro.meuinventario.data.InventarioDbHelper;

public class MainActivity extends AppCompatActivity {

    InventarioDbHelper dbHelper;
    EstoqueCursorAdapter adapter;
    int lastVisibleItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new InventarioDbHelper(this);

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        final ListView listView = findViewById(R.id.list_view);
        View emptyView = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyView);

        Cursor cursor = dbHelper.readStock();

        adapter = new EstoqueCursorAdapter(this, cursor);
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState == 0) return;
                final int currentFirstVisibleItem = view.getFirstVisiblePosition();
                if (currentFirstVisibleItem > lastVisibleItem) {
                    fab.show();
                } else if (currentFirstVisibleItem < lastVisibleItem) {
                    fab.hide();
                }
                lastVisibleItem = currentFirstVisibleItem;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.swapCursor(dbHelper.readStock());
    }

    public void clickOnViewItem(long id) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("itemId", id);
        startActivity(intent);
    }

    public void clickOnSale(long id, int quantity) {
        dbHelper.sellOneItem(id, quantity);
        adapter.swapCursor(dbHelper.readStock());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_dummy_data:
                // add dummy data for testing
                Toast.makeText(this, "Inserindo Dados para Teste", Toast.LENGTH_SHORT).show();
                addDummyData();
                adapter.swapCursor(dbHelper.readStock());
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Add data for demo purposes
     */
    private void addDummyData() {
        EstoqueItem skol269 = new EstoqueItem(
                "Skol latinha 269 ml",
                "R$ 1,99",
                45,
                "Distribuidor Malta",
                "85 3287129520",
                "malta@comerciomalta.com",
                "android.resource://com.example.vianneyribeiro.meuinventario/drawable/skol_lata_269");
        dbHelper.insertItem(skol269);

        EstoqueItem antLataSubZero350 = new EstoqueItem(
                "Antartica Lata Sub Zero",
                "R$ 2,49",
                21,
                "Extra Hipermercados",
                "85 98172333",
                "extra@extra.com",
                "android.resource://com.example.vianneyribeiro.meuinventario/drawable/ant_lata_sub_zero_350");
        dbHelper.insertItem(antLataSubZero350);

        EstoqueItem antPack12 = new EstoqueItem(
                "Antárctica Pack 12",
                "R$ 60,00",
                74,
                "Comercial Chagas",
                "85 34556777",
                "chagas@chagas.com",
                "android.resource://com.example.vianneyribeiro.meuinventario/drawable/pack_12_ant_350");
        dbHelper.insertItem(antPack12);

        EstoqueItem antLata350 = new EstoqueItem(
                "Antartica Lata 350ml",
                "R$ 2,67",
                44,
                "Casa da Cerveja LTDA",
                "85 498485945",
                "zelito@cervas.com",
                "android.resource://com.example.vianneyribeiro.meuinventario/drawable/ant_lata_350");
        dbHelper.insertItem(antLata350);

        EstoqueItem skolLongNeck = new EstoqueItem(
                "Skol Long Neck 255ml",
                "R$ 3,00",
                34,
                "Jeridas Comercial LTDA",
                "88 5454555442",
                "jeridas@comercidodebebidas.com",
                "android.resource://com.example.vianneyribeiro.meuinventario/drawable/skol_long_neck_255");
        dbHelper.insertItem(skolLongNeck);

        EstoqueItem bohemiaPack = new EstoqueItem(
                "Bohemia Pack 6x255ml",
                "R$ 24,00",
                26,
                "Seu Valdomiro",
                "85 3843433388",
                "comercial@seuvalsomiro.com",
                "android.resource://com.example.vianneyribeiro.meuinventario/drawable/bohemia_pack_6_255");
        dbHelper.insertItem(bohemiaPack);

        EstoqueItem brahmaLata355 = new EstoqueItem(
                "Brahma lata 355ml",
                "R$ 2,00",
                54,
                "Festas Comercial LTDA",
                "85 34394929222",
                "festascomercial@comercio.com.br",
                "android.resource://com.example.vianneyribeiro.meuinventario/drawable/brahma_lata_355");
        dbHelper.insertItem(brahmaLata355);

        EstoqueItem brahmaMalzbier = new EstoqueItem(
                "Brahma Malzbier 355ml",
                "R$ 5,00",
                12,
                "Extra Comercial LTDA",
                "85 72323233",
                "jorge@extracomercial.com",
                "android.resource://com.example.vianneyribeiro.meuinventario/drawable/brahma_malzbier_350");
        dbHelper.insertItem(brahmaMalzbier);

        EstoqueItem brahmaZero = new EstoqueItem(
                "Brahma Zero Alcool 355ml",
                "R$ 4,50",
                62,
                "Extra Comercial LTDA",
                "85 45738457833",
                "jorge@extracomercial.com",
                "android.resource://com.example.vianneyribeiro.meuinventario/drawable/brahma_zero_360");
        dbHelper.insertItem(brahmaZero);

        EstoqueItem brahmaPak12 = new EstoqueItem(
                "Brahma pack 12",
                "R$ 50,00",
                22,
                "Casa da Cerva",
                "85 83848322",
                "cerva@casadacerva.com",
                "android.resource://com.example.vianneyribeiro.meuinventario/drawable/brahma_pack_12_350");
        dbHelper.insertItem(brahmaPak12);
    }
}
