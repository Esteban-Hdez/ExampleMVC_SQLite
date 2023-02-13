package com.ehs.examplemvc_sqlite.View;

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


public class DeleteActivity extends AppCompatActivity {

    ListView listaAlumnosD;
    EditText alumnoD;
    Button btnDelete;

    TextInputLayout input_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        listaAlumnosD = (ListView) findViewById(R.id.list_alumnos_eliminar);
        alumnoD = (EditText) findViewById(R.id.ct_id_eliminar);
        btnDelete = (Button) findViewById(R.id.btn_eliminar_alumno);

        input_delete = findViewById(R.id.input_delete);

        AdminBD adminBD = new AdminBD(getApplicationContext());
        listaAlumnosD.setAdapter(new AlumnosCursorAdapter(getApplicationContext(), adminBD.getAllAlumnos(), 0));

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    if (adminBD.getAlumnoById(alumnoD.getText().toString()).moveToNext()) {
                        long id = adminBD.eliminarAlumno(alumnoD.getText().toString());
                        if (id != 0) {
                            listaAlumnosD.setAdapter(new AlumnosCursorAdapter(getApplicationContext(), adminBD.getAllAlumnos(), 0));
                            Toast.makeText(
                                            DeleteActivity.this,
                                            "El alumno con el id: " + alumnoD.getText().toString() + " ha sido eliminado.",
                                            Toast.LENGTH_LONG)
                                    .show();
                        }
                    } else {
                        Toast.makeText(DeleteActivity.this, "No se encontraron coincidencias", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    private boolean validate() {
        boolean retorno = true;

        if (alumnoD.getText().toString().trim().isEmpty()) {
            input_delete.setError("Debe proporcionar una ID");
            alumnoD.requestFocus();
            retorno = false;
        } else {
            input_delete.setErrorEnabled(false);
        }
        return retorno;
    }
}