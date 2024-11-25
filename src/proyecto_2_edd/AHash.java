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
 * Interfaz genérica AHash<T>
 * Define los métodos básicos para una estructura de tabla hash genérica que utiliza claves para almacenar y buscar valores.
 *
 * @param <T> El tipo de datos que se almacenará en la tabla hash.
 */
public interface AHash<T> {
    /**
     * Establece el tamaño de la tabla hash.
     *
     * @param tam El nuevo tamaño de la tabla hash.
     */
    void setTam(int tam);
    /**
     * Obtiene el tamaño actual de la tabla hash.
     *
     * @return El tamaño de la tabla hash.
     */
    int obtenerTam();
        /**
     * Inserta un valor en la tabla hash asociado con una clave específica.
     *
     * @param clave La clave asociada al valor.
     * @param valor El valor que se desea almacenar.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    boolean insertar(String clave, T valor);
    /**
     * Inserta un valor por defecto (o vacío) asociado a una clave específica.
     *
     * @param clave La clave con la que se desea realizar la inserción.
     * @return true si la inserción fue exitosa, false en caso contrario.
     */
    boolean insertar(String clave);
    /**
     * Busca un valor en la tabla hash utilizando una clave específica.
     *
     * @param clave La clave con la que se desea buscar.
     * @return El valor asociado a la clave, o null si no se encuentra.
     */
    T buscar(String clave);
    /**
     * Busca todos los valores asociados a una clave específica.
     *
     * @param clave La clave con la que se desea buscar.
     * @return Una lista enlazada con todos los valores encontrados, o una lista vacía si no hay resultados.
     */
    ListaEnlazada<T> buscarTodos(String clave);
    /**
     * Retorna una representación en forma de cadena de la tabla hash.
     *
     * @return Una cadena con la información de la tabla hash.
     */
    @Override
    String toString();
    
}
