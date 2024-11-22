/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */
public class ArrayList<T> implements Alist<T> {
    /**
     * Tamaño por defecto inicial de la lista
     */
    private final int DEFAULT_TAM_INICIAL = 1;
    /**
     * Máximo del tamaño inicial
     */
    private final int MAX_TAM_INICIAL = 50;
    /**
     * Tamaño por defecto del bloque en que crece el arreglo
     */
    private final int DEFAULT_TAM_BLOQUE = 5;
    /**
     * Máximo del tamaño del bloque
     */
    private final int MAX_TAM_BLOQUE = 50;
    /**
     * Arreglo de elementos de la lista
     */
    private T[] arreglo;
    /**
     * Tamaño de la lista
     */
    private int tamLista;
    /**
     * Tamaño inicial del arreglo de la lista
     */
    private int tamInicial;
    /**
     * Tamaño del bloque en que crece el arreglo de la lista
     */
    private int tamBloque;

    /**
     * Constructor por defecto, se usa el tamaño inicial
     * y el tamaño del bloque por defecto
     */

    @SuppressWarnings("unchecked")
    public ArrayList() {
        this.setTamInicial(DEFAULT_TAM_INICIAL);
        this.setTamBloque(DEFAULT_TAM_BLOQUE);
        arreglo = (T[]) new Object[tamInicial];
        tamLista = 0;
    }

    /**
     * Constructor con el tamaño del bloque a usar
     * El tamaño inicial es el por defecto
     * 
     * @param tamBloque el tamaño del bloque
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int tamBloque) {
        this.setTamBloque(tamBloque);
        this.setTamInicial(DEFAULT_TAM_INICIAL);
        arreglo = (T[]) new Object[tamInicial];
        tamLista = 0;
    }

    /**
     * Constructor con los valores de tamaño inicial y tamaño del bloque
     * 
     * @param tamInicial el tamaño inicial
     * @param tamBloque  el tamaño del bloque
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int tamInicial, int tamBloque) {
        this.setTamInicial(tamInicial);
        this.setTamBloque(tamBloque);
        arreglo = (T[]) new Object[tamInicial];
        tamLista = 0;
    }

    /**
     * Constructor con un array de elementos como argumento.
     * Crea la lista con todos los elementos agregados
     */

    @SuppressWarnings("unchecked")
    public ArrayList(T[] valores) {
        this.setTamInicial(DEFAULT_TAM_INICIAL);
        this.setTamBloque(DEFAULT_TAM_BLOQUE);
        this.tamLista = 0;
        arreglo = (T[]) new Object[tamInicial];
        for (T item : valores) {
            this.agregar(item);
        }
    }

    /**
     * Constructor con una lista de elementos como argumento.
     * Crea la lista con todos los elementos agregados
     * 
     * @param valores
     */
    @SuppressWarnings("unchecked")
    public ArrayList(Alist<T> valores) {
        this.setTamInicial(DEFAULT_TAM_INICIAL);
        this.setTamBloque(DEFAULT_TAM_BLOQUE);
        this.tamLista = 0;
        arreglo = (T[]) new Object[tamInicial];
        for (int i = 0; i < valores.size(); i++) {
            this.agregar(valores.get(i));
        }
    }

    /**
     * El tamaño del arreglo de la lista, es un método package private
     * ya que es usado para hacer pruebas y verificar que funciona correctamente
     * 
     * @return el tamaño del arreglo
     */
    int getTamArreglo() {
        return arreglo.length;
    }

    /**
     * Setter de tamaño inicial
     * 
     * @param tamInicial el tamaño inicial
     */
    public void setTamInicial(int tamInicial) {
        if (tamInicial <= 0 || tamInicial > MAX_TAM_INICIAL) {
            this.tamInicial = DEFAULT_TAM_INICIAL;
        }
        this.tamInicial = tamInicial;
    }

    /**
     * Setter de tamaño del bloque
     * 
     * @param tamBloque el tamaño del bloque
     */
    public void setTamBloque(int tamBloque) {
        if (tamBloque <= 0 || tamBloque > MAX_TAM_BLOQUE) {
            this.tamBloque = DEFAULT_TAM_BLOQUE;
        }
        this.tamBloque = tamBloque;
    }

    /**
     * Retorna el tamaño de la lista
     * 
     * @return el tamaño de la lista
     */
    @Override
    public int size() {
        return tamLista;
    }

    /**
     * Retorna true si la lista es vacía, false en caso contrario
     * 
     * @return true si la lista es vacía, false en caso contrario
     */
    @Override
    public boolean vacia() {
        return tamLista == 0;
    }

    /**
     * Transforma valores negativos de un index, para ser usado desde la cola hacia
     * la cabeza de la lista. en este caso si el indice es igual a -1 entonces
     * es igual a: size() - 1 (El ultimo indice de la lista).
     * 
     * @param index el indice
     * @return el nuevo indice
     */
    protected int transformarIndex(int index) {
        if (index < 0 && index >= -tamLista) {
            return tamLista + index;
        }
        return index;
    }

