package proyecto_2_edd;

class NodoArbol {

    String nombre;
    NodoArbol[] hijo;
    NodoArbol padre;
    String madre; 

    public NodoArbol(String nombre, int numHijo) {
        this.nombre = nombre;
        this.hijo = new NodoArbol[numHijo];
        this.padre = null;
        this.madre = madre;
    }
}

public class Arbol {

    NodoArbol raiz;

    public Arbol(String nombreRaiz, int numHijo) {
        this.raiz = new NodoArbol(nombreRaiz, numHijo);
    }
}
