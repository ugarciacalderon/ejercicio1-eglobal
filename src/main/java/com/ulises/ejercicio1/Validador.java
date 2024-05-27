    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulises.ejercicio1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author ulises
 */
public class Validador {
    public boolean esFecha(String fecha){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try{
            formatter.parse(fecha);
            return true;
        }catch(Exception e){}
        return false;
    }
    public boolean esNumero(String strNum) {
    if (strNum == null) {
        return false;
    }
    try {
        Double.parseDouble(strNum);
    } catch (NumberFormatException nfe) {
        return false;
    }
    return true;
    }
    public boolean esBooleano(String str) {
        if ("true".equalsIgnoreCase(str)) {
            return true;
        } else if ("false".equalsIgnoreCase(str)) {
            return true;
        } else {
            return false;
        }
    }
}
