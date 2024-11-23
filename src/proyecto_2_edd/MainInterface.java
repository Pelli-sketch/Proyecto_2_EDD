/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_2_edd;

import com.sun.source.tree.Tree;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

/**
 *
 * @author pablo
 */
public class MainInterface extends javax.swing.JFrame implements ViewerListener, MouseMotionListener {
    
    private MainApp mainClass;
    private ControladorApp controladorApp;
    private Graph graph;
    private View vista;
    private SwingViewer visualizador;
    private boolean loop = true;
    
    private Node selectedNode = null;
    private boolean draggedNode = false;
    private String selectedLordByName = null;
    private boolean selectedLordByNameShowed = false;
    private String selectedLordByTitle = null;
    private boolean selectedLordByTitleShowed = false;
    

    /**
     * Creates new form MainInterface
     */
    public MainInterface() {
        initComponents();
    }
    
    /**
     * Constructor de VentanaPrincipal cuando se le pasa el main del programa
     * 
     * @param mainClass la clase main del programa
     */    
    public MainInterface(MainApp mainClass) {
        System.setProperty("org.graphstream.ui", "swing");
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gene Tree of the Houses");
        this.mainClass = mainClass;
        this.controladorApp = this.mainClass.controladorApp;
        this.graph = this.controladorApp.graph;

        initComponents();
        setLayout(new BorderLayout());
        jPanelArbol.setLayout(new BorderLayout());
        jPanelArbol.setPreferredSize(new Dimension(1000, 1000));
        jPanelTitulo.setLayout(new BorderLayout());
        jPanelManejoArea.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1400, 1200));
        this.setJLabelTitle("House");
        this.setUpGraphView();
    }    
    /**
     * Metodo para configurar la vista gráfica
     */

    private void setUpGraphView() {
    }

    /**
     * Metodo para manejar los eventos del mouse
     */

    private void managingMouse() {

    /**
     * Metodo para cerrar la ventana
     * 
     * @param viewName el nombre de la ventana
     */

    @Override
    public void viewClosed(String viewName) {
        loop = false;
    }

    /**
     * Metodo para manejar los eventos de un nodo o lord del arbol cuando se
     * presiona
     * 
     * @param id el id del nodo o lord
     */

    @Override
    public void buttonPushed(String id) {
        
    }

    /**
     * Metodo para manejar los eventos de un nodo o lord del arbol
     * cuando se mantiene presionado.
     * 
     * @param id el id del nodo o lord
     */

    @Override
    public void buttonReleased(String id) {
    }

    @Override
    public void mouseOver(String id) {
        // Manejar el evento de mouse sobre un nodo
        // Se deja vacio para cumplir con la interfaz
    }

    @Override
    public void mouseLeft(String id) {
        // Manejar el evento de mouse dejando un nodo
        // Se deja vacio para cumplir con la interfaz
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Manejar el arrastre del mouse sobre un nodo
        // System.out.println("Mouse arrastrado");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // Manejar el movimiento del mouse sobre un nodo
        // Se deja vacio para cumplir con la interfaz
    }

    /**
     * Metodo para configurar el titulo de la ventana
     * 
     * @param titleString el titulo de la ventana
     */

    public void setJLabelTitle(String titleString) {
    }

    /**
     * Metodo para resetear la ventana.
     */

    public void resetWindow() {
    }

    /**
     * Metodo para habilitar o desahabilitar los elementos de la ventana
     */

    public void elementsCheckEnabled() {
    }

    /**
     * Metodo para configurar el spinner
     */

    private void setUpSpinner() {
        if (this.appController.houseTree != null) {
            int treeHeight = this.appController.houseTree.altura();
            SpinnerNumberModel model = new SpinnerNumberModel(0, 0, treeHeight - 1, 1);
            this.jSpinnerNroGeneracion.setModel(model);
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) this.jSpinnerNroGeneracion.getEditor();
            editor.getTextField().setEditable(false);
            return;
        }
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 0, 0);
        this.jSpinnerNroGeneracion.setModel(model);
    }

    /**
     * Metodo para mostrar la ventana de detalle de un Lord
     * 
     * @param lordString el nombre o alias del Lord
     * @return el arbol del lord que se busca
     */

    public Arbol<Amo> getVentanaDetalleLord(String lordString) {
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanelArbol = new javax.swing.JPanel();
        jLabelTituloMainInterface = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFileChooser = new javax.swing.JMenu();
        jMenuExit = new javax.swing.JMenu();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelArbol.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));

        javax.swing.GroupLayout jPanelArbolLayout = new javax.swing.GroupLayout(jPanelArbol);
        jPanelArbol.setLayout(jPanelArbolLayout);
        jPanelArbolLayout.setHorizontalGroup(
            jPanelArbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelArbolLayout.setVerticalGroup(
            jPanelArbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );

        jLabelTituloMainInterface.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.black, java.awt.Color.black));

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jButton1.setText("jButton1");

        jTextField1.setText("jTextField1");

        jLabel1.setText("jLabel1");

        jMenuFileChooser.setText("File");
        jMenuBar1.add(jMenuFileChooser);

        jMenuExit.setText("Exit");
        jMenuBar1.add(jMenuExit);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelTituloMainInterface, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addComponent(jPanelArbol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(162, 162, 162))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTituloMainInterface, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelArbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainInterface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTituloMainInterface;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuExit;
    private javax.swing.JMenu jMenuFileChooser;
    private javax.swing.JPanel jPanelArbol;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
