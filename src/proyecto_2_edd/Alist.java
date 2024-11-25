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
/**
 * Interfaz genérica Alist<T>
 * Define las operaciones básicas para el manejo de una lista genérica.
 *
 * @param <T> El tipo de datos que se almacenará en la lista.
 */
interface Alist<T> {

    /**
     * Obtiene el tamaño actual de la lista.
     *
     * @return El número de elementos en la lista.
     */
    int size();

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista está vacía, false en caso contrario.
     */
    boolean vacia();

    /**
     * Inserta un elemento en una posición específica de la lista.
     *
     * @param index La posición donde se desea insertar el elemento.
     * @param valor  El valor a insertar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    boolean insertar(int index, T valor);

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param valor El valor a agregar.
     * @return true si el elemento fue agregado, false en caso contrario.
     */
    boolean agregar(T valor);

    /**
     * Agrega elementos a la lista desde un arreglo.
     *
     * @param nuevoValor Un arreglo con los valores a agregar.
     */
    void agregarDesdeArreglo(T[] valor);

    /**
     * Agrega elementos a la lista desde otra lista.
     *
     * @param nuevoValor La lista cuyos valores se desean agregar.
     */
    void agregarDesdeLista(Alist<T> valor);

    /**
     * Obtiene el elemento en una posición específica de la lista.
     *
     * @param index La posición del elemento a obtener.
     * @return El elemento en la posición indicada.
     */
    T obtener(int index);

    /**
     * Actualiza el valor en una posición específica de la lista.
     *
     * @param index     La posición del elemento a actualizar.
     * @param nuevoValor El nuevo valor a asignar.
     * @return true si la actualización fue exitosa, false en caso contrario.
     */
    boolean actualizar(int index, T valor);

    /**
     * Elimina un elemento de la lista en una posición específica.
     *
     * @param index La posición del elemento a eliminar.
     * @return El valor del elemento eliminado.
     */
    T eliminar(int index);

    /**
     * Vacía todos los elementos de la lista.
     */
    void vaciarLista();

    /**
     * Crea una copia de la lista.
     *
     * @return Una nueva lista que es una copia de la actual.
     */
    Alist<T> copiarLista();

    /**
     * Compara si dos listas son iguales utilizando un comparador.
     *
     * @param lista      La lista con la que se desea comparar.
     * @param comparador El comparador utilizado para evaluar la igualdad.
     * @return true si las listas son iguales, false en caso contrario.
     */
    boolean sonIguales(Alist<T> lista, AComparador<T> comparador);

    /**
     * Compara si dos listas son iguales.
     *
     * @param lista La lista con la que se desea comparar.
     * @return true si las listas son iguales, false en caso contrario.
     */
    boolean sonIguales(Alist<T> lista);

    /**
     * Ordena la lista utilizando un comparador.
     *
     * @param comparador El comparador utilizado para ordenar los elementos.
     */
    void ordenarLista(AComparador<T> comparador);

    /**
     * Ordena la lista de manera natural, dependiendo de su implementación.
     */
    void ordenar();

    /**
     * Invierte el orden de los elementos en la lista.
     */
    void invertir();

    /**
     * Busca un elemento en la lista utilizando un comparador.
     *
     * @param valor      El valor a buscar.
     * @param comparador El comparador utilizado para la búsqueda.
     * @return La posición del elemento encontrado, o -1 si no se encuentra.
     */
    int buscarElemento(T valor, AComparador<T> comparador);

    /**
     * Busca un elemento en la lista.
     *
     * @param valor El valor a buscar.
     * @return La posición del elemento encontrado, o -1 si no se encuentra.
     */
    int buscarElemento(T valor);

    /**
     * Retorna una representación en forma de cadena de la lista.
     *
     * @return Una cadena con los elementos de la lista.
     */
    @Override
    String toString();
}

