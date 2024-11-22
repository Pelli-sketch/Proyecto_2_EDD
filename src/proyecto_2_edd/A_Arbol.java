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

    public T getValor();

    public void setValor(T valor);

    public A_Arbol<T> getRaiz();

    public boolean addHijo(A_Arbol<T> hijo);

    public ListaEnlazada<A_Arbol<T>> getHijos();

    public A_Arbol<T> getHijo(int i);

    public void setComparador(AComparador<T> comparador);

    public ListaEnlazada<A_Arbol<T>> searchHijo(T valor);

    public ListaEnlazada<A_Arbol<T>> search(T valor);

    public A_Arbol<T> getPadre(A_Arbol<T> arbol);

    public ListaEnlazada<A_Arbol<T>> getAscends(A_Arbol<T> arbol);

    public ListaEnlazada<A_Arbol<T>> getDescends(A_Arbol<T> arbol);

    public ListaEnlazada<A_Arbol<T>>[] getLevels();

    public ListaEnlazada<A_Arbol<T>> getLevel(int level);

    public int getNumLevel(A_Arbol<T> arbol);

    public void vaciar();

    public String levelsToString();

    @Override
    public String toString();    
}
