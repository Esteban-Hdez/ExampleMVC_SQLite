package com.ehs.examplemvc_sqlite.database;

import android.provider.BaseColumns;

public class AlumnosContract {

    public static abstract class AlumnoEntry implements BaseColumns{

        //PLANTILLA DE LA TABLA ALUMNOS

        public static final String TABLE_NAME = "ALUMNO";

        public static final String NOMBRE = "NOMBRE";
        public static final String APELLIDO = "APELLIDO";
        public static final String GRADO = "GRADO";
        public static final String GRUPO = "GRUPO";
        public static final String TURNO = "TURNO";
    }
}
