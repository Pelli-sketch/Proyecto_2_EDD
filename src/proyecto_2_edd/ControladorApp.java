/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 * Clase principal que controla la aplicación para cargar y visualizar estructuras 
 * de datos relacionadas con un árbol genealógico de una casa y sus amos.
 */
public class ControladorApp {

    
    public static final int GRAPH_WIDTH = 1000;
    
    Arbol<Amo> arbolCasa;
    LectorJson lectorJson = new LectorJson();
    Casa casa;
    HashTable<Arbol<Amo>> hTNombreUnico;
    HashTable<Arbol<Amo>> hTAlias;
    
    Graph graph;
    
    public static final int GRAPH_HEIGHT = 1000;
    /**
     * Constructor por defecto que inicializa los atributos principales.
     */    
    public ControladorApp() {
        this.graph = new SingleGraph("Gene Tree of the house");
        this.casa = new Casa();
        this.arbolCasa = null;
    }
    /**
     * Carga los datos de una casa desde un archivo JSON.
     * 
     * @param nombreArchivo el nombre del archivo JSON.
     * @return true si la carga fue exitosa, false en caso contrario.
     */    
    public boolean cargarCasa(String nombreArchivo) {
        String json = this.lectorJson.cargar(nombreArchivo);
        if (!this.casa.parse(json)) {
            return false;
        }
        int tam = this.casa.amos.size();
        this.hTNombreUnico = new HashTable<>(tam);
        this.hTAlias = new HashTable<>(tam);

        return true;
    }
    
    public Casa getCasa() {
        return this.casa;
    }
    /**
     * Obtiene la tabla hash para buscar por nombre único.
     * 
     * @return la tabla hash de nombres únicos.
     */    
    public HashTable<Arbol<Amo>> gethTNombreUnico() {
        return this.hTNombreUnico;
    }
    /**
     * Obtiene la tabla hash para buscar por alias.
     * 
     * @return la tabla hash de alias.
     */    
    public HashTable<Arbol<Amo>> getHTAlias() {
        return this.hTAlias;
    }
    /**
     * Construye el árbol genealógico y llena las tablas hash correspondientes.
     */    
    public void Cargar_Arbol_and_Hash() {
        for (int i = 0; i < this.casa.amos.size(); i++) {

            // el amo que vamos a ingresar al árbol y a las hashTables
            Amo amo = this.casa.amos.obtener(i);
            if (i != 0) {
                if (amo.father == null) {
                    throw new RuntimeException("Error en la carga del árbol, el padre de " + amo.name
                            + " es Unknown y no es el primero de la lista.");
                }
            }
            
            

            // creamos el sub-árbol del amo.
            Arbol<Amo> amoArbol = new Arbol<>(amo);

            // insertamos el sub-árbol del amo en las hashTables
            if (amo.nombreUnico != null) {
                this.hTNombreUnico.insertar(amo.nombreUnico, amoArbol);
            }    
            if (i == 0) {
                this.arbolCasa = amoArbol;
                continue;
            }

            // Creamos un amo padre para poder hacer las búsquedas.
            Amo amoPadre = new Amo();
            amoPadre.nombreUnico = amo.father;
            amoPadre.fullName = amo.father;
            amoPadre.alias = amo.father;

            // Buscamos por el alias a ver si es el que necesitamos
            this.arbolCasa.actualizarComparador(Amo.comparadorAlias);
            ListaEnlazada<A_Arbol<Amo>> padres = this.arbolCasa.buscar(amoPadre);
            if (padres.size() > 1) {
                throw new RuntimeException("Error en la carga del árbol, hay mas de un padre de " + amo.name);
            }
            if (padres.size() == 1) {
                // Si lo encontramos lo agregamos como hizo del padre encontrado.
                Arbol<Amo> padre = (Arbol<Amo>) padres.obtener(0);
                if (padre != null) {
                    padre.agregarHijo(amoArbol);
                    continue;
                }
            }

            // Buscamos por el nombre único a ver si es el que necesitamos
            this.arbolCasa.actualizarComparador(Amo.comparadorNombreUnico);
            padres = this.arbolCasa.buscar(amoPadre);
            if (padres.size() > 1) {
                throw new RuntimeException("Error en la carga del árbol, hay mas de un padre de " + amo.name);
            }
            if (padres.size() == 1) {
                // Si lo encontramos lo agregamos como hizo del padre encontrado.
                Arbol<Amo> padre = (Arbol<Amo>) padres.obtener(0);
                if (padre != null) {
                    padre.agregarHijo(amoArbol);
                    continue;
                }
            }

            // Buscamos por el nombre completo
            this.arbolCasa.actualizarComparador(Amo.comparadorNombreFull);
            padres = this.arbolCasa.buscar(amoPadre);
            if (padres.size() > 1) {
                throw new RuntimeException("Error en la carga del árbol, hay mas de un padre de " + amo.name);
            }
            if (padres.size() == 1) {
                Arbol<Amo> padre = (Arbol<Amo>) padres.obtener(0);
                if (padre != null) {
                    padre.agregarHijo(amoArbol);
                    continue;
                }
            }

            // Si llegó aquí, no se encontró el padre.
            throw new RuntimeException("Error en la carga del árbol, el padre de " + amo.name
                    + " no se encuentra en el árbol.");
        }
    }
    
