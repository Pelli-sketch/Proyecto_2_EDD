class Nodo {
    String nombre;
    Nodo[] hijo; 

    public Nodo(String nombre, int numHijo) {
    this.nombre = nombre; 
    this.hijo = new Nodo[numHijo];
    }
}

class arbol {
    Nodo raiz;

    public arbol(String nombreRaiz, int numHijo){
        this.raiz = new Nodo(nombreRaiz, numHijo);
    }
}


