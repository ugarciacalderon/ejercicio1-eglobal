package com.ulises.ejercicio1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.dhatim.fastexcel.reader.*;

/**
 *
 * @author ulises
 */
public class Ejercicio1 {

    public static void main(String[] args) {
        String archivoEntrada = "";
        String archivoSalida = "";

        switch (args.length) {
            case 1:
                archivoEntrada = args[0];
                int indicePunto = archivoEntrada.lastIndexOf('.');
                archivoSalida = archivoEntrada.substring(0, indicePunto) + "-PROCESADO" + archivoEntrada.substring(indicePunto);
                break;
            case 2:
                archivoEntrada = args[0];
                archivoSalida = args[1];
                break;
            case 0:
                System.out.println("ingresa los argumentos: \n$1: ruta archivo de entrada (txt, csv, xlsx, xls) \n$2: ruta archivo de salida");
        }


        if(args.length>0){
            ValidaTipoArchivo valTipo = new ValidaTipoArchivo();

            File inpuFile = new File(archivoEntrada);
            File outFile = new File(archivoSalida);
            try{
                String tipoArchivo = valTipo.deboProcesarArchivo(inpuFile);
                switch (tipoArchivo) {
                    case "null":
                        System.out.println("El archivo no existe");
                        break;
                    case "txt":
                        System.out.println("archivo txt recibido");
                        break;
                    case "csv":
                        System.out.println("archivo csv recibido");
                        break;
                    case "xls":
                        iteradorExcel(inpuFile, outFile);
                        break;
                    default:
                        break;
                }
            }catch(Exception e){
                System.out.println("Ha ourrido un error"+ e.getLocalizedMessage());
            }
        }
    }
    public static void iteradorExcel(File fileLocation, File salida) throws IOException {
        ProcesoSolicitado procesador = new ProcesoSolicitado();
        Validador validadorTipoDato = new Validador();

        new File(salida.getParent()).mkdirs();

        try (OutputStream os = Files.newOutputStream(salida.toPath()); Workbook wb = new Workbook(os, "ejercicio", "1.0")) {

            Worksheet hoja1 = wb.newWorksheet("Hoja 1");
            Worksheet hoja2 = wb.newWorksheet("Hoja 2");

            Map<Integer, List<String>> data = new HashMap<>();

            try (FileInputStream file = new FileInputStream(fileLocation); ReadableWorkbook wbCargado = new ReadableWorkbook(file)) {
                Sheet sheet = wbCargado.getFirstSheet();
                try (Stream<Row> rows = sheet.openStream()) {
                    rows.forEach(r -> {
                        data.put(r.getRowNum(), new ArrayList<>());
                        
                        for (Cell cell : r) {
                            data.get(r.getRowNum()).add(cell.getRawValue());
                            String value = cell.getRawValue();
                            if (cell.getType() == CellType.BOOLEAN) {
                                value = Objects.equals(cell.getRawValue(), "0") ? "false" : "true";
                            }
                            hoja1.value(r.getRowNum(), cell.getColumnIndex(), value);
                            if(validadorTipoDato.esNumero(value)){
                                hoja2.value(r.getRowNum(), cell.getColumnIndex(), procesador.procesaNumero(value));
                            }else if(validadorTipoDato.esFecha(value)){
                                hoja2.value(r.getRowNum(), cell.getColumnIndex(), procesador.procesaFecha(value));
                            }else if(validadorTipoDato.esBooleano(value)){
                                hoja2.value(r.getRowNum(), cell.getColumnIndex(), procesador.procesaBoolean(value));
                            }else{
                                hoja2.value(r.getRowNum(), cell.getColumnIndex(), procesador.procesaTexto(value));
                            }
                        }
                    });
                }
            }
        }
        System.out.println("Ruta de archivo cargado: "+fileLocation.getAbsolutePath());
        System.out.println("Ruta de archivo guardado: "+ salida.getAbsolutePath());
    }
}
