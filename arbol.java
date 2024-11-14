class NodoArbol {
    String nombre;
    NodoArbol[] hijo; 

    public NodoArbol(String nombre, int numHijo) {
    this.nombre = nombre; 
    this.hijo = new NodoArbol[numHijo];
    }
}

class Arbol {
    NodoArbol raiz;

    public Arbol(String nombreRaiz, int numHijo){
        this.raiz = new NodoArbol(nombreRaiz, numHijo);
    }
}



