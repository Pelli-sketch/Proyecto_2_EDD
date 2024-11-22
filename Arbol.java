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

public class Arbol<T> implements A_Arbol<T> {
    
    private static AComparador<String> comparadorString = (String a, String b) -> a.compareTo(b);
    
    private static AComparador<Number> comparadorNumber = (Number a, Number b) -> {
        Double num1 = a.doubleValue();
        Double num2 = b.doubleValue();
        return num1.compareTo(num2);    
    };

    private T valor;
    
    private ListaEnlazada<A_Arbol<T>> hijos;
    
    private AComparador<T> comparador;
    
    @SuppressWarnings("unchecked")
    private void guessComparador() {
        if (comparador != null) {
            return;
        }
        if (this.valor == null) {
            return;
        }
        if (this.valor instanceof String) {
            this.comparador = (AComparador<T>) comparadorString;
            return;
        }
        if (this.valor instanceof Number) {
            this.comparador = (AComparador<T>) comparadorNumber;
            return;
        }
    }
        

    NodoArbol raiz;

    public Arbol(String nombreRaiz, int numHijo) {
        this.raiz = new NodoArbol(nombreRaiz, numHijo);
    }
    
    public Arbol() {
        this.hijos = new ListaEnlazada<>();
    }
    
    public Arbol(T valor) {
        this.valor = valor;
        this.hijos = new ListaEnlazada<>();
        this.guessComparador();
    }
    
    @Override
    public boolean vacio() {
        if (this.valor == null && this.hijos.vacia()) {
            return true;
        }
        return false;
    }
    @Override
    public T getValor() {
        return this.valor;
    }
    
    @Override
    public void setValor(T valor) {
        this.valor = valor;
        this.guessComparador();
    }
    
    @Override
    public Arbol<T> getRaiz() {
        return this;
    }
    
    @Override
    public int numElementos() {
        if (this.hijos.vacia()) {
            if (this.vacio()) {
                return 0;
            }
            return 1;
        }
        int sumElementos = 0;
        for (int i = 0; i < this.hijos.size(); i++) {
            sumElementos += this.hijos.get(i).numElementos();
        }
        if (this.valor == null) {
            return sumElementos;
        }
        return sumElementos + 1;
    }
    
