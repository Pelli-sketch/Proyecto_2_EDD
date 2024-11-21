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
            String txt = "";
            if (this.valor == null) {
                txt += "";
            } else {
                txt += String.valueOf(valor);
            }
            if (this.siguiente != null) {
                txt += " -> ";
            } else {
                txt += " -> //";
            }
            return txt;
        }
    }
    
    private Nodo<T> cabeza;

    /**
     * referencia al ultimo nodo de la lista
     */
    private Nodo<T> cola;

    /**
     * indice del nodo en cache
     */
    private Integer cachedIndex;

    /**
     * nodo en cache
     */
    private Nodo<T> cachedNodo;

    /**
     * tamaño de la lista.
     */
    private int tam;

    /**
     * Constructor
     */
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
        return this.tam == 0;
    }
    @Override
    public int size() {
        return this.tam;
    }
    protected int transformarIndex(int index) {
        if (index < 0 && index >= -this.tam) {
            return this.tam + index;
        }
        return index;
    }
    
    public boolean insertar(int index, T valor) {
        index = transformarIndex(index);
        if (index < 0 || index > this.tam) {
            return false;
        }
        Nodo<T> nodo = new Nodo<>(valor);
        nodo.siguiente = null;
        if (this.vacia()) {
            this.cabeza = nodo;
            this.cola = nodo;
            this.cachedIndex = 0;
            this.cachedNodo = nodo;
            this.tam++;
            return true;
        }

        if (index == 0) {
            nodo.siguiente = this.cabeza;
            this.cabeza = nodo;
            this.cachedIndex = 0;
            this.cachedNodo = nodo;
            this.tam++;
            return true;
        }
        if (index == this.tam) {
            this.cola.siguiente = nodo;
            this.cola = nodo;
            this.tam++;
            return true;
        }

        Nodo<T> aux;
        int i;
        if (this.cachedIndex == null || (index < this.cachedIndex - 1)) {
            aux = this.cabeza;
            i = 0;
        } else {
            aux = this.cachedNodo;
            i = this.cachedIndex;
        }
        for (; i < index - 1; i++) {
            aux = aux.siguiente;
        }
        nodo.siguiente = aux.siguiente;
        aux.siguiente = nodo;
        this.cachedIndex = index;
        this.cachedNodo = nodo;
        this.tam++;
        return true;
    }
    
    @Override
    public boolean agregar(T valor) {
        return insertar(size(), valor);
    }
    public void agregar(T[] valor) {
        for (int i = 0; i < valor.length; i++) {
            this.agregar(valor[i]);
        }
    }
    public void agregar(Alist<T> valor) {
        for (int i = 0; i < valor.size(); i++) {
            this.agregar(valor.get(i));
        }
    }
    public T get(int index) {
        if (this.vacia()) {
            this.cachedIndex = null;
            this.cachedNodo = null;
            return null;
        }
        index = transformarIndex(index);
        if (index < 0 || index >= this.tam) {
            return null;
        }
        if (index == this.tam - 1) {
            return this.cola.valor;
        }
        Nodo<T> nodo = cabeza;
        int i;
        if (this.cachedIndex == null || (index < this.cachedIndex)) {
            nodo = this.cabeza;
            i = 0;
        } else {
            nodo = this.cachedNodo;
            i = this.cachedIndex;
        }
        for (; i < index; i++) {
            nodo = nodo.siguiente;
        }
        this.cachedIndex = index;
        this.cachedNodo = nodo;
        return nodo.valor;
    }
    @Override
    public boolean set(int index, T valor) {
        if (this.vacia()) {
            this.cachedIndex = null;
            this.cachedNodo = null;
            return false;
        }
        index = transformarIndex(index);
        if (index < 0 || index >= this.tam) {
            return false;
        }
        if (index == this.tam - 1) {
            this.cola.valor = valor;
            return true;
        }
        Nodo<T> nodo = this.cabeza;
        int i;
        if (this.cachedIndex == null || (index < this.cachedIndex)) {
            nodo = this.cabeza;
            i = 0;
        } else {
            nodo = this.cachedNodo;
            i = this.cachedIndex;
        }
        for (; i < index; i++) {
            nodo = nodo.siguiente;
        }
        nodo.valor = valor;
        this.cachedIndex = index;
        this.cachedNodo = nodo;
        return true;
    }
    public T remover(int index) {
        if (this.vacia()) {
            this.cachedIndex = null;
            this.cachedNodo = null;
            return null;
        }
        index = transformarIndex(index);
        if (index < 0 || index >= this.tam) {
            return null;
        }
        Nodo<T> aux;
        if (index == 0) {
            aux = this.cabeza;
            this.cabeza = this.cabeza.siguiente;
            if (this.cabeza == null) {
                this.cola = null;
            }
            aux.siguiente = null;
            this.tam--;
            this.cachedIndex = null;
            this.cachedNodo = null;
            return aux.valor;
        }
        aux = this.cabeza;
        int i;
        if (this.cachedIndex == null || (index < this.cachedIndex - 1)) {
            aux = this.cabeza;
            i = 0;
        } else {
            aux = this.cachedNodo;
            i = this.cachedIndex;
        }
        for (; i < index - 1; i++) {
            aux = aux.siguiente;
        }
        Nodo<T> nodo = aux.siguiente;
        if (nodo == this.cola) {
            this.cola = aux;
        }
        aux.siguiente = aux.siguiente.siguiente;
        this.cachedIndex = null;
        this.cachedNodo = null;
        this.tam--;
        nodo.siguiente = null;
        return nodo.valor;
    }
    @Override
    public void vaciar() {
        if (this.vacia()) {
            this.cachedIndex = null;
            this.cachedNodo = null;
            return;
        }
        Nodo<T> nodo = this.cabeza;
        Nodo<T> siguiente;
        while (nodo != null) {
            siguiente = nodo.siguiente;
            nodo.siguiente = null;
            nodo = siguiente;
        }
        this.cabeza = null;
        this.tam = 0;
        this.cachedIndex = null;
        this.cachedNodo = null;
    }
    @Override
    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        if (this.vacia()) {
            return copia;
        }
        Nodo<T> nodo = this.cabeza;
        T valor;
        while (nodo != null) {
            valor = nodo.valor;
            copia.agregar(valor);
            nodo = nodo.siguiente;
        }
        return copia;
    }

    
    
    
}