    /**
     * Inserta un elemento en la lista en la posición indicada, retorna true si se
     * pudo insertar, false en caso contrario
     * 
     * @param index el indice en la lista donde insertar el elemento
     * @param valor el valor a insertar
     * @return true si se pudo insertar, false en caso contrario
     */

    @SuppressWarnings("unchecked")
    @Override
    public boolean insertar(int index, T valor) {
        index = transformarIndex(index);
        if (index < 0 || index > tamLista) {
            return false;
        }
        if (tamLista == arreglo.length) {
            T[] temp = (T[]) new Object[arreglo.length + tamBloque];
            for (int i = 0; i < tamLista; i++) {
                temp[i] = arreglo[i];
            }
            arreglo = temp;
        }
        for (int i = tamLista; i > index; i--) {
            arreglo[i] = arreglo[i - 1];
        }
        arreglo[index] = valor;
        tamLista++;
        return true;
    }

    /**
     * Inserta un elemento al final de la lista. Retorna true si se pudo insertar,
     * false en caso contrario
     * 
     * @param valor el valor a insertar
     * @return true si se pudo insertar, false en caso contrario
     */

    @Override
    public boolean agregar(T valor) {
        return insertar(tamLista, valor);
    }

    /**
     * Agrega los elementos de un arreglo a la lista
     * 
     * @param valor el array con los elementos a agregar
     */
    @Override
    public void agregar(T[] valor) {
        for (int i = 0; i < valor.length; i++) {
            this.agregar(valor[i]);
        }
    }

    /**
     * Agrega los elementos de una lista a la lista
     * 
     * @param valor la lista con los elementos a agregar
     * 
     */
    @Override
    public void agregar(Alist<T> valor) {
        for (int i = 0; i < valor.size(); i++) {
            this.agregar(valor.get(i));
        }
    }

    /**
     * Retorna el elemento de la lista en la posición indicada, null en caso
     * contrario
     * 
     * @param index el indice del elemento
     * @return el elemento
     */
    @Override
    public T get(int index) {
        index = transformarIndex(index);
        if (index < 0 || index >= tamLista) {
            return null;
        }
        return arreglo[index];
    }

    /**
     * Modifica el elemento de la lista en la posición indicada, retorna true si se
     * pudo modificar, false en caso contrario
     * 
     * @param index el indice del elemento
     * @param valor el nuevo valor
     * @return true si se pudo modificar, false en caso contrario
     */
    @Override
    public boolean set(int index, T valor) {
        index = transformarIndex(index);
        if (index < 0 || index >= tamLista) {
            return false;
        }
        arreglo[index] = valor;
        return true;
    }

    /**
     * Compacta el arreglo de la lista, evita que el arreglo se llene de espacios
     * no usados. Solo compacta si la diferencia entre el tamaño de la lista y
     * el tamaño del arreglo es mayor que el tamaño del bloque.
     */
    @SuppressWarnings("unchecked")
    public void compactar() {
        T[] temp = (T[]) new Object[tamLista];
        for (int i = 0; i < tamLista; i++) {
            temp[i] = arreglo[i];
        }
        arreglo = temp;
    }

    /**
     * Elimina el elemento de la lista en la posición indicada, retorna el valor
     * que se elimino, null en caso contrario
     * 
     * @param index el indice del elemento
     * @return el valor que se elimino
     */
    @Override
    public T remover(int index) {
        index = transformarIndex(index);
        if (index < 0 || index >= tamLista) {
            return null;
        }
        T temp = arreglo[index];
        for (int i = index; i < tamLista - 1; i++) {
            arreglo[i] = arreglo[i + 1];
        }
        tamLista--;
        if (arreglo.length - tamLista > tamBloque) {
            compactar();
        }
        return temp;
    }

    /**
     * Vacia la lista.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void vaciar() {
        arreglo = (T[]) new Object[tamInicial];
        tamLista = 0;
    }

    /**
     * Retorna una copia de la lista
     * 
     * @return una copia de la lista
     */
    public ArrayList<T> copiar() {
        ArrayList<T> temp = new ArrayList<>();
        if (this.size() == 0) {
            return temp;
        }
        for (int i = 0; i < this.size(); i++) {
            temp.agregar(this.get(i));
        }
        return temp;
    }

    /**
     * Compara si la lista enviada por parámetro es igual a la lista actual.
     * Usa un comparador para comparar los elementos de ambas listas.
     * 
     * @param list       la lista a comparar
     * @param comparador una expresión lambda con el comparador. @see AComparador
     * @return true si son iguales, false en caso contrario
     */

