/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author sebma
 */
public class Proyecto_2_EDD {

    public static void main(String[] args) {
        try {
            LectorJson.leerJson("Baratheon.json");
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
