package com.ehs.examplemvc_sqlite.View;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ehs.examplemvc_sqlite.Model.Alumno;
import com.ehs.examplemvc_sqlite.R;
import com.ehs.examplemvc_sqlite.database.AdminBD;
import com.google.android.material.textfield.TextInputLayout;


public class InsertActivity extends AppCompatActivity {

    Button btnInsertar;
    EditText nombre;
    EditText apellido;
    EditText grado;
    EditText grupo;
    EditText turno;

    TextInputLayout input_nombre;
    TextInputLayout input_apellido;
    TextInputLayout input_grado;
    TextInputLayout input_grupo;
    TextInputLayout input_turno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        btnInsertar = (Button) findViewById(R.id.btn_insert_alumno);
        nombre = (EditText) findViewById(R.id.ct_nombre);
        apellido = (EditText) findViewById(R.id.ct_apellido);
        grado = (EditText) findViewById(R.id.ct_grado);
        grupo = (EditText) findViewById(R.id.ct_grupo);
        turno = (EditText) findViewById(R.id.ct_turno);

        input_nombre = findViewById(R.id.input_nombre);
        input_apellido = findViewById(R.id.input_apellido);
        input_grado = findViewById(R.id.input_grado);
        input_grupo = findViewById(R.id.input_grupo);
        input_turno = findViewById(R.id.input_turno);

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();

                if (validate()) {
                    AdminBD adminBD = new AdminBD(getApplicationContext());
                    SQLiteDatabase database = adminBD.getWritableDatabase();
                    adminBD.guardarAlumno(database, new Alumno(
                            nombre.getText().toString(),
                            apellido.getText().toString(),
                            grado.getText().toString(),
                            grupo.getText().toString(),
                            turno.getText().toString()));

                    Toast.makeText(getApplicationContext(), "Se agrego correctamente el registro!", Toast.LENGTH_SHORT).show();
                    clean();
                }
            }
        });
    }

    private boolean validate() {
        boolean retorno = true;

        if (nombre.getText().toString().trim().isEmpty()) {
            input_nombre.setError("Campo obligatorio");
            input_nombre.requestFocus();
            return false;
        } else {
            input_nombre.setErrorEnabled(false);
        }

        if (apellido.getText().toString().trim().isEmpty()) {
            input_apellido.setError("Campo obligatorio");
            input_apellido.requestFocus();
            return false;
        } else {
            input_apellido.setErrorEnabled(false);
        }

        if (grado.getText().toString().trim().isEmpty()) {
            input_grado.setError("Campo obligatorio");
            input_grado.requestFocus();
            return false;
        } else {
            input_grado.setErrorEnabled(false);
        }

        if (grupo.getText().toString().trim().isEmpty()) {
            input_grupo.setError("Campo obligatorio");
            input_grupo.requestFocus();
            return false;
        } else {
            input_grupo.setErrorEnabled(false);
        }

        if (turno.getText().toString().trim().isEmpty()) {
            input_turno.setError("Campo obligatorio");
            input_turno.requestFocus();
            return false;
        } else {
            input_turno.setErrorEnabled(false);
        }

        return retorno;
    }

    private void clean(){
        nombre.setText("");
        apellido.setText("");
        grado.setText("");
        grupo.setText("");
        turno.setText("");
        nombre.requestFocus();
    }
}