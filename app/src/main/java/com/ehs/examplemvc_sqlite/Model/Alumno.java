package com.ehs.examplemvc_sqlite.Model;

import android.content.ContentValues;

import com.ehs.examplemvc_sqlite.database.AlumnosContract;


public class Alumno {

    //Atributros
    private String nombre;
    private String apellido;
    private String grado;
    private String grupo;
    private String turno;

    //inicializacion - constructor
    public Alumno(String name, String lastname, String degree, String group, String turn){
        this.nombre = name;
        this.apellido = lastname;
        this.grado = degree;
        this.grupo = group;
        this.turno = turn;
    }

    //getter y setter

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public ContentValues toContentValues(){
        ContentValues contenedor = new ContentValues();
        contenedor.put(AlumnosContract.AlumnoEntry.NOMBRE, nombre);
        contenedor.put(AlumnosContract.AlumnoEntry.APELLIDO, apellido);
        contenedor.put(AlumnosContract.AlumnoEntry.GRADO, grado);
        contenedor.put(AlumnosContract.AlumnoEntry.GRUPO, grupo);
        contenedor.put(AlumnosContract.AlumnoEntry.TURNO, turno);
        return  contenedor;
    }
}
