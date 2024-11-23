/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto_2_edd;

/**
 *
 * @author pablo
 */
/**
 *
 * @author pablo
 */
public class ListaDinamica<T> implements Alist<T> {
    private static final int TAM_INICIAL_DEFECTO = 1;
    private static final int TAM_INICIAL_MAXIMO = 50;
    private static final int TAM_BLOQUE_DEFECTO = 5;
    private static final int TAM_BLOQUE_MAXIMO = 50;

    private T[] elementos;
    private int cantidadElementos;
    private int tamanoInicial;
    private int tamanoBloque;

    @SuppressWarnings("unchecked")
    public ListaDinamica() {
        this(TAM_INICIAL_DEFECTO, TAM_BLOQUE_DEFECTO);
    }

    @SuppressWarnings("unchecked")
    public ListaDinamica(int tamanoBloque) {
        this(TAM_INICIAL_DEFECTO, tamanoBloque);
    }

    @SuppressWarnings("unchecked")
    public ListaDinamica(int tamanoInicial, int tamanoBloque) {
        this.tamanoInicial = validarTamanoInicial(tamanoInicial);
        this.tamanoBloque = validarTamanoBloque(tamanoBloque);
        this.elementos = (T[]) new Object[this.tamanoInicial];
        this.cantidadElementos = 0;
    }

    @SuppressWarnings("unchecked")
    public ListaDinamica(T[] elementosIniciales) {
        this();
        for (T elemento : elementosIniciales) {
            agregar(elemento);
        }
    }

    @SuppressWarnings("unchecked")
    public ListaDinamica(Alist<T> lista) {
        this();
        for (int i = 0; i < lista.size(); i++) {
            agregar(lista.obtener(i));
        }
    }

    private int validarTamanoInicial(int tamano) {
        return Math.min(Math.max(tamano, 1), TAM_INICIAL_MAXIMO);
    }

    private int validarTamanoBloque(int tamano) {
        return Math.min(Math.max(tamano, 1), TAM_BLOQUE_MAXIMO);
    }

    // Métodos adicionales para agregar, obtener, etc., deberían ir aquí


    /**
     * Método para establecer el tamaño inicial de la lista.
     * 
     * @param tamanoInicial el tamaño inicial deseado
     */
    public void definirTamanoInicial(int tamanoInicial) {
        if (tamanoInicial <= 0 || tamanoInicial > TAM_INICIAL_MAXIMO) {
            this.tamanoInicial = TAM_INICIAL_DEFECTO;
        } else {
            this.tamanoInicial = tamanoInicial;
        }
    }

    /**
     * Método para establecer el tamaño del bloque de crecimiento.
     * 
     * @param tamanoBloque el tamaño del bloque deseado
     */
    public void definirTamanoBloque(int tamanoBloque) {
        if (tamanoBloque <= 0 || tamanoBloque > TAM_BLOQUE_MAXIMO) {
            this.tamanoBloque = TAM_BLOQUE_DEFECTO;
        } else {
            this.tamanoBloque = tamanoBloque;
        }
    }

    /**
     * Método que devuelve la cantidad de elementos en la lista.
     * 
     * @return el tamaño actual de la lista
     */
    @Override
    public int size() {
        return cantidadElementos;
    }

    /**
     * Método que verifica si la lista está vacía.
     * 
     * @return true si la lista está vacía, false de lo contrario
     */
    @Override
    public boolean vacia() {
        return cantidadElementos == 0;
    }

    /**
     * Convierte índices negativos en índices válidos para la lista.
     * Si el índice es -1, se convierte en el último índice.
     * 
     * @param indice el índice a transformar
     * @return el índice transformado
     */
    protected int convertirIndice(int indice) {
        if (indice < 0 && indice >= -cantidadElementos) {
            return cantidadElementos + indice;
        }
        return indice;
    }

    /**
     * Inserta un elemento en la posición especificada de la lista.
     * 
     * @param indice el índice en la lista donde se debe insertar el elemento
     * @param valor el elemento a insertar
     * @return true si la inserción fue exitosa, false de lo contrario
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean insertar(int indice, T valor) {
        indice = convertirIndice(indice);
        if (indice < 0 || indice > cantidadElementos) {
            return false;
        }
        if (cantidadElementos == elementos.length) {
            T[] nuevoArreglo = (T[]) new Object[elementos.length + tamanoBloque];
            System.arraycopy(elementos, 0, nuevoArreglo, 0, cantidadElementos);
            elementos = nuevoArreglo;
        }
        System.arraycopy(elementos, indice, elementos, indice + 1, cantidadElementos - indice);
        elementos[indice] = valor;
        cantidadElementos++;
        return true;
    }

    /**
     * Agrega un elemento al final de la lista.
     * 
     * @param valor el elemento a agregar
     * @return true si la adición fue exitosa, false de lo contrario
     */
    @Override
    public boolean agregar(T valor) {
        return insertar(cantidadElementos, valor);
    }

