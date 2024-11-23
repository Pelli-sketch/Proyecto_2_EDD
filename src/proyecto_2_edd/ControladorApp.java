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

public class ControladorApp {
    
    public static final int GRAPH_WIDTH = 1000;
    
    Arbol<Amo> arbolCasa;
    LectorJson lectorJson = new LectorJson();
    Casa casa;
    HashTable<Arbol<Amo>> hashTableUniqueName;
    HashTable<Arbol<Amo>> hashTableAlias;
    
    Graph graph;
    
    public static final int GRAPH_HEIGHT = 1000;
    
    public ControladorApp() {
        this.graph = new SingleGraph("Gene Tree of the house");
        this.casa = new Casa();
        this.arbolCasa = null;
    }
    
    public Casa getCasa() {
        return this.casa;
    }
    
    public HashTable<Arbol<Amo>> getHashTableUniqueName() {
        return this.hashTableUniqueName;
    }
    public HashTable<Arbol<Amo>> getHashTableAlias() {
        return this.hashTableAlias;
    }
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
            if (amo.uniqueName != null) {
                this.hashTableUniqueName.insertar(amo.uniqueName, amoArbol);
            }    
            if (i == 0) {
                this.arbolCasa = amoArbol;
                continue;
            }

            // Creamos un amo padre para poder hacer las búsquedas.
            Amo amoPadre = new Amo();
            amoPadre.uniqueName = amo.father;
            amoPadre.fullName = amo.father;
            amoPadre.alias = amo.father;

