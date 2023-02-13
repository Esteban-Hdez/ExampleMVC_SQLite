package com.ehs.examplemvc_sqlite.View;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ehs.examplemvc_sqlite.R;
import com.ehs.examplemvc_sqlite.database.AdminBD;
import com.google.android.material.textfield.TextInputLayout;


public class UpdateActivity extends AppCompatActivity {

    EditText idActualizar;

    EditText nombre;
    EditText apellido;
    EditText grado;
    EditText grupo;
    EditText turno;

    TextInputLayout input_idActualizar;
    TextInputLayout input_nombre;
    TextInputLayout input_apellido;
    TextInputLayout input_grado;
    TextInputLayout input_grupo;
    TextInputLayout input_turno;

    Button btnActualizar, btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        //relacion de graficos con clase
        idActualizar = (EditText) findViewById(R.id.ct_id_actualizar);
        nombre = (EditText) findViewById(R.id.ct_nombre_act);
        apellido = (EditText) findViewById(R.id.ct_apellido_act);
        grado = (EditText) findViewById(R.id.ct_grado_act);
        grupo = (EditText) findViewById(R.id.ct_grupo_act);
        turno = (EditText) findViewById(R.id.ct_turno_act);
        btnActualizar = (Button) findViewById(R.id.btn_actualizar_act);
        btnBuscar = findViewById(R.id.bt_buscar_update);

        input_idActualizar = findViewById(R.id.input_update_id);
        input_nombre = findViewById(R.id.input_update_nombre);
        input_apellido = findViewById(R.id.input_update_apellido);
        input_grado = findViewById(R.id.input_update_grado);
        input_grupo = findViewById(R.id.input_update_grupo);
        input_turno = findViewById(R.id.input_update_turno);

        AdminBD adminBD = new AdminBD(getApplicationContext());

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idActualizar.getText().toString().trim().isEmpty()) {
                    input_idActualizar.setError("Campo obligatorio");
                    input_idActualizar.requestFocus();
                } else {
                    input_idActualizar.setErrorEnabled(false);
                    Cursor cursor = adminBD.getAlumnoById(idActualizar.getText().toString());
                    if (cursor.moveToNext()){
                        nombre.setText(cursor.getString(1));
                        apellido.setText(cursor.getString(2));
                        grado.setText(cursor.getString(3));
                        grupo.setText(cursor.getString(4));
                        turno.setText(cursor.getString(5));
                    } else {
                        clean();
                        Toast.makeText(UpdateActivity.this, "No se encontraron coincidencias", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    long id = adminBD.actualizarAlumno(idActualizar.getText().toString(),
                            nombre.getText().toString(),
                            apellido.getText().toString(),
                            grado.getText().toString(),
                            grupo.getText().toString(),
                            turno.getText().toString());
                    if (id != 0){
                        clean();
                        Toast.makeText(getApplicationContext(), "Se ha actualizado el registro!" + id, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ocurrio un error", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    private boolean validate() {
        boolean retorno = true;

        if (idActualizar.getText().toString().trim().isEmpty()) {
            input_idActualizar.setError("Campo obligatorio");
            input_idActualizar.requestFocus();
            return false;
        } else {
            input_idActualizar.setErrorEnabled(false);
        }

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
        idActualizar.setText("");
        nombre.setText("");
        apellido.setText("");
        grado.setText("");
        grupo.setText("");
        turno.setText("");
        idActualizar.requestFocus();
    }
}