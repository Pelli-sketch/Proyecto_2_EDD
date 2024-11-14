package proyecto_2_edd;

public class Lista<T> {
        private NodoLista<T> pFirst;
        private NodoLista<T> pLast;
        private int size;
    
        public Lista() {
            this.pFirst = null;
            this.pLast = null;
            this.size = 0;
        }
    
        public NodoLista<T> getpFirst() {
            return pFirst;
        }
    
        public NodoLista<T> getpLast() {
            return pLast;
        }
    
        public int getSize() {
            return size;
        }
    
        public void InsertarFinal(T valor) {
            if (pFirst == null) {
                pFirst = new NodoLista<>(valor, pLast);
                pLast = pFirst;
            } else {
                pLast.setpNext(new NodoLista<>(valor, null));
                pLast = pLast.getpNext();
    
            }
            size++;
        }
    
    
    } 

    class NodoLista<T> {
        private T data;
        private NodoLista<T> pNext; 
    
        public NodoLista(T data, NodoLista<T> pNext) {
            this.data = data;
            this.pNext = pNext;
        }
    
        public T getData() {
            return data;
        }
    
        public NodoLista<T> getpNext() {
            return pNext;
        }
    
        public void setData(T data) {
            this.data = data;
        }
    
        public void setpNext(NodoLista<T> pNext) {
            this.pNext = pNext;
        }
    
    }
    