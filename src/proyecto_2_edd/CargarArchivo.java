/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */

import javax.swing.JOptionPane;
import java.io.File;

public class CargarArchivo extends javax.swing.JFrame {
    
    private MainApp mainClass;
    private MainInterface mainInterface;

    /**
     * Creates new form CargarArchivo
     */
    public CargarArchivo() {
        initComponents();
    }
    
    public CargarArchivo(MainApp mainClass, MainInterface mainInterface) {
        setLocationRelativeTo(mainInterface);
        this.mainInterface = mainInterface;
        this.mainClass = mainClass;
        initComponents();
        // Seleccionar el directorio por defecto
        File directory = new File(MainApp.Direct_Default);
        aFileSelector.setCurrentDirectory(directory);        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jToolBar2 = new javax.swing.JToolBar();
        aFileSelector = new javax.swing.JFileChooser();

        jToolBar1.setRollover(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar2.setRollover(true);

        aFileSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aFileSelectorActionPerformed(evt);
            }
        });
        jToolBar2.add(aFileSelector);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aFileSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aFileSelectorActionPerformed

        File selectedFile = aFileSelector.getSelectedFile();
        if (selectedFile == null || evt.getActionCommand() == "Cancel") {
            this.mainInterface.setVisible(true);
            dispose();
            return;
        }        
        
        String fileName = selectedFile.getAbsolutePath();
        if (selectedFile.getName().endsWith(".json")) {
            this.mainClass.controladorApp.reiniciar();
//            if (!this.mainClass.controladorApp.cargarCasa(fileName)) {
//                JOptionPane.showMessageDialog(this,
//                        "No se pudo cargar el archivo json: " + fileName);
//                selectedFile = null;
//                aFileSelector.setSelectedFile(selectedFile);
//                return;
//            }
            
            this.mainInterface.setJLabelTitle(this.mainClass.controladorApp.casa.fullname);
            this.mainClass.controladorApp.Cargar_Arbol_and_Hash();
            this.mainClass.controladorApp.cargarArbolGraph((A_Arbol<Amo>) this.mainClass.controladorApp.arbolCasa);
            this.mainInterface.setVisible(true);
            dispose();
            return;
        }
        JOptionPane.showMessageDialog(this, "El archivo: " + fileName + " no es un .json");
        selectedFile = null;
        aFileSelector.setSelectedFile(selectedFile);
        return;
    }//GEN-LAST:event_aFileSelectorActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.mainInterface.setVisible(true);
    }//GEN-LAST:event_formWindowClosing


    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CargarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CargarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CargarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CargarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CargarArchivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser aFileSelector;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
}
