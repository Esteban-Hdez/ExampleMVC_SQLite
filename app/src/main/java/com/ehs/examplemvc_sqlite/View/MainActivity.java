package com.ehs.examplemvc_sqlite.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ehs.examplemvc_sqlite.R;
import com.ehs.examplemvc_sqlite.database.AdminBD;


public class MainActivity extends AppCompatActivity {

    Button btnInsertar;
    Button btnConsultar;
    Button btnActualizar;
    Button btnEliminar;

    Intent pantallas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ejecucionBD();

        //Inicializacion de botones
        btnInsertar = (Button) findViewById(R.id.btn_insertar);
        btnConsultar = (Button) findViewById(R.id.btn_consultar);
        btnActualizar = (Button) findViewById(R.id.btn_actualizar);
        btnEliminar = (Button) findViewById(R.id.btn_eliminar);

        //Escuchadores de botones
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantallas = new Intent(getApplicationContext(), InsertActivity.class);
                startActivity(pantallas);
            }
        });
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantallas = new Intent(getApplicationContext(), QueryActivity.class);
                startActivity(pantallas);
            }
        });
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantallas = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(pantallas);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pantallas = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(pantallas);
            }
        });
    }

    public void ejecucionBD(){
        AdminBD adminBD = new AdminBD(MainActivity.this);
        SQLiteDatabase database = adminBD.getWritableDatabase();
    }
}