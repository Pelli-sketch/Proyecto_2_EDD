/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */
interface A_Arbol<T> {
    
    public boolean vacio();
    
    public int numElements();

    public int height();

    public T obtenerValor();

    public void actualizarValor(T valor);

    public A_Arbol<T> obtenerRaiz();

    public boolean agregarHijo(A_Arbol<T> hijo);

    public ListaEnlazada<A_Arbol<T>> obtenerHijos();

    public A_Arbol<T> obtenerHijo(int i);

    public void setComparador(AComparador<T> comparador);

    public ListaEnlazada<A_Arbol<T>> buscarHijo(T valor);

    public ListaEnlazada<A_Arbol<T>> buscar(T valor);

    public A_Arbol<T> obtenerPadre(A_Arbol<T> arbol);

    public ListaEnlazada<A_Arbol<T>> obtenerAscends(A_Arbol<T> arbol);

    public ListaEnlazada<A_Arbol<T>> obtenerDescends(A_Arbol<T> arbol);

    public ListaEnlazada<A_Arbol<T>>[] obtenerLevels();

    public ListaEnlazada<A_Arbol<T>> obtenerLevel(int level);

    public int obtenerNumLevel(A_Arbol<T> arbol);

    public void vaciar();

    public String levelsToString();

    @Override
    public String toString();    
}
