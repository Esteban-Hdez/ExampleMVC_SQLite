package com.ehs.examplemvc_sqlite.Controller;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.ehs.examplemvc_sqlite.R;
import com.ehs.examplemvc_sqlite.database.AlumnosContract;


public class AlumnosCursorAdapter extends CursorAdapter {

    public AlumnosCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_alumno, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //referencias interfaz
        TextView nombre = (TextView) view.findViewById(R.id.tv_nombre);
        TextView apellido = (TextView) view.findViewById(R.id.tv_apellido);
        TextView grado = (TextView) view.findViewById(R.id.tv_grado);
        TextView grupo = (TextView) view.findViewById(R.id.tv_grupo);
        TextView turno = (TextView) view.findViewById(R.id.tv_turno);

        //obtencion de datos BD
        String name = cursor.getString(cursor.getColumnIndex(AlumnosContract.AlumnoEntry.NOMBRE));
        String lastname = cursor.getString(cursor.getColumnIndex(AlumnosContract.AlumnoEntry.APELLIDO));
        String degree = cursor.getString(cursor.getColumnIndex(AlumnosContract.AlumnoEntry.GRADO));
        String group = cursor.getString(cursor.getColumnIndex(AlumnosContract.AlumnoEntry.GRUPO));
        String turn = cursor.getString(cursor.getColumnIndex(AlumnosContract.AlumnoEntry.TURNO));

        //asignar datos
        nombre.setText(name);
        apellido.setText(lastname);
        grado.setText("Grado: " + degree);
        grupo.setText("Grupo: " + group);
        turno.setText("Turno: " + turn);

    }
}
