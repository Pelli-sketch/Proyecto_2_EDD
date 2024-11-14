package proyecto_2_edd;

class NodoArbol {
    String nombre;
    NodoArbol[] hijo; 

    public NodoArbol(String nombre, int numHijo) {
    this.nombre = nombre; 
    this.hijo = new NodoArbol[numHijo];
    }
}

public class Arbol {
    NodoArbol raiz;

    public Arbol(String nombreRaiz, int numHijo){
        this.raiz = new NodoArbol(nombreRaiz, numHijo);
    }
}



