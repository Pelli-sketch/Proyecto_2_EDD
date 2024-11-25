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
    /**
     * Clase Nodo de la Lista.
     */
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

    /**
     * Atributos
     * referencia al primer nodo de la lista.
     *
     * @see Nodo
     */
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

    /**
     * Constructor con un arreglo de elementos. Crea la lista
     * con todos los elementos del arreglo agregados, en el
     * mismo orden.
     *
     * @param valores
     */
    public ListaEnlazada(T[] valores) {
        for (T item : valores) {
            this.agregar(item);
        }
        this.cachedIndex = null;
        this.cachedNodo = null;
    }

    /**
     * Constructor con una lista de elementos. Crea la lista
     * con todos los elementos de la lista agregados, en el
     * mismo orden.
     *
     * @param valores
     */
    public ListaEnlazada(Alist<T> valores) {
        for (int i = 0; i < valores.size(); i++) {
            this.agregar(valores.obtener(i));
        }
        this.cachedIndex = null;
        this.cachedNodo = null;
    }

    /**
     * Devuelve el tamaño de la lista.
     *
     * @return el tamaño de la lista.
     */
    @Override
    public int size() {
        return this.tam;
    }

    /**
     * Verifica si la lista esta vacía.
     *
     * @return true si la lista esta vacía, false en caso contrario.
     */
    @Override
    public boolean vacia() {
        return this.tam == 0;
    }

    /**
     * Transforma valores negativo de un index, para ser usado desde la cola hacia
     * la cabeza de la lista. en este caso si el indice es igual a -1 entonces
     * es igual a: size() - 1 (El ultimo indice de la lista).
     *
     * @param index el indice a transformar
     * @return el indice transformado.
     */
    protected int transformarIndex(int index) {
        if (index < 0 && index >= -this.tam) {
            return this.tam + index;
        }
        return index;
    }

    /**
     * Inserta el valor en la posición indicada. Si el indice es
     * negativo, usa transformarIndex para recorrer la lista desde la cola hacia
     * la cabeza. @see transformarIndex
     *
     * @param index posición en la lista.
     * @param valor valor a insertar.
     * @return true si el valor fue insertado, false en caso contrario.
     */
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

    /**
     * Inserta el valor al final de la lista.
     *
     * @param valor el valor a insertar.
     * @return true si el valor fue insertado, false en caso contrario.
     */
    @Override
    public boolean agregar(T valor) {
        return insertar(size(), valor);
    }

    /**
     * Agrega un array de elementos a la lista.
     * 
     * @param valor el array
     */
    @Override
    public void agregarDesdeArreglo(T[] valor) {
        for (int i = 0; i < valor.length; i++) {
            this.agregar(valor[i]);
        }
    }

    /**
     * Agrega una lista de elementos a la lista.
     * 
     * @param valor la lista a agregar
     */
    public void agregarDesdeLista(Alist<T> valor) {
        for (int i = 0; i < valor.size(); i++) {
            this.agregar(valor.obtener(i));
        }
    }

    /**
     * Devuelve el valor del elemento en la posición indicada o null si hay un
     * error. Si el indice es negativo, usa transformarIndex para recorrer
     * la lista desde la cola hacia la cabeza. @see transformarIndex
     * 
     * @param index posición en la lista.
     * 
     * @return el valor del elemento o null si hay un error.
     */

    public T obtener(int index) {
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

    /**
     * Modifica el valor del elemento en la posición indicada. Si el indice es
     * negativo, usa transformarIndex para recorrer la lista desde la cola hacia
     * la cabeza. @see transformarIndex. Devuelve true si el valor fue
     * modificado, false en caso contrario.
     * 
     * @param index posición en la lista.
     * @param valor el nuevo valor del elemento.
     * 
     * @return true si el valor fue modificado, false en caso contrario.
     */

    @Override
    public boolean actualizar(int index, T valor) {
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

    /**
     * Elimina el elemento en la posición indicada. Devuelve el valor del elemento
     * eliminado o null si hay un error.
     * Si el indice es negativo, usa transformarIndex para recorrer la lista desde
     * la cola hacia la cabeza. @see transformarIndex
     * 
     * @param index posición en la lista.
     * 
     * @return el valor del elemento eliminado o null si hay un error.
     */

    @Override
    public T eliminar(int index) {
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

    /**
     * Elimina todos los elementos de la lista
     */
    @Override
    public void vaciarLista() {
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

    /**
     * Copia la lista en una nueva lista.
     *
     * @return la nueva lista.
     */
    @Override
    public ListaEnlazada<T> copiarLista() {
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

    /**
     * Compara si la lista enviada por parámetro es igual a la lista actual.
     * Usa un comparador para comparar los elementos de ambas listas.
     * 
     * @param list       la lista a comparar.
     * @param comparador una expresión lambda con el comparador. @see IComparador
     * 
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean sonIguales(Alist<T> list, AComparador<T> comparador) {
        if (list == null) {
            return false;
        }
        if (this.tam != list.size()) {
            return false;
        }
        if (this == list) {
            return true;
        }
        if (this.tam == 0) {
            return true;
        }
        for (int i = 0; i < this.tam; i++) {
            if (comparador.compareTo(this.obtener(i), list.obtener(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara si la lista enviada por parámetro es igual a la lista actual.
     * Sirve solo si los elementos de la lista son de tipo String o Number.
     * Si son de otro tipo devuelve false. En este caso deberías usar
     * equals(IList,IComparador). @see equals(IList, IComparador)
     * 
     * @param list la lista a comparar
     * 
     * @return true si son iguales, false en caso contrario
     */
    public boolean sonIguales(Alist<T> list) {
        if (list == null) {
            return false;
        }
        if (this.tam != list.size()) {
            return false;
        }
        if (this.obtener(0) instanceof String && list.obtener(0) instanceof String) {
            return this.sonIguales(list, (a, b) -> ((String) a).compareTo((String) b));
        }
        if (this.obtener(0) instanceof Number && list.obtener(0) instanceof Number) {
            return this.sonIguales(list, (a, b) -> {
                Double num1 = ((Number) a).doubleValue();
                Double num2 = ((Number) b).doubleValue();
                return num1.compareTo(num2);
            });
        }
        return false;
    }

    /**
     * Ordena la lista según el comparador indicado.
     * 
     * @param comparador expresión lambda con el comparador @see IComparador
     * 
     */
    public void ordenarLista(AComparador<T> comparador) {
        if (this.tam == 0 || this.tam == 1) {
            this.cachedIndex = null;
            this.cachedNodo = null;
            return;
        }
        T aux = null;
        for (int i = 0; i < this.size(); i++) {
            for (int j = i + 1; j < this.size(); j++) {
                if (comparador.compareTo(this.obtener(i), this.obtener(j)) > 0) {
                    aux = this.obtener(i);
                    this.actualizar(i, this.obtener(j));
                    this.actualizar(j, aux);
                }
            }
        }
        this.cachedIndex = null;
        this.cachedNodo = null;
    }

    /**
     * Ordena la lista si los elementos de la lista son de tipo String o Number
     * No ordena si la lista tiene elementos de otro tipo. Usar ordenar(IComparador)
     * para estos casos. @see ordenar(IComparador)
     */
    public void ordenar() {
        if (this.tam == 0 || this.tam == 1) {
            this.cachedIndex = null;
            this.cachedNodo = null;
            return;
        }
        if (this.obtener(0) instanceof String) {
            this.ordenarLista((a, b) -> ((String) a).compareTo((String) b));
            return;
        }
        if (this.obtener(0) instanceof Number) {
            this.ordenarLista((a, b) -> {
                Double num1 = ((Number) a).doubleValue();
                Double num2 = ((Number) b).doubleValue();
                return num1.compareTo(num2);
            });
            return;
        }
    }

    /**
     * Invierte la lista
     */
    public void invertir() {
        if (this.tam == 0 || this.tam == 1) {
            this.cachedIndex = null;
            this.cachedNodo = null;
            return;
        }
        T aux = null;
        for (int i = 0; i < this.size() / 2; i++) {
            aux = this.obtener(i);
            this.actualizar(i, this.obtener(this.size() - i - 1));
            this.actualizar(this.size() - i - 1, aux);
        }
        this.cachedIndex = null;
        this.cachedNodo = null;
    }

    /**
     * Busca un elemento en la lista y retorna su posición o -1 si no lo encuentra.
     * Utiliza un comparador.
     * 
     * @param valor      el elemento a buscar
     * @param comparador el comparador @see IComparador
     * 
     * @return la posición del elemento o -1
     */
    public int buscarElemento(T valor, AComparador<T> comparador) {
        for (int i = 0; i < this.tam; i++) {
            if (comparador.compareTo(this.obtener(i), valor) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca un elemento en la lista y retorna su índice o -1 si no lo encuentra.
     * No utiliza un comparador. Solo funciona para elementos de la lista que
     * son de tipo String o Number. Si son de otro tipo devuelve -1. Usar
     * buscar(T, IComparador) para estos casos. @see buscar(T, IComparador)
     * 
     * @param valor el elemento a buscar
     * 
     * @return la dirección del elemento o -1
     */
    public int buscarElemento(T valor) {
        if (this.tam == 0) {
            return -1;
        }
        if (this.obtener(0) instanceof String && valor instanceof String) {
            return this.buscarElemento(valor, (a, b) -> ((String) a).compareTo((String) b));
        }
        if (this.obtener(0) instanceof Number && valor instanceof Number) {
            return this.buscarElemento(valor, (a, b) -> {
                Double num1 = ((Number) a).doubleValue();
                Double num2 = ((Number) b).doubleValue();
                return num1.compareTo(num2);
            });
        }
        return -1;
    }

    /**
     * Devuelve una cadena de caracteres con la representación de la lista.
     *
     * @return una cadena de caracteres con la representación de la lista.
     */
    @Override
    public String toString() {
        String txt = "List(" + this.tam + "): ";
        if (this.vacia()) {
            txt += "//";
            return txt;
        }
        Nodo<T> nodo;
        nodo = this.cabeza;
        while (nodo != null) {
            txt += nodo.toString();
            nodo = nodo.siguiente;
        }
        return txt;
    }

    /**
     * Main por si se necesita probar algo directamente.
     *
     * @param args
     */

    public static void main(String args[]) {

    }
}
