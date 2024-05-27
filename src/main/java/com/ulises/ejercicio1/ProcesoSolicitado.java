/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ulises.ejercicio1;

import java.util.Date;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author ulises
 */
public class ProcesoSolicitado {
    public Integer procesaNumero(String strnumero){
        Integer numero = Integer.parseInt(strnumero);
        return numero+1;
    }
    public String procesaTexto(String str){
        String nstr = "";
        char caracter;
        for (int i=0; i<str.length(); i++)
        {
            caracter= str.charAt(i);
            nstr= caracter+nstr;
        }
        return nstr;
    }
    public Date procesaFecha(String fechastr){
        Date fecha = null;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try{
            fecha = formatter.parse(fechastr);
            return DateUtils.addDays(fecha, 1);
        }catch(Exception e){
            return fecha;
        }
    }
    public String procesaBoolean(String str){
        boolean valorActual = Boolean.parseBoolean(str);
        boolean valorFinal = !valorActual;
        return Boolean.toString(valorFinal);
    }
}
