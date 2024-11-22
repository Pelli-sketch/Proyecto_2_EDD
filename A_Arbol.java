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
    
    public int numElementos();

    public int altura();

    public T getValor();

    public void setValor(T valor);

    public A_Arbol<T> getRaiz();

    public boolean agregarHijo(A_Arbol<T> hijo);

    public ListaEnlazada<A_Arbol<T>> getHijos();

    public A_Arbol<T> getHijo(int i);

    public void setComparador(AComparador<T> comparador);

    public ListaEnlazada<A_Arbol<T>> buscarHijo(T valor);

    public ListaEnlazada<A_Arbol<T>> buscar(T valor);

    public A_Arbol<T> getPadre(A_Arbol<T> arbol);

    public ListaEnlazada<A_Arbol<T>> getAscendentes(A_Arbol<T> arbol);

    public ListaEnlazada<A_Arbol<T>> getDescendientes(A_Arbol<T> arbol);

    public ListaEnlazada<A_Arbol<T>>[] getNiveles();

    public ListaEnlazada<A_Arbol<T>> getNivel(int nivel);

    public int getNumNiveL(A_Arbol<T> arbol);

    public void vaciar();

    public String nivelesToString();

    @Override
    public String toString();    
}
