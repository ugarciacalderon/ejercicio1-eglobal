package com.ulises.ejercicio1;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ValidaTipoArchivo {
    public String deboProcesarArchivo(File archivo) throws IOException{
        boolean existe = existeArchivo(archivo);
        if(!existe){
            return "null";
        }else{
            var tipo = Files.probeContentType(archivo.toPath());

            if(tipo.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
                return "xls";
            }
        }
        return "";
    }
    public boolean existeArchivo(File archivo){
        return archivo.exists();
    }
}
