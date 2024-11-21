/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author sebma
 */
/** 
 * Clase hashtable como estructura de datos secundaria, declarando dos variables,
* T y Y, las cuales representan los valores key y value, del mismo modo se implementan
* los atributos de tamaño que indican la extension del array, luego el array que se
* encarga de almacenar los datos del bucket
 * @author sebma
 * @param <T>
 * @param <Y> 
 */
//public class HashTable<T extends Comparable, Y> {

//    Lista<Par<T, Y>> pares;
//    int tamannoBucket;
//    Lista<Par<T, Y>>[] buckets;
//    int tamanno;
//
///**
// * Constructor de la clase hasthable, necesario para inizializar las variables 
// */
//    public HashTable() {
//        this.pares = new Lista();
//        this.tamannoBucket = 50;
//        this.buckets = new Lista[tamannoBucket];
//        this.tamanno = 0;
//    }
//
///**
// * Aplicando la funcion hash, retorna el indice segun la clave que estemos pasando,
// * tomando el valor key (clave) y convirtiendolo a un entero
// * @param clave
// * @return 
// */
//    private int hash(T clave) {
//        return clave.hashCode()%tamannoBucket;
//    }
//
///** Funcion encargada de tomar el valor key (clave) y retornarlo como un Par,
// * en caso de encontrarlo, la función retorna la data del nodo buscado, en el 
// * caso contrario de no ser encontrado, la función retorna null
// * @param clave
// * @return 
// */
//    private Par findClave(T clave) {
//        int indice = hash(clave);
//        Lista<Par<T, Y>> bucket = this.buckets[indice];
//        NodoLista<Par<T, Y>> aux = bucket.getpFirst();
//        while (aux != null) {
//            if (aux.getData().primero.equals(clave)) {
//            return aux.getData();
//            } 
//            aux = aux.getpNext();
//            
//        }
//        return null;
//    }
//}

public class HashTable<T> implements AHash<T> {
    
}
