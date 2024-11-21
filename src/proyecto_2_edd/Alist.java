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
     * @param index el indice en la lista donde insertar el elemento
     * @param valor el valor a insertar
     * @return true si se pudo insertar, false en caso contrario
     */
    boolean insertar(int index, T valor);

    /**
     * Método que agrega un elemento a la lista
     * 
     * @param valor el valor a agregar
     * @return true si se pudo agregar, false en caso contrario
     */
    boolean agregar(T valor);

    /**
     * Método que agrega un array de elementos a la lista y
     * devuelve un arreglo de booleanos con el resultado de la operación
     * 
     * @param valor el array a agregar
     * @return un array de booleanos
     */
    void agregar(T[] valor);

    /**
     * Método que agrega una lista de elementos a la lista y devuelve un array de
     * booleanos con el resultado de la operación
     * 
     * @param valor la lista a agregar
     * @return un array de booleanos
     */
    void agregar(Alist<T> valor);

    /**
     * Método que retorna un elemento de la lista
     * 
     * @param index el indice del elemento
     * @return el valor
     */
    T get(int index);

    /**
     * Método que modifica un elemento de la lista
     * 
     * @param index el indice del elemento
     * @param valor el nuevo valor
     * @return true si se pudo modificar, false en caso contrario
     */
    boolean set(int index, T valor);

    /**
     * Método que elimina un elemento de la lista y lo retorna
     * 
     * @param index el indice del elemento
     * @return el valor que se elimino
     */
    T remover(int index);

    /**
     * Método que vacía la lista
     */
    void vaciar();
    
    Alist<T> copiar();
    
    public boolean equals(Alist<T> list, AComparador<T> comparador);

    public boolean equals(Alist<T> list);
    
    public void ordenar(AComparador<T> comparador);

    public void ordenar();

    public void invertir();

    public int buscar(T valor, AComparador<T> comparador);

    public int buscar(T valor);    
    
    /**
     * Método que retorna un string con la representación de la lista
     * 
     * @return la representación
     */
    public String toString();
}
