package com.ehs.examplemvc_sqlite.Utils;

public class Utils {

    public static boolean isNumeric(String value){
        return value != null && value.matches("[0-9.]+");
    }
}
