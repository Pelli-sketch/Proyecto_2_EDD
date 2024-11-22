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
            this.agregar(valores.get(i));
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

    protected int transformarIndex(int index) {
        return (index < 0 && index >= -tam) ? tam + index : index;
    }

    public boolean insertar(int index, T valor) {
        index = transformarIndex(index);
        if (index < 0 || index > tam) {
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

        if (index == 0) {
            nuevoNodo.siguiente = cabeza;
            cabeza = nuevoNodo;
            cachedIndex = 0;
            cachedNodo = nuevoNodo;
            tam++;
            return true;
        }

        if (index == tam) {
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
            tam++;
            return true;
        }

        Nodo<T> aux;
        int i;
        if (cachedIndex == null || index < cachedIndex - 1) {
            aux = cabeza;
            i = 0;
        } else {
            aux = cachedNodo;
            i = cachedIndex;
        }

        for (; i < index - 1; i++) {
            aux = aux.siguiente;
        }

        nuevoNodo.siguiente = aux.siguiente;
        aux.siguiente = nuevoNodo;
        cachedIndex = index;
        cachedNodo = nuevoNodo;
        tam++;
        return true;
    }
    
    @Override
    public boolean agregar(T valor) {
        return insertar(size(), valor);
    }

    public void agregar(T[] valores) {
        for (T valor : valores) {
            agregar(valor);
        }
    }

    public void agregar(Alist<T> lista) {
        for (int i = 0; i < lista.size(); i++) {
            agregar(lista.get(i));
        }
    }

    public T get(int index) {
        if (vacia()) {
            resetCache();
            return null;
        }
        index = transformarIndex(index);
        if (index < 0 || index >= tam) {
            return null;
        }
        if (index == tam - 1) {
            return cola.valor;
        }
    
        Nodo<T> nodo = (cachedIndex == null || index < cachedIndex) ? cabeza : cachedNodo;
        int i = (cachedIndex == null || index < cachedIndex) ? 0 : cachedIndex;

        while (i < index) {
            nodo = nodo.siguiente;
            i++;
        }
    
        updateCache(index, nodo);
        return nodo.valor;
    }

    @Override
    public boolean set(int index, T valor) {
        if (vacia()) {
            resetCache();
            return false;
        }
        index = transformarIndex(index);
        if (index < 0 || index >= tam) {
            return false;
        }
        if (index == tam - 1) {
            cola.valor = valor;
            return true;
        }

        Nodo<T> nodo = (cachedIndex == null || index < cachedIndex) ? cabeza : cachedNodo;
        int i = (cachedIndex == null || index < cachedIndex) ? 0 : cachedIndex;

        while (i < index) {
            nodo = nodo.siguiente;
            i++;
        }
    
        nodo.valor = valor;
        updateCache(index, nodo);
        return true;
    }   

    public T remover(int index) {
        if (vacia()) {
            resetCache();
            return null;
        }
        index = transformarIndex(index);
        if (index < 0 || index >= tam) {
            return null;
        }

        Nodo<T> aux;
        if (index == 0) {
            aux = cabeza;
            cabeza = cabeza.siguiente;
            if (cabeza == null) {
                cola = null;
            }
            tam--;
            resetCache();
            return aux.valor;
        }

        aux = (cachedIndex == null || index < cachedIndex - 1) ? cabeza : cachedNodo;
        int i = (cachedIndex == null || index < cachedIndex - 1) ? 0 : cachedIndex;

        while (i < index - 1) {
            aux = aux.siguiente;
            i++;
        }

        Nodo<T> nodo = aux.siguiente;
        if (nodo == cola) {
            cola = aux;
        }
        aux.siguiente = nodo.siguiente;
        resetCache();
        tam--;
        nodo.siguiente = null;
        return nodo.valor;
    }

    private void resetCache() {
        cachedIndex = null;
        cachedNodo = null;
    }

    private void updateCache(int index, Nodo<T> nodo) {
        cachedIndex = index;
        cachedNodo = nodo;
    }
    
    @Override
    public void vaciar() {
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
    public ListaEnlazada<T> copiar() {
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
    public boolean equals(Alist<T> list, AComparador<T> comparador) {
        if (list == null || tam != list.size()) {
            return false;
        }
        if (this == list || tam == 0) {
            return true;
        }
        for (int i = 0; i < tam; i++) {
            if (comparador.compareTo(get(i), list.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Alist<T> list) {
        if (list == null || tam != list.size()) {
            return false;
        }
        if (get(0) instanceof String && list.get(0) instanceof String) {
            return equals(list, (a, b) -> ((String) a).compareTo((String) b));
        }
        if (get(0) instanceof Number && list.get(0) instanceof Number) {
            return equals(list, (a, b) -> Double.compare(((Number) a).doubleValue(), ((Number) b).doubleValue()));
        }
        return false;
    }

    public void ordenar(AComparador<T> comparador) {
        if (tam <= 1) {
            cachedIndex = null;
            cachedNodo = null;
            return;
        }
        for (int i = 0; i < size(); i++) {
            for (int j = i + 1; j < size(); j++) {
                if (comparador.compareTo(get(i), get(j)) > 0) {
                    T aux = get(i);
                    set(i, get(j));
                    set(j, aux);
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
        if (get(0) instanceof String) {
            ordenar((a, b) -> ((String) a).compareTo((String) b));
        } else if (get(0) instanceof Number) {
            ordenar((a, b) -> Double.compare(((Number) a).doubleValue(), ((Number) b).doubleValue()));
        }
    }

    public void invertir() {
        if (tam <= 1) {
            cachedIndex = null;
            cachedNodo = null;
            return;
        }
        for (int i = 0; i < size() / 2; i++) {
            T aux = get(i);
            set(i, get(size() - i - 1));
            set(size() - i - 1, aux);
        }
        cachedIndex = null;
        cachedNodo = null;
    }

    public int buscar(T valor, AComparador<T> comparador) {
        for (int i = 0; i < tam; i++) {
            if (comparador.compareTo(get(i), valor) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int buscar(T valor) {
        if (tam == 0) {
            return -1;
        }
        if (get(0) instanceof String && valor instanceof String) {
            return buscar(valor, (a, b) -> ((String) a).compareTo((String) b));
        }
        if (get(0) instanceof Number && valor instanceof Number) {
            return buscar(valor, (a, b) -> Double.compare(((Number) a).doubleValue(), ((Number) b).doubleValue()));
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
