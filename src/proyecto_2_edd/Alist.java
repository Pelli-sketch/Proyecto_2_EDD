/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */
/**
 * Interfaz lista de elementos de tipo genérico
 * 
 */
interface Alist<T> {
    /**
     * Método que retorna el tamaño de la lista
     * 
     * @return el tamaño de la lista
     */
    int size();

    /**
     * Método que indica si la lista esta vacia
     * 
     * @return true si la lista esta vacia, false en caso contrario
     */
    boolean vacia();

    /**
     * Método que inserta un elemento en la lista
     * 
     * @param indice el indice en la lista donde insertar el elemento
     * @param nuevoValor el nuevoValor a insertar
     * @return true si se pudo insertar, false en caso contrario
     */
    boolean insertar(int indice, T valor);

    /**
     * Método que agrega un elemento a la lista
     * 
     * @param nuevoValor el nuevoValor a agregar
     * @return true si se pudo agregar, false en caso contrario
     */
    boolean agregar(T valor);

    /**
     * Método que agrega un array de elementos a la lista y
     * devuelve un arreglo de booleanos con el resultado de la operación
     * 
     * @param nuevoValor el array a agregar
     * @return un array de booleanos
     */
    void agregarDesdeArreglo(T[] nuevoValor);

    /**
     * Método que agrega una lista de elementos a la lista y devuelve un array de
     * booleanos con el resultado de la operación
     * 
     * @param nuevoValor la lista a agregar
     * @return un array de booleanos
     */
    void agregarDesdeLista(Alist<T> nuevoValor);

    /**
     * Método que retorna un elemento de la lista
     * 
     * @param indice el indice del elemento
     * @return el nuevoValor
     */
    T obtener(int indice);

    /**
     * Método que modifica un elemento de la lista
     * 
     * @param indice el indice del elemento
     * @param nuevoValor el nuevo nuevoValor
     * @return true si se pudo modificar, false en caso contrario
     */
    boolean actualizar(int indice, T nuevoValor);

    /**
     * Método que elimina un elemento de la lista y lo retorna
     * 
     * @param indice el indice del elemento
     * @return el nuevoValor que se elimino
     */
    T eliminar(int indice);

    /**
     * Método que vacía la lista
     */
    void vaciarLista();
    
    Alist<T> copiarLista();
    
    public boolean sonIguales(Alist<T> lista, AComparador<T> comparador);

    public boolean sonIguales(Alist<T> lista);
    
    public void ordenarLista(AComparador<T> comparador);

    public void ordenar();

    public void invertir();

    public int buscarElemento(T valor, AComparador<T> comparador);

    public int buscarElemento(T valor);    
    
    /**
     * Método que retorna un string con la representación de la lista
     * 
     * @return la representación
     */
    public String toString();
}
