package com.ehs.examplemvc_sqlite.View;

import static com.ehs.examplemvc_sqlite.Utils.Utils.isNumeric;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ehs.examplemvc_sqlite.Controller.AlumnosCursorAdapter;
import com.ehs.examplemvc_sqlite.R;
import com.ehs.examplemvc_sqlite.database.AdminBD;
import com.google.android.material.textfield.TextInputLayout;


public class QueryActivity extends AppCompatActivity {

    ListView listaAlumnos;
    Button btnConsultar;
    EditText etID;

    TextInputLayout input_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        listaAlumnos = (ListView) findViewById(R.id.list_alumnos);
        btnConsultar = (Button) findViewById(R.id.btn_consultar_consultar);
        etID = (EditText) findViewById(R.id.ct_id_consultar);

        input_id = findViewById(R.id.input_query);

        AdminBD adminBD = new AdminBD(getApplicationContext());
        listaAlumnos.setAdapter(new AlumnosCursorAdapter(getApplicationContext(), adminBD.getAllAlumnos(), 0));

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    if (isNumeric(etID.getText().toString())) {
                        // buscar por id
                        if (adminBD.getAlumnoById(etID.getText().toString()).moveToNext()) {
                            listaAlumnos.setAdapter(new AlumnosCursorAdapter(
                                    getApplicationContext(),
                                    adminBD.getAlumnoById(etID.getText().toString()),
                                    0));
                        } else {
                            listaAlumnos.setAdapter(new AlumnosCursorAdapter(getApplicationContext(), adminBD.getAllAlumnos(), 0));
                            Toast.makeText(QueryActivity.this, "No se encontraron coincidencias", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // buscar por nombre
                        if (adminBD.getAlumnoByName(etID.getText().toString()).moveToNext()) {
                            listaAlumnos.setAdapter(new AlumnosCursorAdapter(
                                    getApplicationContext(),
                                    adminBD.getAlumnoByName(etID.getText().toString()),
                                    0));
                        } else {
                            listaAlumnos.setAdapter(new AlumnosCursorAdapter(getApplicationContext(), adminBD.getAllAlumnos(), 0));
                            Toast.makeText(QueryActivity.this, "No se encontraron coincidencias", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

    }

    private boolean validate() {
        boolean retorno = true;

        if (etID.getText().toString().trim().isEmpty()) {
            input_id.setError("Debe proporcionar una ID");
            etID.requestFocus();
            retorno = false;
        } else {
            input_id.setErrorEnabled(false);
        }

        return retorno;
    }
}