    /**
     * Añade los elementos de un arreglo a la lista.
     * 
     * @param elementos el arreglo con los elementos a agregar
     */
    @Override
    public void agregarDesdeArreglo(T[] elementos) {
        for (T elemento : elementos) {
            this.agregar(elemento);
        }
    }

    /**
     * Añade los elementos de otra lista a la lista actual.
     * 
     * @param lista la lista con los elementos a agregar
     */
    @Override
    public void agregarDesdeLista(Alist<T> lista) {
        for (int i = 0; i < lista.size(); i++) {
            this.agregar(lista.obtener(i));
        }
    }

    /**
     * Devuelve el elemento en la posición especificada de la lista,
     * o null si la posición es inválida.
     * 
     * @param indice el índice del elemento
     * @return el elemento en la posición indicada
     */
    @Override
    public T obtener(int indice) {
        indice = convertirIndice(indice);
        if (indice < 0 || indice >= cantidadElementos) {
            return null;
        }
        return elementos[indice];
    }

    /**
     * Actualiza el elemento en la posición indicada de la lista.
     * Retorna true si la modificación fue exitosa, false de lo contrario.
     * 
     * @param indice el índice del elemento
     * @param nuevoValor el nuevo valor para el elemento
     * @return true si se pudo modificar, false en caso contrario
     */
    @Override
    public boolean actualizar(int indice, T nuevoValor) {
        indice = convertirIndice(indice);
        if (indice < 0 || indice >= cantidadElementos) {
            return false;
        }
        elementos[indice] = nuevoValor;
        return true;
    }

    /**
     * Compacta el arreglo de la lista para eliminar espacios no utilizados.
     * La compactación ocurre solo si la diferencia entre el tamaño de la lista
     * y el tamaño del arreglo excede el tamaño del bloque.
     */
    @SuppressWarnings("unchecked")
    public void compactarArreglo() {
        if (elementos.length - cantidadElementos > tamanoBloque) {
            T[] nuevoArreglo = (T[]) new Object[cantidadElementos];
            System.arraycopy(elementos, 0, nuevoArreglo, 0, cantidadElementos);
            elementos = nuevoArreglo;
        }
    }

    /**
     * Elimina el elemento en la posición indicada de la lista.
     * Retorna el valor eliminado, o null si la posición es inválida.
     * 
     * @param indice el índice del elemento a eliminar
     * @return el valor eliminado
     */
    @Override
    public T eliminar(int indice) {
        indice = convertirIndice(indice);
        if (indice < 0 || indice >= cantidadElementos) {
            return null;
        }
        T valorEliminado = elementos[indice];
        System.arraycopy(elementos, indice + 1, elementos, indice, cantidadElementos - indice - 1);
        cantidadElementos--;
        compactarArreglo();
        return valorEliminado;
    }

    /**
     * Vacía la lista, eliminando todos los elementos.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void vaciarLista() {
        elementos = (T[]) new Object[tamanoInicial];
        cantidadElementos = 0;
    }

    /**
     * Devuelve una copia de la lista actual.
     * 
     * @return una nueva lista que es una copia de la actual
     */
    public ListaDinamica<T> copiarLista() {
        ListaDinamica<T> copia = new ListaDinamica<>();
        for (int i = 0; i < this.size(); i++) {
            copia.agregar(this.obtener(i));
        }
        return copia;
    }

