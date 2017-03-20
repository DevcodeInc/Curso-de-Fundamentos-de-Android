package com.mobytesac.cashflow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.mobytesac.cashflow.adapters.ItemAdapter;
import com.mobytesac.cashflow.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private List<Item> items;
    private ItemAdapter adapter;
    private TextView text_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }

    private void initComponents(){
        generateData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lista);
        text_total = (TextView) findViewById(R.id.text_total);

        adapter = new ItemAdapter();
        adapter.setItems(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setTotal();
    }

    private void generateData(){
        items = new ArrayList<>();
        Item item1 = new Item(23.3, "Ingreso");
        Item item2 = new Item(10.5, "Egreso");
        items.add(item1);
        items.add(item2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nuevo:
                navigationToFormActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);        
        }
    }

    private void navigationToFormActivity(){
        Intent intent = new Intent(this, FormActivity.class);
        startActivityForResult(intent, FormActivity.RESULT_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            if(resultCode == FormActivity.RESULT_OK){
                Double monto = data.getDoubleExtra("monto", 0);
                String tipo = data.getStringExtra("tipo");
                createItem(monto, tipo);
            }
            if(resultCode == FormActivity.RESULT_CANCEL){

            }
        }
    }

    private void createItem(Double monto, String tipo) {
        Item item = new Item(monto, tipo);
        adapter.setItem(item);
        setTotal();
    }

    private void setTotal(){
        Double total = 0.0;
        for(Item item: items){
            if(item.getTipo().equals("Ingreso")){
                total = total + item.getMonto();
            }else{
                total = total - item.getMonto();
            }
        }
        text_total.setText(String.format("S/. %.2f", total));
    }
}