    @Override
    public boolean agregarHijo(A_Arbol<T> hijo) {
        if (hijo == null) {
            return false;
        }
        return this.hijos.agregar(hijo);
    }
    @Override
    public ListaEnlazada<A_Arbol<T>> getHijos() {
        return this.hijos;
    }
    @Override
    public Arbol<T> getHijo(int i) {
        return (Arbol<T>) this.hijos.get(i);
    }
    @Override
    public void setComparador(AComparador<T> comparador) {
        this.comparador = comparador;
    }
    @Override
    public ListaEnlazada<A_Arbol<T>> buscarHijo(T valor) {
        if (this.comparador == null) {
            throw new RuntimeException("No se ha definido un comparador");
        }
        ListaEnlazada<A_Arbol<T>> encontrados = new ListaEnlazada<>();
        for (int i = 0; i < this.hijos.size(); i++) {
            // this.hijos.get(i).setComparador(this.comparador);
            if (this.comparador.compareTo(this.hijos.get(i).getValor(), valor) == 0) {
                encontrados.agregar(this.hijos.get(i));
            }
        }
        return encontrados;
    }
    @Override
    public ListaEnlazada<A_Arbol<T>> buscar(T valor) {
        if (this.comparador == null) {
            throw new RuntimeException("No se ha definido un comparador");
        }
        ListaEnlazada<A_Arbol<T>> encontrados = new ListaEnlazada<>();
        ListaEnlazada<A_Arbol<T>> cola = new ListaEnlazada<>();
        if (this.vacio()) {
            return encontrados;
        }
        cola.agregar(this);
        while (!cola.vacia()) {
            A_Arbol<T> arbol = cola.remover(0);
            if (this.comparador.compareTo(arbol.getValor(), valor) == 0) {
                encontrados.agregar(arbol);
            }
            ListaEnlazada<A_Arbol<T>> hijos = arbol.getHijos();
            for (int i = 0; i < hijos.size(); i++) {
                cola.agregar(hijos.get(i));
            }
        }
        return encontrados;
    }
    @Override
    public int altura() {
        if (this.hijos.size() == 0) {
            return 1;
        }
        int maxAltura = 0;
        for (int i = 0; i < this.hijos.size(); i++) {
            int alturaHijo = this.hijos.get(i).altura();
            if (alturaHijo > maxAltura) {
                maxAltura = alturaHijo;
            }
        }
        return maxAltura + 1;
    }
    private void getNiveles(int nivel, ListaEnlazada<A_Arbol<T>>[] niveles) {
        niveles[nivel].agregar(this);
        for (int i = 0; i < this.hijos.size(); i++) {
            Arbol<T> hijo = (Arbol<T>) this.hijos.get(i);
            hijo.getNiveles(nivel + 1, niveles);
        }
    }    
    @SuppressWarnings("unchecked")
    @Override
    public ListaEnlazada<A_Arbol<T>>[] getNiveles() {
        ListaEnlazada<A_Arbol<T>>[] niveles = new ListaEnlazada[this.altura()];
        for (int i = 0; i < niveles.length; i++) {
            niveles[i] = new ListaEnlazada<>();
        }
        this.getNiveles(0, niveles); // Llamada recursiva
        return niveles;
    }
    private void getNivel(int nivelActual, ListaEnlazada<A_Arbol<T>> elementosNivel, int nivelDeseado) {
        if (nivelActual == nivelDeseado) {
            elementosNivel.agregar(this);
            return;
        }
        for (int i = 0; i < this.hijos.size(); i++) {
            Arbol<T> hijo = (Arbol<T>) this.hijos.get(i);
            hijo.getNivel(nivelActual + 1, elementosNivel, nivelDeseado);
        }
    }
    @Override
    public ListaEnlazada<A_Arbol<T>> getNivel(int nivel) {
        if (nivel < 0 || nivel >= this.altura()) {
            return null;
        }
        ListaEnlazada<A_Arbol<T>> elementosNivel = new ListaEnlazada<>();
        this.getNivel(0, elementosNivel, nivel); // Llamada recursiva
        return elementosNivel;
    }
    @Override
    public ListaEnlazada<A_Arbol<T>> getAscendentes(A_Arbol<T> arbol) {
        if (arbol == null) {
            return null;
        }
        if (this == arbol) {
            return new ListaEnlazada<A_Arbol<T>>();
        }
        if (this.hijos.vacia()) {
            return null;
        }
        for (int i = 0; i < this.hijos.size(); i++) {
            ListaEnlazada<A_Arbol<T>> ascendentes = this.hijos.get(i).getAscendentes(arbol);
            if (ascendentes == null) {
                continue;
            }
            ascendentes.insertar(0, this);
            return ascendentes;
        }
        return null;
    }
    @Override
    public ListaEnlazada<A_Arbol<T>> getDescendientes(A_Arbol<T> arbol) {
        if (arbol == null) {
            return null;
        }
        ListaEnlazada<A_Arbol<T>> descendientes = new ListaEnlazada<>();
        ListaEnlazada<A_Arbol<T>>[] niveles = arbol.getNiveles();
        if (niveles.length <= 1) {
            return descendientes;
        }
        for (int i = 1; i < niveles.length; i++) {
            for (int j = 0; j < niveles[i].size(); j++) {
                descendientes.agregar(niveles[i].get(j));
            }
        }
        return descendientes;
    }
    @Override
    public int getNumNiveL(A_Arbol<T> arbol) {
        if (arbol == null) {
            return -1;
        }
        ListaEnlazada<A_Arbol<T>> ascendentes = this.getAscendentes(arbol);
        if (ascendentes == null) {
            return -1;
        }
        return ascendentes.size();
    }
    @Override
    public A_Arbol<T> getPadre(A_Arbol<T> arbol) {
        if (arbol == null) {
            return null;
        }
        ListaEnlazada<A_Arbol<T>> ascendentes = this.getAscendentes(arbol);
        if (ascendentes == null) {
            return null;
        }
        if (ascendentes.vacia()) {
            return null;
        }
        return ascendentes.get(ascendentes.size() - 1);
    }
    @Override
    public String toString() {
        String txt = "";
        if (this.valor == null) {
            txt += "//";
        } else {
            txt += this.valor.toString();
        }
        txt += ": [";
        for (int i = 0; i < this.hijos.size(); i++) {
            txt += this.hijos.get(i).getValor().toString();
            if (i < this.hijos.size() - 1) {
                txt += ", ";
            }
        }
        txt += "]";
        return txt;
    }
    private void vaciar(int nivel) {
        this.valor = null;
        for (int i = 0; i < this.hijos.size(); i++) {
            Arbol<T> hijo = (Arbol<T>) this.hijos.get(i);
            hijo.vaciar(nivel + 1);
        }
        if (nivel > 0) {
            this.hijos = null;
        }
        this.hijos = new ListaEnlazada<>();
    }
    @Override
    public void vaciar() {
        this.vaciar(0);
    }
    @Override
    public String nivelesToString() {
        String txt = "";
        ListaEnlazada<A_Arbol<T>>[] niveles = this.getNiveles();
        for (int i = 0; i < niveles.length; i++) {
            for (int j = 0; j < niveles[i].size(); j++) {
                txt += niveles[i].get(j).toString();
                if (j < niveles[i].size() - 1) {
                    txt += "\t";
                }
            }
            txt += "\n";
        }
        return txt;
    }
}
