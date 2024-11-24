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
    public T obtenerValor() {
        return this.valor;
    }
    
    @Override
    public void actualizarValor(T valor) {
        this.valor = valor;
        this.guessComparador();
    }
    
    @Override
    public Arbol<T> obtenerRaiz() {
        return this;
    }
    
    @Override
    public int numElements() {
        if (this.hijos.vacia()) {
            if (this.vacio()) {
                return 0;
            }
            return 1;
        }
        int sumElementos = 0;
        for (int i = 0; i < this.hijos.size(); i++) {
            sumElementos += this.hijos.obtener(i).numElements();
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
    public ListaEnlazada<A_Arbol<T>> obtenerHijos() {
        return this.hijos;
    }
    @Override
    public Arbol<T> obtenerHijo(int i) {
        return (Arbol<T>) this.hijos.obtener(i);
    }
    @Override
    public void actualizarComparador(AComparador<T> comparador) {
        this.comparador = comparador;
    }
    @Override
    public ListaEnlazada<A_Arbol<T>> buscarHijo(T valor) {
        if (this.comparador == null) {
            throw new RuntimeException("No se ha definido un comparador");
        }
        ListaEnlazada<A_Arbol<T>> encontrados = new ListaEnlazada<>();
        for (int i = 0; i < this.hijos.size(); i++) {
            // this.hijos.obtener(i).setComparador(this.comparador);
            if (this.comparador.compareTo(this.hijos.obtener(i).obtenerValor(), valor) == 0) {
                encontrados.agregar(this.hijos.obtener(i));
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
            A_Arbol<T> arbol = cola.eliminar(0);
            if (this.comparador.compareTo(arbol.obtenerValor(), valor) == 0) {
                encontrados.agregar(arbol);
            }
            ListaEnlazada<A_Arbol<T>> hijos = arbol.obtenerHijos();
            for (int i = 0; i < hijos.size(); i++) {
                cola.agregar(hijos.obtener(i));
            }
        }
        return encontrados;
    }
    @Override
    public int height() {
        if (this.hijos.size() == 0) {
            return 1;
        }
        int maxAltura = 0;
        for (int i = 0; i < this.hijos.size(); i++) {
            int alturaHijo = this.hijos.obtener(i).height();
            if (alturaHijo > maxAltura) {
                maxAltura = alturaHijo;
            }
        }
        return maxAltura + 1;
    }
    private void obtenerLevels(int level, ListaEnlazada<A_Arbol<T>>[] levels) {
        levels[level].agregar(this);
        for (int i = 0; i < this.hijos.size(); i++) {
            Arbol<T> hijo = (Arbol<T>) this.hijos.obtener(i);
            hijo.obtenerLevels(level + 1, levels);
        }
    }    
    @SuppressWarnings("unchecked")
    @Override
    public ListaEnlazada<A_Arbol<T>>[] obtenerLevels() {
        ListaEnlazada<A_Arbol<T>>[] levels = new ListaEnlazada[this.height()];
        for (int i = 0; i < levels.length; i++) {
            levels[i] = new ListaEnlazada<>();
        }
        this.obtenerLevels(0, levels); // Llamada recursiva
        return levels;
    }
    private void obtenerLevel(int levelActual, ListaEnlazada<A_Arbol<T>> levelElements, int levelDeseado) {
        if (levelActual == levelDeseado) {
            levelElements.agregar(this);
            return;
        }
        for (int i = 0; i < this.hijos.size(); i++) {
            Arbol<T> hijo = (Arbol<T>) this.hijos.obtener(i);
            hijo.obtenerLevel(levelActual + 1, levelElements, levelDeseado);
        }
    }
    @Override
    public ListaEnlazada<A_Arbol<T>> obtenerLevel(int level) {
        if (level < 0 || level >= this.height()) {
            return null;
        }
        ListaEnlazada<A_Arbol<T>> levelElements = new ListaEnlazada<>();
        this.obtenerLevel(0, levelElements, level); // Llamada recursiva
        return levelElements;
    }
    @Override
    public ListaEnlazada<A_Arbol<T>> obtenerAscends(A_Arbol<T> arbol) {
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
            ListaEnlazada<A_Arbol<T>> ascends = this.hijos.obtener(i).obtenerAscends(arbol);
            if (ascends == null) {
                continue;
            }
            ascends.insertar(0, this);
            return ascends;
        }
        return null;
    }
    @Override
    public ListaEnlazada<A_Arbol<T>> obtenerDescends(A_Arbol<T> arbol) {
        if (arbol == null) {
            return null;
        }
        ListaEnlazada<A_Arbol<T>> descends = new ListaEnlazada<>();
        ListaEnlazada<A_Arbol<T>>[] levels = arbol.obtenerLevels();
        if (levels.length <= 1) {
            return descends;
        }
        for (int i = 1; i < levels.length; i++) {
            for (int j = 0; j < levels[i].size(); j++) {
                descends.agregar(levels[i].obtener(j));
            }
        }
        return descends;
    }
    @Override
    public int obtenerNumLevel(A_Arbol<T> arbol) {
        if (arbol == null) {
            return -1;
        }
        ListaEnlazada<A_Arbol<T>> ascends = this.obtenerAscends(arbol);
        if (ascends == null) {
            return -1;
        }
        return ascends.size();
    }
    @Override
    public A_Arbol<T> obtenerPadre(A_Arbol<T> arbol) {
        if (arbol == null) {
            return null;
        }
        ListaEnlazada<A_Arbol<T>> ascends = this.obtenerAscends(arbol);
        if (ascends == null) {
            return null;
        }
        if (ascends.vacia()) {
            return null;
        }
        return ascends.obtener(ascends.size() - 1);
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
            txt += this.hijos.obtener(i).obtenerValor().toString();
            if (i < this.hijos.size() - 1) {
                txt += ", ";
            }
        }
        txt += "]";
        return txt;
    }
    private void vaciar(int level) {
        this.valor = null;
        for (int i = 0; i < this.hijos.size(); i++) {
            Arbol<T> hijo = (Arbol<T>) this.hijos.obtener(i);
            hijo.vaciar(level + 1);
        }
        if (level > 0) {
            this.hijos = null;
        }
        this.hijos = new ListaEnlazada<>();
    }
    @Override
    public void vaciar() {
        this.vaciar(0);
    }
    @Override
    public String levelsToString() {
        String txt = "";
        ListaEnlazada<A_Arbol<T>>[] levels = this.obtenerLevels();
        for (int i = 0; i < levels.length; i++) {
            for (int j = 0; j < levels[i].size(); j++) {
                txt += levels[i].obtener(j).toString();
                if (j < levels[i].size() - 1) {
                    txt += "\t";
                }
            }
            txt += "\n";
        }
        return txt;
    }
}
