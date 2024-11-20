/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_2_edd;

/**
 * Clase Grafo, para representar un grafo. En el caso del proyecto los vertices
 * son hijos o dependientes de un cierto linaje.
 */
public class Grafo {
    private final int MAX_NUM_SOLUCIONES = 1;
    /**
     * La lista de vertices
     */
    private ArrayList<Vertice> vertices;

    /**
     * La lista de sucursales
     */
    private ArrayList<String> dependientes;

    /**
     * El tipo de búsqueda a utilizar, puede ser BFS (por amplitud) o DFS (por
     * profundidad) @see TipoBusqueda
     */
//    private TipoBusqueda tipoBusqueda;

    /**
     * Constructor de la clase
     */
    public Grafo() {
        this.vertices = new ArrayList<>();
        this.dependientes = new ArrayList<>();
//        this.t = 0;
//        this.tipoBusqueda = TipoBusqueda.BFS;
    }

}
