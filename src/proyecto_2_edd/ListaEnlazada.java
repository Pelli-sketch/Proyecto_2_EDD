/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */
public class ListaEnlazada<T> implements Alist<T> {

    @SuppressWarnings("hiding")
    private class Nodo<T> {
        T valor;
        Nodo<T> siguiente;

        Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }

        @Override
        public String toString() {
            StringBuilder txt = new StringBuilder();
            txt.append(this.valor != null ? valor.toString() : "");
            txt.append(this.siguiente != null ? " -> " : " -> //");
            return txt.toString();
        }
    }

    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private Integer cachedIndex;
    private Nodo<T> cachedNodo;
    private int tam;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cola = null;
        this.tam = 0;
        this.cachedIndex = null;
        this.cachedNodo = null;
    }

    public ListaEnlazada(T[] valores) {
        for (T item : valores) {
            this.agregar(item);
        }
        this.cachedIndex = null;
        this.cachedNodo = null;
    }

    public ListaEnlazada(Alist<T> valores) {
        for (int i = 0; i < valores.size(); i++) {
            this.agregar(valores.obtener(i));
        }
        this.cachedIndex = null;
        this.cachedNodo = null;
    }

    @Override
    public boolean vacia() {
        return tam == 0;
    }

    @Override
    public int size() {
        return tam;
    }

    protected int convertirIndice(int indice) {
        return (indice < 0 && indice >= -tam) ? tam + indice : indice;
    }

    public boolean insertar(int indice, T valor) {
        indice = convertirIndice(indice);
        if (indice < 0 || indice > tam) {
            return false;
        }

        Nodo<T> nuevoNodo = new Nodo<>(valor);
        if (vacia()) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
            cachedIndex = 0;
            cachedNodo = nuevoNodo;
            tam++;
            return true;
        }

        if (indice == 0) {
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
            cachedIndex = 0;
            cachedNodo = nuevoNodo;
            tam++;
            return true;
        }

        if (indice == tam) {
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
            tam++;
            return true;
        }

        Nodo<T> aux;
        int i;
        if (cachedIndex == null || indice < cachedIndex - 1) {
            aux = cabeza;
            i = 0;
        } else {
            aux = cachedNodo;
            i = cachedIndex;
        }

        for (; i < indice - 1; i++) {
            aux = aux.siguiente;
        }

        nuevoNodo.siguiente = aux.siguiente;
        aux.siguiente = nuevoNodo;
        cachedIndex = indice;
        cachedNodo = nuevoNodo;
        tam++;
        return true;
    }
    
    @Override
    public boolean agregar(T valor) {
        return insertar(size(), valor);
    }

    public void agregarDesdeArreglo(T[] valores) {
        for (T valor : valores) {
            agregar(valor);
        }
    }

    public void agregarDesdeLista(Alist<T> lista) {
        for (int i = 0; i < lista.size(); i++) {
            agregar(lista.obtener(i));
        }
    }

    public T obtener(int indice) {
        if (vacia()) {
            reactualizarCache();
            return null;
        }
        indice = convertirIndice(indice);
        if (indice < 0 || indice >= tam) {
            return null;
        }
        if (indice == tam - 1) {
            return cola.valor;
        }
    
        Nodo<T> nodo = (cachedIndex == null || indice < cachedIndex) ? cabeza : cachedNodo;
        int i = (cachedIndex == null || indice < cachedIndex) ? 0 : cachedIndex;

        while (i < indice) {
            nodo = nodo.siguiente;
            i++;
        }
    
        updateCache(indice, nodo);
        return nodo.valor;
    }

    @Override
    public boolean actualizar(int indice, T valor) {
        if (vacia()) {
            reactualizarCache();
            return false;
        }
        indice = convertirIndice(indice);
        if (indice < 0 || indice >= tam) {
            return false;
        }
        if (indice == tam - 1) {
            cola.valor = valor;
            return true;
        }

        Nodo<T> nodo = (cachedIndex == null || indice < cachedIndex) ? cabeza : cachedNodo;
        int i = (cachedIndex == null || indice < cachedIndex) ? 0 : cachedIndex;

        while (i < indice) {
            nodo = nodo.siguiente;
            i++;
        }
    
        nodo.valor = valor;
        updateCache(indice, nodo);
        return true;
    }   

    public T eliminar(int indice) {
        if (vacia()) {
            reactualizarCache();
            return null;
        }
        indice = convertirIndice(indice);
        if (indice < 0 || indice >= tam) {
            return null;
        }

        Nodo<T> aux;
        if (indice == 0) {
            aux = cabeza;
            cabeza = cabeza.siguiente;
            if (cabeza == null) {
                cola = null;
            }
            tam--;
            reactualizarCache();
            return aux.valor;
        }

        aux = (cachedIndex == null || indice < cachedIndex - 1) ? cabeza : cachedNodo;
        int i = (cachedIndex == null || indice < cachedIndex - 1) ? 0 : cachedIndex;

        while (i < indice - 1) {
            aux = aux.siguiente;
            i++;
        }

        Nodo<T> nodo = aux.siguiente;
        if (nodo == cola) {
            cola = aux;
        }
        aux.siguiente = nodo.siguiente;
        reactualizarCache();
        tam--;
        nodo.siguiente = null;
        return nodo.valor;
    }

    private void reactualizarCache() {
        cachedIndex = null;
        cachedNodo = null;
    }

    private void updateCache(int indice, Nodo<T> nodo) {
        cachedIndex = indice;
        cachedNodo = nodo;
    }
    
    @Override
    public void vaciarLista() {
        if (vacia()) {
            cachedIndex = null;
            cachedNodo = null;
        } else {
            Nodo<T> nodo = cabeza;
            while (nodo != null) {
                Nodo<T> siguiente = nodo.siguiente;
                nodo.siguiente = null;
                nodo = siguiente;
            }
            cabeza = null;
            tam = 0;
            cachedIndex = null;
            cachedNodo = null;
        }
    }

    @Override
    public ListaEnlazada<T> copiarLista() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        if (!vacia()) {
            Nodo<T> nodo = cabeza;
            while (nodo != null) {
                copia.agregar(nodo.valor);
                nodo = nodo.siguiente;
            }
        }
        return copia;
    }

    @Override
    public boolean sonIguales(Alist<T> list, AComparador<T> comparador) {
        if (list == null || tam != list.size()) {
            return false;
        }
        if (this == list || tam == 0) {
            return true;
        }
        for (int i = 0; i < tam; i++) {
            if (comparador.compareTo(obtener(i), list.obtener(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean sonIguales(Alist<T> list) {
        if (list == null || tam != list.size()) {
            return false;
        }
        if (obtener(0) instanceof String && list.obtener(0) instanceof String) {
            return sonIguales(list, (a, b) -> ((String) a).compareTo((String) b));
        }
        if (obtener(0) instanceof Number && list.obtener(0) instanceof Number) {
            return sonIguales(list, (a, b) -> Double.compare(((Number) a).doubleValue(), ((Number) b).doubleValue()));
        }
        return false;
    }

    public void ordenarLista(AComparador<T> comparador) {
        if (tam <= 1) {
            cachedIndex = null;
            cachedNodo = null;
            return;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = i + 1; j < size(); j++) {
                if (comparador.compareTo(obtener(i), obtener(j)) > 0) {
                    T aux = obtener(i);
                    actualizar(i, obtener(j));
                    actualizar(j, aux);
                }
            }
        }
        cachedIndex = null;
        cachedNodo = null;
    }

    public void ordenar() {
        if (tam <= 1) {
            cachedIndex = null;
            cachedNodo = null;
            return;
        }
        if (obtener(0) instanceof String) {
            ordenarLista((a, b) -> ((String) a).compareTo((String) b));
        } else if (obtener(0) instanceof Number) {
            ordenarLista((a, b) -> Double.compare(((Number) a).doubleValue(), ((Number) b).doubleValue()));
        }
    }

    public void invertir() {
        if (tam <= 1) {
            cachedIndex = null;
            cachedNodo = null;
            return;
        }
        for (int i = 0; i < size() / 2; i++) {
            T aux = obtener(i);
            actualizar(i, obtener(size() - i - 1));
            actualizar(size() - i - 1, aux);
        }
        cachedIndex = null;
        cachedNodo = null;
    }

    public int buscarElemento(T valor, AComparador<T> comparador) {
        for (int i = 0; i < tam; i++) {
            if (comparador.compareTo(obtener(i), valor) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int buscarElemento(T valor) {
        if (tam == 0) {
            return -1;
        }
        if (obtener(0) instanceof String && valor instanceof String) {
            return buscarElemento(valor, (a, b) -> ((String) a).compareTo((String) b));
        }
        if (obtener(0) instanceof Number && valor instanceof Number) {
            return buscarElemento(valor, (a, b) -> Double.compare(((Number) a).doubleValue(), ((Number) b).doubleValue()));
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder txt = new StringBuilder("Lista(" + tam + "): ");
        if (vacia()) {
            txt.append("//");
        } else {
            Nodo<T> nodo = cabeza;
            while (nodo != null) {
                txt.append(nodo.toString());
                nodo = nodo.siguiente;
            }
        }
        return txt.toString();
    }

    
    
}
