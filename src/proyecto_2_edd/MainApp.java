/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */
public class MainApp {
    
    public static final String Direct_Default = "./ArchivosProyecto/";

    ControladorApp controladorApp;

    public MainApp() {
        this.controladorApp = new ControladorApp();
        MainInterface mainInterface = new MainInterface(this);
        mainInterface.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MainApp();
    }
}    
