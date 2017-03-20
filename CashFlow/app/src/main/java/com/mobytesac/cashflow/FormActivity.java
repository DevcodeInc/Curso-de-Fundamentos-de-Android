package com.mobytesac.cashflow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int RESULT_OK = 1;
    public static final int RESULT_CANCEL = 0;
    private Spinner tipo;
    private Button btn_save;
    private EditText text_monto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        initComponents();
    }

    private void initComponents(){
        tipo = (Spinner) findViewById(R.id.tipo);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        text_monto = (EditText) findViewById(R.id.text_monto);

        String[] tipos = new String[2];
        tipos[0]= "Ingreso";
        tipos[1]= "Egreso";

        ArrayAdapter<String>  adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tipos
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if(validate()){
            Double monto = Double.parseDouble(text_monto.getText().toString().trim());
            String tipo_str = tipo.getSelectedItem().toString();
            setData(monto, tipo_str);
        }else{
            Toast.makeText(this, "Ingrese un monto válido", Toast.LENGTH_SHORT).show();
        }


    }

    private void setData(Double monto, String tipo){
        Intent intent = new Intent();
        intent.putExtra("monto", monto);
        intent.putExtra("tipo", tipo);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCEL, intent);
        finish();
    }

    private boolean validate(){
        return text_monto.getText().toString().trim().length() > 0;
    }
}
