/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_2_edd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


/**
 *
 * @author sebma
 */
public class Proyecto_2_EDD {

    public static void main(String[] args) {
        try {
            leerJasonFile("C:\\Users\\sebma\\OneDrive\\Documents\\GitHub\\Proyecto_2_EDD\\prueba.txt");
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static String leerJasonFile(String nombreArchivo) throws IOException {
        System.out.println("metodo leer json: " + nombreArchivo);
//        String path = new File(".").getCanonicalPath();
//        nombreArchivo = path + File.separator + nombreArchivo;
        System.out.println(nombreArchivo);
        try (FileReader reader = new FileReader("C:\\Users\\sebma\\OneDrive\\Documents\\GitHub\\Proyecto_2_EDD\\prueba.txt")) {
            JsonParser parser = new JsonParser();
            JsonObject objetoPrueba = parser.parse(reader).getAsJsonObject();
            System.out.println(objetoPrueba);
            return ("hola");
        }
    }
  
    }