    /**
     * Compara si la lista proporcionada es igual a la lista actual.
     * Utiliza un comparador para comparar los elementos de ambas listas.
     * 
     * @param lista la lista a comparar
     * @param comparador un comparador para comparar elementos
     * @return true si son iguales, false de lo contrario
     */
    public boolean sonIguales(Alist<T> lista, AComparador<T> comparador) {
        if (lista == null || this.size() != lista.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (comparador.compareTo(this.obtener(i), lista.obtener(i)) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compara si la lista proporcionada es igual a la lista actual.
     * Solo es aplicable si los elementos son de tipo String o Number.
     * En caso contrario, devuelve false. Para comparaciones más generales,
     * se debe usar el método sonIguales(Alist<T>, AComparador<T>).
     * 
     * @param lista la lista a comparar
     * @return true si son iguales, false en caso contrario
     */
    public boolean sonIguales(Alist<T> lista) {
        if (lista == null || this.size() != lista.size()) {
            return false;
        }
        if (this.obtener(0) instanceof String && lista.obtener(0) instanceof String) {
            return sonIguales(lista, (a, b) -> ((String) a).compareTo((String) b));
        }
        if (this.obtener(0) instanceof Number && lista.obtener(0) instanceof Number) {
            return sonIguales(lista, (a, b) -> {
                Double num1 = ((Number) a).doubleValue();
                Double num2 = ((Number) b).doubleValue();
                return num1.compareTo(num2);
            });
        }
        return false;
    }

    /**
     * Ordena los elementos de la lista según el comparador proporcionado.
     * 
     * @param comparador una expresión lambda que define el criterio de comparación @see AComparador
     */
    public void ordenarLista(AComparador<T> comparador) {
        int tamaño = this.size();
        if (tamaño < 2) {
            return;
        }
        for (int i = 0; i < tamaño; i++) {
            for (int j = i + 1; j < tamaño; j++) {
                if (comparador.compareTo(this.obtener(i), this.obtener(j)) > 0) {
                    T temporal = this.obtener(i);
                    this.actualizar(i, this.obtener(j));
                    this.actualizar(j, temporal);
                }
            }
        }
    }

    /**
     * Ordena la lista si los elementos son de tipo String o Number.
     * No realiza la ordenación si los elementos son de otro tipo.
     * Para otros casos, utilizar el método ordenarLista(AComparador).
     * 
     * @see ordenarLista(AComparador)
     */
    public void ordenar() {
        if (this.size() < 2) {
            return;
        }
        if (this.obtener(0) instanceof String) {
            this.ordenarLista((a, b) -> ((String) a).compareTo((String) b));
        } else if (this.obtener(0) instanceof Number) {
            this.ordenarLista((a, b) -> {
                Double num1 = ((Number) a).doubleValue();
                Double num2 = ((Number) b).doubleValue();
                return num1.compareTo(num2);
            });
        }
    }

    /**
     * Invierte el orden de los elementos en la lista.
     */
    public void invertir() {
        int tamaño = this.size();
        if (tamaño < 2) {
            return;
        }
        for (int i = 0; i < tamaño / 2; i++) {
            T temporal = this.obtener(i);
            this.actualizar(i, this.obtener(tamaño - i - 1));
            this.actualizar(tamaño - i - 1, temporal);
        }
    }

    /**
     * Busca un elemento en la lista y devuelve su posición, o -1 si no se encuentra.
     * Utiliza un comparador para la búsqueda.
     * 
     * @param valor      el elemento a buscar
     * @param comparador el comparador para definir el criterio de búsqueda @see AComparador
     * 
     * @return la posición del elemento o -1 si no se encuentra
     */
    public int buscarElemento(T valor, AComparador<T> comparador) {
        for (int i = 0; i < this.size(); i++) {
            if (comparador.compareTo(this.obtener(i), valor) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Busca un elemento en la lista y devuelve su índice, o -1 si no se encuentra.
     * Este método no utiliza un comparador y solo funciona para elementos de tipo
     * String o Number. Para otros tipos, se debe usar buscarElemento(T, AComparador).
     * 
     * @param valor el elemento a buscar
     * 
     * @return la posición del elemento o -1 si no se encuentra
     */
    public int buscarElemento(T valor) {
        if (this.size() == 0) {
            return -1;
        }
        if (this.obtener(0) instanceof String && valor instanceof String) {
            return this.buscarElemento(valor, (a, b) -> ((String) a).compareTo((String) b));
        } else if (this.obtener(0) instanceof Number && valor instanceof Number) {
            return this.buscarElemento(valor, (a, b) -> {
                Double num1 = ((Number) a).doubleValue();
                Double num2 = ((Number) b).doubleValue();
                return num1.compareTo(num2);
            });
        }
        return -1;
    }

    /**
     * Devuelve una representación en forma de cadena de la lista.
     * 
     * @return una cadena que representa la lista
     */
    @Override
    public String toString() {
        if (elementos == null) {
            return "Lista(0/0): //";
        }
        StringBuilder representación = new StringBuilder("Lista(" + cantidadElementos + "): ");
        for (int i = 0; i < cantidadElementos; i++) {
            representación.append(elementos[i]).append(" -> ");
        }
        representación.append("//");
        return representación.toString();
    }
}