    public boolean equals(Alist<T> list, AComparador<T> comparador) {
        if (list == null) {
            return false;
        }
        if (this.size() != list.size()) {
            return false;
        }
        if (this == list) {
            return true;
        }
        if (this.size() == 0) {
            return true;
        }
        for (int i = 0; i < this.size(); i++) {
            if (comparador.compareTo(this.get(i), list.get(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara si la lista enviada por parámetro es igual a la lista actual.
     * Sirve solo si los elementos de la lista son de tipo String o Number.
     * Si no lo son, devuelve false. En este caso deberías usar equals(Alist<T>,
     * AComparador<T>).
     * 
     * @see equals(Alist<T>, AComparador<T>)
     * 
     * @param list la lista a comparar
     * @return true si son iguales, false en caso contrario
     */
    public boolean equals(Alist<T> list) {
        if (list == null) {
            return false;
        }
        if (this.size() != list.size()) {
            return false;
        }
        if (this.get(0) instanceof String && list.get(0) instanceof String) {
            return this.equals(list, (a, b) -> ((String) a).compareTo((String) b));
        }
        if (this.get(0) instanceof Number && list.get(0) instanceof Number) {
            return this.equals(list, (a, b) -> {
                Double num1 = ((Number) a).doubleValue();
                Double num2 = ((Number) b).doubleValue();
                return num1.compareTo(num2);
            });
        }
        return false;
    }

    /**
     * Ordena la lista según el comparador indicado.
     * 
     * @param comparador expresión lambda con el comparador @see AComparador
     * 
     */
    public void ordenar(AComparador<T> comparador) {
        if (this.size() == 0 || this.size() == 1) {
            return;
        }
        T aux = null;
        for (int i = 0; i < this.size(); i++) {
            for (int j = i + 1; j < this.size(); j++) {
                if (comparador.compareTo(this.get(i), this.get(j)) > 0) {
                    aux = this.get(i);
                    this.set(i, this.get(j));
                    this.set(j, aux);
                }
            }
        }
    }

    /**
     * Ordena la lista si los elementos de la lista son de tipo String o Number
     * No ordena si la lista tiene elementos de otro tipo. Usar ordenar(AComparador)
     * para estos casos. @see ordenar(AComparador)
     */
    public void ordenar() {
        if (this.size() == 0 || this.size() == 1) {
            return;
        }
        if (this.get(0) instanceof String) {
            this.ordenar((a, b) -> ((String) a).compareTo((String) b));
            return;
        }
        if (this.get(0) instanceof Number) {
            this.ordenar((a, b) -> {
                Double num1 = ((Number) a).doubleValue();
                Double num2 = ((Number) b).doubleValue();
                return num1.compareTo(num2);
            });
            return;
        }
    }

    /**
     * Invierte la lista
     */
    public void invertir() {
        if (this.size() == 0 || this.size() == 1) {
            return;
        }
        T aux = null;
        for (int i = 0; i < this.size() / 2; i++) {
            aux = this.get(i);
            this.set(i, this.get(this.size() - i - 1));
            this.set(this.size() - i - 1, aux);
        }
    }

    /**
     * Busca un elemento en la lista y retorna su posición o -1 si no lo encuentra.
     * Utiliza un comparador.
     * 
     * @param valor      el elemento a buscar
     * @param comparador el comparador @see AComparador
     * 
     * @return la posición del elemento o -1
     */
    public int buscar(T valor, AComparador<T> comparador) {
        for (int i = 0; i < this.size(); i++) {
            if (comparador.compareTo(this.get(i), valor) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca un elemento en la lista y retorna su índice o -1 si no lo encuentra.
     * No utiliza un comparador. Solo funciona para elementos de la lista que
     * son de tipo String o Number. Si son de otro tipo devuelve -1. Usar
     * buscar(T, AComparador) para estos casos. @see buscar(T, AComparador)
     * 
     * @param valor el elemento a buscar
     * 
     * @return la dirección del elemento o -1
     */
    public int buscar(T valor) {
        if (this.size() == 0) {
            return -1;
        }
        if (this.get(0) instanceof String && valor instanceof String) {
            return this.buscar(valor, (a, b) -> ((String) a).compareTo((String) b));
        }
        if (this.get(0) instanceof Number && valor instanceof Number) {
            return this.buscar(valor, (a, b) -> {
                Double num1 = ((Number) a).doubleValue();
                Double num2 = ((Number) b).doubleValue();
                return num1.compareTo(num2);
            });
        }
        return -1;
    }

    /**
     * Retorna un string con la representación de la lista
     */
    @Override
    public String toString() {
        if (arreglo == null) {
            String txt = "List(0/0): ";
            txt += "//";
            return txt;
        }
        String txt = "List(" + tamLista + "): ";

        for (int i = 0; i < tamLista; i++) {
            txt += arreglo[i] + " -> ";
        }
        txt += "//";
        return txt;
    }
}
