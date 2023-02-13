package com.ehs.examplemvc_sqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.ehs.examplemvc_sqlite.Model.Alumno;
import com.ehs.examplemvc_sqlite.database.AlumnosContract.AlumnoEntry;


public class AdminBD extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "escuela_primaria.db";

    //Constructor de Sqlite
    public AdminBD(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    //OnCreate de SQLITE
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + AlumnoEntry.TABLE_NAME + " ("
                + AlumnoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + AlumnoEntry.NOMBRE + " TEXT NOT NULL,"
                + AlumnoEntry.APELLIDO + " TEXT NOT NULL,"
                + AlumnoEntry.GRADO + " TEXT NOT NULL,"
                + AlumnoEntry.GRUPO + " TEXT NOT NULL,"
                + AlumnoEntry.TURNO + " TEXT NOT NULL)");

        guardarAlumno(sqLiteDatabase, new Alumno("Daniel", "Jimenez", "2", "C", "MAT"));
        guardarAlumno(sqLiteDatabase, new Alumno("Maria", "Lopez", "1", "B", "VES"));
        guardarAlumno(sqLiteDatabase, new Alumno("Francisco", "Mijares", "6", "A", "NOC"));
        guardarAlumno(sqLiteDatabase, new Alumno("Fernando", "Rodriguez", "6", "B", "MAT"));
        guardarAlumno(sqLiteDatabase, new Alumno("Rosa", "Antonio", "9", "D", "VES"));
        guardarAlumno(sqLiteDatabase, new Alumno("Beatriz", "Vazquez", "3", "C", "NOC"));
    }

    //On Upgrade  de SQLITE
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //GUARDAR ALUMNO
    public long guardarAlumno(SQLiteDatabase db, Alumno alumno) {
        return db.insert(
                AlumnoEntry.TABLE_NAME,
                null,
                alumno.toContentValues());
    }

    //ELIMINAR ALUMNO
    public long eliminarAlumno(String id) {
        return getReadableDatabase()
                .delete(
                        AlumnoEntry.TABLE_NAME,
                        AlumnoEntry._ID + " = ?",
                        new String[]{id}
                );
    }

    //ACTUALIZAR ALUMNO
    public long actualizarAlumno(String id, String nombre, String apellido, String grado, String grupo, String turno) {

        ContentValues datos = new ContentValues();

        if (!nombre.equals("")) {
            datos.put(AlumnoEntry.NOMBRE, nombre);
        }
        if (!apellido.equals("")) {
            datos.put(AlumnoEntry.APELLIDO, apellido);
        }
        if (!grado.equals("")) {
            datos.put(AlumnoEntry.GRADO, grado);
        }
        if (!grupo.equals("")) {
            datos.put(AlumnoEntry.GRUPO, grupo);
        }
        if (!turno.equals("")) {
            datos.put(AlumnoEntry.TURNO, turno);
        }

        return getReadableDatabase()
                .update(AlumnoEntry.TABLE_NAME,
                        datos,
                        AlumnoEntry._ID + " = ?",
                        new String[]{id});
    }

    //OBTENER TODOS LOS ALUMNOS
    public Cursor getAllAlumnos() {
        return getReadableDatabase()
                .query(
                        AlumnoEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
    }

    //OBTENER UN ALUMNO POR SU ID
    public Cursor getAlumnoById(String id) {
        return getReadableDatabase()
                .query(
                        AlumnoEntry.TABLE_NAME,
                        null,
                        AlumnoEntry._ID + " LIKE ?",
                        new String[]{id},
                        null,
                        null,
                        null
                );
    }

    //OBTENER UN ALUMNO POR SU Nombre
    public Cursor getAlumnoByName(String name) {
//        return getReadableDatabase()
//                .query(
//                        AlumnoEntry.TABLE_NAME,
//                        null,
//                        AlumnoEntry.NOMBRE + " LIKE ? ",
//                        new String[]{name},
//                        null,
//                        null,
//                        null
//                );
        return getReadableDatabase()
                .rawQuery("SELECT * FROM " + AlumnoEntry.TABLE_NAME + " "
                        + "WHERE " + AlumnoEntry.NOMBRE
                        + " LIKE '%" + name + "%'", null);
    }
}
