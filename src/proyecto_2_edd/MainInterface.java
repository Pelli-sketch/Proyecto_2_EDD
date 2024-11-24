/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_2_edd;

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
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.view.ViewerListener;

/**
 *
 * @author pablo
 */
public class MainInterface extends javax.swing.JFrame implements ViewerListener, MouseMotionListener {
    
    private MainApp mainClass;
    private ControladorApp controladorApp;
    private Graph graph;
    private View view;
    private SwingViewer viewer;
    private boolean loop = true;
    
    private Node selectedNode = null;
    private boolean takedNode = false;
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
     * Constructor de MainInterface cuando se le pasa el main del programa
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
        jPanelInterfaceTitle.setLayout(new BorderLayout());
        jPanelManejoApp.setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 1000));
//        this.setJLabelTitle("House");
        this.configVistaGraph();
    }    
    /**
     * Metodo para configurar la vista gráfica
     */

    private void configVistaGraph() {
        viewer = (SwingViewer) new SwingViewer(graph, SwingViewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
        viewer.disableAutoLayout();
        view = viewer.addDefaultView(false);
        jPanelArbol.add((Component) view, BorderLayout.CENTER);
        ((Component) view).addMouseMotionListener(this);
        ControlMouse();
    }

    /**
     * Metodo para manejar los eventos del mouse
     */

    private void ControlMouse() {
        ViewerPipe fromViewer = viewer.newViewerPipe();
        fromViewer.addViewerListener(this);
        fromViewer.addSink(graph);

        new Thread(() -> {
            while (loop) {
                fromViewer.pump();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();        
    }

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
        Node node = graph.getNode(id);
        if (node != null) {
            this.selectedNode = node;
        }
    }

    /**
     * Metodo para manejar los eventos de un nodo o lord del arbol
     * cuando se mantiene presionado.
     * 
     * @param id el id del nodo o lord
     */

    @Override
    public void buttonReleased(String id) {
        Node node = graph.getNode(id);
        if (node != null) {
            if (this.selectedNode != null) {
                if (this.takedNode) {
                    this.takedNode = false;
                    return;
                }
                // Aquí tengo que buscar el lord y mostrar sus detalles
                this.getAmoInterface(id);
            }
        }
        this.takedNode = false;
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
        if (this.selectedNode != null) {
            this.takedNode = true;
        }
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
        this.jLabelTitleMainInterface.setText(titleString);
        this.jLabelTitleMainInterface.setVisible(true);
    }

    /**
     * Metodo para resetear la ventana.
     */

    public void reloadWindow() {
        if (this.controladorApp.arbolCasa == null) {
            return;
        }
        this.jTextName.setText("");
        this.jTextTitle.setText("");
        String[] arregloVacio = new String[0];
        this.jListNames.clearSelection();
        this.jListNames.setListData(arregloVacio);
        this.jListTitle.clearSelection();
        this.jListTitle.setListData(arregloVacio);
        this.jSpinnerGen.setValue(0);
        // hay que cambiar el valor min y max
        this.jTextAreaGen.setText("");
        this.controladorApp.cargarArbolGraph((A_Arbol<Amo>) this.controladorApp.arbolCasa);
        verificacionElemEnabled();
    }

    /**
     * Metodo para habilitar o desahabilitar los elementos de la ventana
     */

    public void verificacionElemEnabled() {
        if (this.controladorApp.arbolCasa == null) {
            this.jButtonName.setEnabled(false);
            this.jListNames.setEnabled(false);
            String[] arregloVacio = new String[0];
            this.jListNames.clearSelection();
            this.jListNames.setListData(arregloVacio);

            this.jButtonAncestors.setEnabled(false);

            this.jButtonTitle.setEnabled(false);
            this.jTextTitle.setEnabled(false);
            this.jListTitle.clearSelection();
            this.jListTitle.setListData(arregloVacio);

            this.jSpinnerGen.setValue(0);
            this.jSpinnerGen.setEnabled(false);
            this.jButtonGen.setEnabled(false);
            this.jTextAreaGen.setText("");

            this.jButtonForReset.setEnabled(false);
            return;

        }
        this.jButtonName.setEnabled(true);
        this.jTextName.setEnabled(true);

        this.jButtonTitle.setEnabled(true);
        this.jTextTitle.setEnabled(true);
        this.jSpinnerGen.setEnabled(true);
        this.jButtonGen.setEnabled(true);

        this.jButtonForReset.setEnabled(true);
        if (jListNames.getSelectedValue() != null) {
            this.jButtonAncestors.setEnabled(true);
        } else {
            this.jButtonAncestors.setEnabled(false);
        }
    }

    /**
     * Metodo para configurar el spinner
     */

    private void configSpinner() {
        if (this.controladorApp.arbolCasa != null) {
            int treeHeight = this.controladorApp.arbolCasa.height();
            SpinnerNumberModel model = new SpinnerNumberModel(0, 0, treeHeight - 1, 1);
            this.jSpinnerGen.setModel(model);
            JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) this.jSpinnerGen.getEditor();
            editor.getTextField().setEditable(false);
            return;
        }
        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 0, 0);
        this.jSpinnerGen.setModel(model);
    }

    /**
     * Metodo para mostrar la ventana de detalle de un amo
     * 
     * @param amoString el nombre o alias del amo
     * @return el arbol del amo que se busca
     */

    public Arbol<Amo> getAmoInterface(String amoString) {
        String[] amoNombreUnico_Alias = amoString.split(":");
        String amoNombreUnico = amoNombreUnico_Alias[0].strip();
        String amoAlias = null;
        if (amoNombreUnico_Alias.length > 1) {
            amoAlias = amoNombreUnico_Alias[1].strip();
        }

        Arbol<Amo> arbolAmo = null;
        if (amoAlias != null && !amoAlias.isEmpty()) {
            // Buscamos en el hashTable por Alias
            arbolAmo = this.controladorApp.getHTAlias().buscar(amoAlias);
        }
        if (arbolAmo == null && amoNombreUnico != null && !amoNombreUnico.isEmpty()) {
            // Buscamos en el hashTable por Nombre Unico
            arbolAmo = this.controladorApp.gethTNombreUnico().buscar(amoNombreUnico);
        }
        if (arbolAmo == null) {
            JOptionPane.showMessageDialog(this,
                    "No se consiguió el amo: " + amoString + " por Alias o por Nombre Único!");
            return null;
        }
        AmoInterface amoInterface = new AmoInterface(this, arbolAmo.obtenerValor());
        amoInterface.setVisible(true);
        return arbolAmo;
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
        jPanelManejoApp = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListTitle = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jButtonTitle = new javax.swing.JButton();
        jTextTitle = new javax.swing.JTextField();
        jSlider1 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jSpinnerGen = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jButtonGen = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaGen = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListNames = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jTextName = new javax.swing.JTextField();
        jButtonName = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButtonAncestors = new javax.swing.JButton();
        jPanelInterfaceTitle = new javax.swing.JPanel();
        jLabelTitleMainInterface = new javax.swing.JLabel();
        jButtonForReset = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuFileChooser1 = new javax.swing.JMenuItem();
        jMenu = new javax.swing.JMenu();
        jMenuExit = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanelArbol.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));

        javax.swing.GroupLayout jPanelArbolLayout = new javax.swing.GroupLayout(jPanelArbol);
        jPanelArbol.setLayout(jPanelArbolLayout);
        jPanelArbolLayout.setHorizontalGroup(
            jPanelArbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanelArbolLayout.setVerticalGroup(
            jPanelArbolLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jPanelManejoApp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jListTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListTitleMouseClicked(evt);
            }
        });
        jListTitle.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListTitleValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListTitle);

        jLabel1.setText("TITLE:");

        jButtonTitle.setText("Search");
        jButtonTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTitleActionPerformed(evt);
            }
        });

        jTextTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextTitleActionPerformed(evt);
            }
        });

        jLabel2.setText("Music Volume:");

        jLabel4.setText("Gen Number:");

        jButtonGen.setText("Search");
        jButtonGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenActionPerformed(evt);
            }
        });

        jTextAreaGen.setColumns(20);
        jTextAreaGen.setRows(5);
        jScrollPane4.setViewportView(jTextAreaGen);

        javax.swing.GroupLayout jPanelManejoAppLayout = new javax.swing.GroupLayout(jPanelManejoApp);
        jPanelManejoApp.setLayout(jPanelManejoAppLayout);
        jPanelManejoAppLayout.setHorizontalGroup(
            jPanelManejoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelManejoAppLayout.createSequentialGroup()
                .addGroup(jPanelManejoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelManejoAppLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelManejoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelManejoAppLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonTitle))
                            .addGroup(jPanelManejoAppLayout.createSequentialGroup()
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanelManejoAppLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelManejoAppLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGen))
                    .addGroup(jPanelManejoAppLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        jPanelManejoAppLayout.setVerticalGroup(
            jPanelManejoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelManejoAppLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelManejoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButtonTitle)
                    .addComponent(jTextTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelManejoAppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jSpinnerGen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonGen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jListNames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListNamesMouseClicked(evt);
            }
        });
        jListNames.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListNamesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListNames);

        jLabel3.setText("Name:");

        jTextName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextNameActionPerformed(evt);
            }
        });

        jButtonName.setText("Search");
        jButtonName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNameActionPerformed(evt);
            }
        });

        jLabel5.setText("If you want to see the ancestors select ONE name:");

        jButtonAncestors.setText("Ancestors");
        jButtonAncestors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAncestorsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonName))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jButtonAncestors))
                        .addGap(0, 16, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonName))
                .addGap(80, 80, 80)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAncestors)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelInterfaceTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabelTitleMainInterface.setFont(new java.awt.Font("VALORANT", 1, 36)); // NOI18N
        jLabelTitleMainInterface.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitleMainInterface.setText("Gen Tree");
        jLabelTitleMainInterface.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.black, java.awt.Color.black));

        javax.swing.GroupLayout jPanelInterfaceTitleLayout = new javax.swing.GroupLayout(jPanelInterfaceTitle);
        jPanelInterfaceTitle.setLayout(jPanelInterfaceTitleLayout);
        jPanelInterfaceTitleLayout.setHorizontalGroup(
            jPanelInterfaceTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInterfaceTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTitleMainInterface, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelInterfaceTitleLayout.setVerticalGroup(
            jPanelInterfaceTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitleMainInterface, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        jButtonForReset.setText("Reset");
        jButtonForReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonForResetActionPerformed(evt);
            }
        });

        jMenu2.setText("File");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuFileChooser1.setText("Select File");
        jMenuFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuFileChooser1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuFileChooser1);

        jMenuBar1.add(jMenu2);

        jMenu.setText("Exit");
        jMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActionPerformed(evt);
            }
        });

        jMenuExit.setText("Exit App");
        jMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuExitActionPerformed(evt);
            }
        });
        jMenu.add(jMenuExit);

        jMenuBar1.add(jMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelManejoApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jButtonForReset, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanelInterfaceTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelArbol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(484, 484, 484))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelManejoApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelInterfaceTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelArbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonForReset, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTitleActionPerformed
        String[] arregloVacio = new String[0];
        this.jListTitle.clearSelection();
        this.jListTitle.setListData(arregloVacio);

        if (this.jTextTitle.getText().isEmpty()) {
            return;
        }

        Amo amo = new Amo();
        amo.title = this.jTextTitle.getText();

        this.controladorApp.arbolCasa.actualizarComparador(Amo.comparadorTitleComienzaPor);
        ListaEnlazada<A_Arbol<Amo>> byTitle = this.controladorApp.arbolCasa.buscar(amo);

        String[] arreglo = new String[byTitle.size()];
        for (int i = 0; i < byTitle.size(); i++) {
            String nombre = byTitle.obtener(i).obtenerValor().nombreUnico;
            if (nombre == null) {
                nombre = "";
            }
            String nombreAlias = nombre;
            String alias = byTitle.obtener(i).obtenerValor().alias;
            if (alias != null) {
                nombreAlias += ": " + alias;
            }

            arreglo[i] = nombreAlias;
        }
        this.jListTitle.setListData(arreglo);
    }//GEN-LAST:event_jButtonTitleActionPerformed

    private void jTextTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextTitleActionPerformed

    private void jButtonGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenActionPerformed
        int numGen = (int) this.jSpinnerGen.getValue();
        ListaEnlazada<A_Arbol<Amo>> byGen = this.controladorApp.arbolCasa.obtenerLevel(numGen);
        this.jTextAreaGen.setText("");
        for (int i = 0; i < byGen.size(); i++) {
            Amo amo = byGen.obtener(i).obtenerValor();
            String nombreUnico = amo.nombreUnico;
            if (nombreUnico == null) {
                nombreUnico = "";
            }
            String nombreAlias = nombreUnico;
            String alias = amo.alias;
            if (alias != null) {
                nombreAlias += ": " + alias;
            }

            this.jTextAreaGen.append(nombreAlias + "\n");
        }    
    }//GEN-LAST:event_jButtonGenActionPerformed

    private void jButtonAncestorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAncestorsActionPerformed
        if (this.selectedLordByName == null) {
            return;
        }
        String[] amoNombreUnico_Alias = this.selectedLordByName.split(":");
        String amoNombreUnico = amoNombreUnico_Alias[0].strip();
        String amoAlias = null;
        if (amoNombreUnico_Alias.length > 1) {
            amoAlias = amoNombreUnico_Alias[1].strip();
        }

        Arbol<Amo> amoArbol = null;
        if (amoAlias != null && !amoAlias.isEmpty()) {
            // Buscamos en el hashTable por Alias
            amoArbol = this.controladorApp.getHTAlias().buscar(amoAlias);
        }
        if (amoArbol == null && amoNombreUnico != null && !amoNombreUnico.isEmpty()) {
            // Buscamos en el hashTable por Nombre Unico
            amoArbol = this.controladorApp.gethTNombreUnico().buscar(amoNombreUnico);
        }
        if (amoArbol == null) {
            JOptionPane.showMessageDialog(this,
                    "No se consiguió el amo: " + this.selectedLordByName + " por Alias o por Nombre Único!");
            return;
        }
        this.controladorApp.cargarAntepasadosGraph(amoArbol);
    }//GEN-LAST:event_jButtonAncestorsActionPerformed

    private void jButtonNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNameActionPerformed
        String[] arregloVacio = new String[0];
        this.jListNames.clearSelection();
        this.jListNames.setListData(arregloVacio);

        if (this.jTextName.getText().isEmpty()) {
            return;
        }

        Amo amo = new Amo();

        amo.nombreUnico = this.jTextName.getText();
        amo.alias = this.jTextName.getText();

        this.controladorApp.arbolCasa.actualizarComparador(Amo.comparadorNombreUnicoComienzaPor);

        ListaEnlazada<A_Arbol<Amo>> porNombre = this.controladorApp.arbolCasa.buscar(amo);

        this.controladorApp.arbolCasa.actualizarComparador(Amo.comparadorAliasComienzaPor);
        ListaEnlazada<A_Arbol<Amo>> porAlias = this.controladorApp.arbolCasa.buscar(amo);

        ListaEnlazada<A_Arbol<Amo>> porNombreAlias = new ListaEnlazada<A_Arbol<Amo>>();
        if (!porNombre.vacia()) {
            porNombreAlias = porNombre;
        }
        if (!porNombreAlias.vacia() && !porAlias.vacia()) {
            for (int i = 0; i < porAlias.size(); i++) {
                boolean encontrado = false;
                for (int j = 0; j < porNombreAlias.size(); j++) {
                    if (porAlias.obtener(i) == porNombreAlias.obtener(j)) {
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    porNombreAlias.agregar(porAlias.obtener(i));
                }
            }
        } else if (porNombreAlias.vacia() && !porAlias.vacia()) {
            porNombreAlias = porAlias;
        }

        String[] arreglo = new String[porNombreAlias.size()];
        for (int i = 0; i < porNombreAlias.size(); i++) {
            String nombre = porNombreAlias.obtener(i).obtenerValor().nombreUnico;
            if (nombre == null) {
                nombre = "";
            }
            String nombreAlias = nombre;
            String alias = porNombreAlias.obtener(i).obtenerValor().alias;
            if (alias != null) {
                nombreAlias += ": " + alias;
            }

            arreglo[i] = nombreAlias;
        }
        this.jListNames.setListData(arreglo);
    }//GEN-LAST:event_jButtonNameActionPerformed

    private void jTextNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextNameActionPerformed

    private void jButtonForResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonForResetActionPerformed
        this.reloadWindow();
    }//GEN-LAST:event_jButtonForResetActionPerformed

    private void jListNamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListNamesMouseClicked
        if (this.selectedLordByName == null || this.selectedLordByName.isEmpty()) {
            return;
        }
        if (this.selectedLordByNameShowed) {
            this.selectedLordByNameShowed = false;
            return;
        }
        this.getAmoInterface(this.selectedLordByName);
        this.verificacionElemEnabled();
    }//GEN-LAST:event_jListNamesMouseClicked

    private void jListNamesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListNamesValueChanged
        String amo = jListNames.getSelectedValue();
        if (amo == null || amo.isEmpty()) {
            return;
        }
        if (this.selectedLordByName != null && this.selectedLordByName.equals(amo)) {
            if (this.selectedLordByNameShowed) {
                return;
            }
        }
        this.selectedLordByName = amo;
        this.selectedLordByNameShowed = true;
        Arbol<Amo> amoArbol = this.getAmoInterface(this.selectedLordByName);
        this.controladorApp.cargarArbolGraph((A_Arbol<Amo>) amoArbol);
        this.verificacionElemEnabled();
    }//GEN-LAST:event_jListNamesValueChanged

    private void jListTitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListTitleMouseClicked
        if (this.selectedLordByTitle == null || this.selectedLordByTitle.isEmpty()) {
            return;
        }
        if (this.selectedLordByTitleShowed) {
            this.selectedLordByTitleShowed = false;
            return;
        }
        this.getAmoInterface(this.selectedLordByTitle);
        this.verificacionElemEnabled();
    }//GEN-LAST:event_jListTitleMouseClicked

    private void jListTitleValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListTitleValueChanged
        String amo = jListTitle.getSelectedValue();
        if (amo == null || amo.isEmpty()) {
            return;
        }
        if (this.selectedLordByTitle != null && this.selectedLordByTitle.equals(amo)) {
            if (this.selectedLordByTitleShowed) {
                return;
            }
        }
        this.selectedLordByTitle = amo;
        this.selectedLordByTitleShowed = true;
        this.getAmoInterface(this.selectedLordByTitle);
        this.verificacionElemEnabled();
    }//GEN-LAST:event_jListTitleValueChanged

    private void jMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActionPerformed

    }//GEN-LAST:event_jMenuActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed

    }//GEN-LAST:event_jMenu2ActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        this.verificacionElemEnabled();
        this.configSpinner();
    }//GEN-LAST:event_formWindowGainedFocus

    private void jMenuFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuFileChooser1ActionPerformed
        this.reloadWindow();
        CargarArchivo cargarArchivo = new CargarArchivo(mainClass, this);
        cargarArchivo.setVisible(true);
    }//GEN-LAST:event_jMenuFileChooser1ActionPerformed

    private void jMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuExitActionPerformed

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
    private javax.swing.JButton jButtonAncestors;
    private javax.swing.JButton jButtonForReset;
    private javax.swing.JButton jButtonGen;
    private javax.swing.JButton jButtonName;
    private javax.swing.JButton jButtonTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelTitleMainInterface;
    private javax.swing.JList<String> jListNames;
    private javax.swing.JList<String> jListTitle;
    private javax.swing.JMenu jMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuExit;
    private javax.swing.JMenuItem jMenuFileChooser1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelArbol;
    private javax.swing.JPanel jPanelInterfaceTitle;
    private javax.swing.JPanel jPanelManejoApp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSpinner jSpinnerGen;
    private javax.swing.JTextArea jTextAreaGen;
    private javax.swing.JTextField jTextName;
    private javax.swing.JTextField jTextTitle;
    // End of variables declaration//GEN-END:variables
}
