package com.mobytesac.devcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = MainActivity.class.getSimpleName();

    Button btnIniciar;
    Button btnCompartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se esta creando la actividad
        Log.i(TAG, "onCreate: ");
        initComponents();
    }

    public void initComponents(){
        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnCompartir = (Button) findViewById(R.id.btnCompartir);
        btnIniciar.setOnClickListener(onIniciarListener);
        btnCompartir.setOnClickListener(onCompartirListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //la actividad esta a punto de ser visible
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //la actividad ya esta visible
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //la actividad esta a punto de ser pausado
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //la actividad ya no es visible
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //la actividad esta a punto de ser destruida
        Log.i(TAG, "onDestroy: ");
    }

    //region OnclickListener
    private View.OnClickListener onIniciarListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("param", "mensaje de la primera actividad");
            startActivity(intent);
        }
    };

    private View.OnClickListener onCompartirListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "DevCode");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Texto a compartir");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        }
    };
    //endregion

}