    private void cargarArbolGraph(A_Arbol<Amo> amo, String fatherNodeId, int x, int y,
            int xStepSize,
            int yStepSize) {

        if (fatherNodeId == null) {
            this.graph.clear();
        }

        String nombreUnico = amo.obtenerValor().nombreUnico;
        if (nombreUnico == null) {
            nombreUnico = "";
        }
        String alias = amo.obtenerValor().alias;
        if (alias == null) {
            alias = "";
        }
        String name = amo.obtenerValor().name;
        if (name == null) {
            name = "";
        }
        String nodeId = nombreUnico + ":" + alias;
        Node node = this.graph.addNode(nodeId);
        node.setAttribute("ui.label", name);
        node.setAttribute("xy", x, y);

        if (fatherNodeId != null) {
            this.graph.addEdge(fatherNodeId + "->" + nodeId, fatherNodeId, nodeId);
        }

        ListaEnlazada<A_Arbol<Amo>> hijos = amo.obtenerHijos();

        if (hijos.vacia()) {
            return;
        }
        int xOffset = -1 * ((int) hijos.size() / 2) * xStepSize;
        x = x + xOffset;
        for (int i = 0; i < hijos.size(); i++) {
            this.cargarArbolGraph(hijos.obtener(i), nodeId, x, y - yStepSize, xStepSize, yStepSize);
            x += xStepSize;
        }
    }
    public void cargarArbolGraph(A_Arbol<Amo> amo) {
        ListaEnlazada<A_Arbol<Amo>>[] levels = amo.obtenerLevels();
        int numLevels = levels.length;
        int yStepSize = ControladorApp.GRAPH_HEIGHT / numLevels;
        int maxNumGen = 0;
        
        if (amo == null) {
            throw new IllegalArgumentException("El árbol proporcionado (amo) es null.");//Verifica la carga de datos.
        }

        for (int i = 0; i < levels.length; i++) {
            if (levels[i].size() > maxNumGen) {
                maxNumGen = levels[i].size();
            }
        }
        int xStepSize = ControladorApp.GRAPH_WIDTH / maxNumGen;


        this.cargarArbolGraph(amo, null, 0, 0, xStepSize, yStepSize);


        String css = "node {" +
                " text-size: 16px;" + // Tamaño del texto
                " text-font: Papyrus;" + // Define la fuente como Papyrus
                " text-style: bold;" + // Texto en negrita
                " text-alignment: center;" + // Texto centrado
                " text-background-mode: plain;" + // Activa el fondo de texto
                " text-background-color: #FFFFE0;" + // Color de fondo del texto (un tono claro de amarillo)
                " text-padding: 5px;" + // Relleno alrededor del texto

                " fill-color: #d99058;" + // Color de relleno del nodo (un tono más oscuro de amarillo)
                " stroke-mode: plain;" + // Activa el borde del nodo
                " stroke-color: black;" + // Color del borde
                " shape: box;" + // Forma de los nodos como caja
                " size-mode: fit;" + // Ajuste automático del tamaño del nodo
                "}" +
                "edge {" +
                " shape: cubic-curve;" + // Forma de las aristas como curva cubica
                " fill-color: gray;" + // Color de relleno de las aristas
                " stroke-mode: plain;" + // Activa el borde de las aristas
                // " stroke-width: 3px;" + // Ancho del borde de las aristas
                "}";
        this.graph.setAttribute("ui.stylesheet", css);
        this.graph.setAttribute("ui.antialias", true);
        this.graph.setAttribute("ui.quality", true);
    }
    public void cargarAntepasadosGraph(A_Arbol<Amo> amo) {

        // Limpiamos el gráfico
        this.graph.clear();
        // obtenemos todos los antepasados del Amo
        ListaEnlazada<A_Arbol<Amo>> antepasados = this.arbolCasa.obtenerAscends(amo);
        if (antepasados.vacia()) {
            return;
        }


        String[] nodeIds = new String[antepasados.size()];
        int numAntepasados = antepasados.size();

        int yStepSize = ControladorApp.GRAPH_HEIGHT / numAntepasados;
        for (int i = 0, y = 0; i < antepasados.size(); i++, y -= yStepSize) {
            String nombreUnico = antepasados.obtener(i).obtenerValor().nombreUnico;
            if (nombreUnico == null) {
                nombreUnico = "";
            }
            String alias = antepasados.obtener(i).obtenerValor().alias;
            if (alias == null) {
                alias = "";
            }
            String name = antepasados.obtener(i).obtenerValor().name;
            if (name == null) {
                name = "";
            }
            String nodeId = nombreUnico + ":" + alias;
            nodeIds[i] = nodeId;

            // creo el nodo con el nodeId
            Node node = this.graph.addNode(nodeId);

            node.setAttribute("ui.label", name);

            node.setAttribute("xy", 0, y);
        }
        for (int i = 0; i < nodeIds.length - 1; i++) {
            // ahora uno con una arista a cada padre con su hijo.
            String fatherNodeId = nodeIds[i];
            String nodeId = nodeIds[i + 1];
            this.graph.addEdge(fatherNodeId + "->" + nodeId, fatherNodeId, nodeId);
        }

        // le damos el estilo al gráfico
        String css = "node {" +
                " text-size: 16px;" + // Tamaño del texto
                " text-font: Papyrus;" + // Define la fuente como Papyrus
                " text-style: bold;" + // Texto en negrita
                " text-alignment: center;" + // Texto centrado
                " text-background-mode: plain;" + // Activa el fondo de texto
                " text-background-color: #FFFFE0;" + // Color de fondo del texto (un tono claro de amarillo)
                " text-padding: 5px;" + // Relleno alrededor del texto

                " fill-color: #F0E68C;" + // Color de relleno del nodo (un tono más oscuro de amarillo)
                " stroke-mode: plain;" + // Activa el borde del nodo
                " stroke-color: black;" + // Color del borde
                " shape: box;" + // Forma de los nodos como caja
                " size-mode: fit;" + // Ajuste automático del tamaño del nodo
                "}" +
                "edge {" +
                " shape: cubic-curve;" + // Forma de las aristas como curva cubica
                " fill-color: gray;" + // Color de relleno de las aristas
                " stroke-mode: plain;" + // Activa el borde de las aristas
                // " stroke-width: 3px;" + // Ancho del borde de las aristas
                "}";
        this.graph.setAttribute("ui.stylesheet", css);
        this.graph.setAttribute("ui.antialias", true);
        this.graph.setAttribute("ui.quality", true);

    }
    public String grafoToString() {
        String txt = "Grafo: " + this.graph.toString() + "\n";
        for (int i = 0; i < this.graph.getNodeCount(); i++) {
            txt += "node: " + this.graph.getNode(i).toString() + "\n";
        }
        for (int i = 0; i < this.graph.getEdgeCount(); i++) {
            txt += "Arist: " + this.graph.getEdge(i).getId().toString() + "\n";
        }
        return txt;
    }

    /**
     * Resetea el arbol y el grafo
     */

    public void reiniciar() {
        this.casa.vaciarLista();
        if (this.arbolCasa != null) {
            this.arbolCasa.vaciar();
        }
        this.graph.clear();
    }
    public static void main(String[] args) {

    }    
}