            // Buscamos por el alias a ver si es el que necesitamos
            this.arbolCasa.setComparador(Amo.comparadorAlias);
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
            this.arbolCasa.setComparador(Amo.comparadorNombreUnico);
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
            this.arbolCasa.setComparador(Amo.comparadorNombreFull);
            padres = this.arbolCasa.buscar(amoPadre);
            if (padres.size() > 1) {
                throw new RuntimeException("Error en la carga del árbol, hay mas de un padre de " + amo.name);
            }
            if (padres.size() == 1) {
                // Si se encontró lo agregamos como hizo del padre encontrado.
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

        // Si el padre es null, limpiamos el gráfico porque es la raiz.
        if (fatherNodeId == null) {
            this.graph.clear();
        }

        // vamos a crear un id para el nodo en graph stream, con el cual luego sea
        // posible hacer la búsqueda en el hash ya sea por nombre único o por alias
        // usamos el formato: uniqueName:alias. Así cuando tenga un nodo hago un
        // split por el ":" y luego lo busco en el hashTable correspondiente.
        String uniqueName = amo.obtenerValor().uniqueName;
        if (uniqueName == null) {
            uniqueName = "";
        }
        String alias = amo.obtenerValor().alias;
        if (alias == null) {
            alias = "";
        }
        String name = amo.obtenerValor().name;
        if (name == null) {
            name = "";
        }
        String nodeId = uniqueName + ":" + alias;

        // asigno el nodeId para crear el nodo y le asigno como etiqueta el nombre,
        // ya que es más corto y permite ver mejor a los nodos del árbol en su
        // representación en graphStream.
        Node node = this.graph.addNode(nodeId);
        node.setAttribute("ui.label", name);

        // Le asigno su posición dentro de la cuadricula que se creó para que el grafo
        // tenga forma de árbol genealógico.
        node.setAttribute("xy", x, y);

        // Se crea la arista y se le da un id representativo, aunque en este proyecto
        // el id de la arista no se usa.
        if (fatherNodeId != null) {
            this.graph.addEdge(fatherNodeId + "->" + nodeId, fatherNodeId, nodeId);
        }

        // Obtengo los hijos del subarbol que estoy recorriendo.
        ListaEnlazada<A_Arbol<Amo>> hijos = amo.obtenerHijos();

        // Si no tiene hijos, se termina la recursividad.
        if (hijos.vacia()) {
            return;
        }

        // Se calcula el nodo mas a la izquierda para que todos queden centrados con
        // respecto a su padre. Llamamos a este valor xOffset. Como va a la izquierda
        // en el plano cartesiano, el signo es negativo.
        int xOffset = -1 * ((int) hijos.size() / 2) * xStepSize;
        x = x + xOffset;
        for (int i = 0; i < hijos.size(); i++) {
            // a cada hijo se lo llama recursivamente con un y al que se le resta yStepSize,
            // para que este por debajo de su padre, y el x calculado sumándole al x el
            // xStepSize, así cada hijo se va a ir moviendo hacia la derecha y todos
            // centrados con respecto a su padre.
            this.cargarArbolGraph(hijos.obtener(i), nodeId, x, y - yStepSize, xStepSize, yStepSize);
            x += xStepSize;
        }
    }
    public void cargarArbolGraph(A_Arbol<Amo> amo) {
        // Obtengo los levels del árbol, para poder calcular los xStepSize y yStepSize.
        ListaEnlazada<A_Arbol<Amo>>[] levels = amo.obtenerLevels();
        int numLevels = levels.length;

        // Calculo los xStepSize y yStepSize
        int yStepSize = ControladorApp.GRAPH_HEIGHT / numLevels;
        int maxNumGen = 0;
        // el mayor número de hijos de todos los levels
        // para calcular el xStepSize
        for (int i = 0; i < levels.length; i++) {
            if (levels[i].size() > maxNumGen) {
                maxNumGen = levels[i].size();
            }
        }
        int xStepSize = ControladorApp.GRAPH_WIDTH / maxNumGen;

        // ahora hago la llamada recursiva para cargar el grafo de graphStream.
        this.cargarArbolGraph(amo, null, 0, 0, xStepSize, yStepSize);

        // Y le doy estilo a los nodos y aristas
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

        // necesitamos un arreglo de ids de nodos para los antepasados
        // al igual que con el cargarArbolGraph, los ids serán uniqueName:alias
        // ya que me permite poder hacer luego busquedas en el hashTable
        // ya sea por uniqueName o por alias haciendo solamente un split
        // de uniqueName:alias por el ":".

        String[] nodeIds = new String[antepasados.size()];
        int numAntepasados = antepasados.size();

        // Calculamos el yStepSize para que los nodos estén equidistantes.
        int yStepSize = ControladorApp.GRAPH_HEIGHT / numAntepasados;
        for (int i = 0, y = 0; i < antepasados.size(); i++, y -= yStepSize) {

            // calculo los nodeIds
            String uniqueName = antepasados.obtener(i).obtenerValor().uniqueName;
            if (uniqueName == null) {
                uniqueName = "";
            }
            String alias = antepasados.obtener(i).obtenerValor().alias;
            if (alias == null) {
                alias = "";
            }
            String name = antepasados.obtener(i).obtenerValor().name;
            if (name == null) {
                name = "";
            }
            String nodeId = uniqueName + ":" + alias;
            nodeIds[i] = nodeId;

            // creo el nodo con el nodeId
            Node node = this.graph.addNode(nodeId);

            // pero la etiqueta (label) lo hago con el name
            node.setAttribute("ui.label", name);
            // y le doy su ubicación en el gráfico, ver que el for, decrementa
            // el valor de y en yStepSize en cada iteración, de esta forma los hijos
            // están por debajo de los padres
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
            txt += "nodo: " + this.graph.getNode(i).toString() + "\n";
        }
        for (int i = 0; i < this.graph.getEdgeCount(); i++) {
            txt += "Arista: " + this.graph.getEdge(i).getId().toString() + "\n";
        }
        return txt;
    }

    /**
     * Resetea el arbol y el grafo
     */

    public void reset() {
        this.casa.vaciarLista();
        if (this.arbolCasa != null) {
            this.arbolCasa.vaciar();
        }
        this.graph.clear();
    }    
}
