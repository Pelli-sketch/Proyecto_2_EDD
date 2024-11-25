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
 * Interfaz genérica A_Arbol<T>
 * Define las operaciones básicas para una estructura de árbol genérica.
 *
 * @param <T> El tipo de datos que almacena el árbol.
 */
interface A_Arbol<T> {

    /**
     * Verifica si el árbol está vacío.
     *
     * @return true si el árbol está vacío, false en caso contrario.
     */
    public boolean vacio();

    /**
     * Obtiene el número de elementos almacenados en el árbol.
     *
     * @return El número total de elementos en el árbol.
     */
    public int numElements();

    /**
     * Calcula la altura del árbol.
     *
     * @return La altura del árbol.
     */
    public int height();

    /**
     * Obtiene el valor almacenado en el nodo raíz del árbol.
     *
     * @return El valor del nodo raíz.
     */
    public T obtenerValor();

    /**
     * Actualiza el valor almacenado en el nodo raíz del árbol.
     *
     * @param valor El nuevo valor para el nodo raíz.
     */
    public void actualizarValor(T valor);

    /**
     * Obtiene la raíz del árbol.
     *
     * @return Un árbol que representa la raíz actual.
     */
    public A_Arbol<T> obtenerRaiz();

    /**
     * Agrega un hijo al nodo actual.
     *
     * @param hijo El árbol hijo a agregar.
     * @return true si el hijo fue agregado con éxito, false en caso contrario.
     */
    public boolean agregarHijo(A_Arbol<T> hijo);

    /**
     * Obtiene todos los hijos del nodo actual.
     *
     * @return Una lista enlazada con todos los hijos del nodo.
     */
    public ListaEnlazada<A_Arbol<T>> obtenerHijos();

    /**
     * Obtiene un hijo específico del nodo actual.
     *
     * @param i El índice del hijo deseado.
     * @return El árbol correspondiente al hijo solicitado.
     */
    public A_Arbol<T> obtenerHijo(int i);

    /**
     * Actualiza el comparador utilizado para evaluar los elementos del árbol.
     *
     * @param comparador El nuevo comparador a utilizar.
     */
    public void actualizarComparador(AComparador<T> comparador);

    /**
     * Busca un hijo directo del nodo actual que coincida con un valor específico.
     *
     * @param valor El valor a buscar.
     * @return Una lista enlazada con los hijos que coinciden.
     */
    public ListaEnlazada<A_Arbol<T>> buscarHijo(T valor);

    /**
     * Busca nodos en el árbol que coincidan con un valor específico.
     *
     * @param valor El valor a buscar.
     * @return Una lista enlazada con los nodos que coinciden.
     */
    public ListaEnlazada<A_Arbol<T>> buscar(T valor);

    /**
     * Obtiene el padre de un nodo específico.
     *
     * @param arbol El nodo cuyo padre se desea obtener.
     * @return El árbol que representa al padre del nodo.
     */
    public A_Arbol<T> obtenerPadre(A_Arbol<T> arbol);

    /**
     * Obtiene todos los ascendientes (ancestros) de un nodo específico.
     *
     * @param arbol El nodo cuyo árbol de ascendientes se desea obtener.
     * @return Una lista enlazada con todos los ascendientes del nodo.
     */
    public ListaEnlazada<A_Arbol<T>> obtenerAscends(A_Arbol<T> arbol);

    /**
     * Obtiene todos los descendientes de un nodo específico.
     *
     * @param arbol El nodo cuyo árbol de descendientes se desea obtener.
     * @return Una lista enlazada con todos los descendientes del nodo.
     */
    public ListaEnlazada<A_Arbol<T>> obtenerDescends(A_Arbol<T> arbol);

    /**
     * Obtiene todos los niveles del árbol organizados como listas.
     *
     * @return Un arreglo de listas enlazadas donde cada lista representa un nivel del árbol.
     */
    public ListaEnlazada<A_Arbol<T>>[] obtenerLevels();

    /**
     * Obtiene los nodos de un nivel específico del árbol.
     *
     * @param level El número del nivel que se desea obtener.
     * @return Una lista enlazada con los nodos de ese nivel.
     */
    public ListaEnlazada<A_Arbol<T>> obtenerLevel(int level);

    /**
     * Obtiene el número de nivel en el que se encuentra un nodo específico.
     *
     * @param arbol El nodo cuyo nivel se desea obtener.
     * @return El número del nivel del nodo.
     */
    public int obtenerNumLevel(A_Arbol<T> arbol);

    /**
     * Vacía el contenido del árbol, eliminando todos sus nodos.
     */
    public void vaciar();

    /**
     * Representa los niveles del árbol como una cadena de texto.
     *
     * @return Una cadena que describe los niveles del árbol.
     */
    public String levelsToString();

    /**
     * Retorna una representación en forma de cadena del árbol.
     *
     * @return Una cadena con la información del árbol.
     */
    @Override
    public String toString();
}

