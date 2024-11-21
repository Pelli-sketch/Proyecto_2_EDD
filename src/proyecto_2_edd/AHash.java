/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */
public interface AHash {
    
    void setTam(int tam);
    
    int getTam();

    boolean insertar(String clave, T valor);

    boolean insertar(String clave);

    T buscar(String clave);

    ListaEnlazada<T> buscarTodos(String clave);

    String toString();
    
}
