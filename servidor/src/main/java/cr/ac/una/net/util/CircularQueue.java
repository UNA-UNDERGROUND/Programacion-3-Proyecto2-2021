package cr.ac.una.net.util;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Clase que implementa una cola circular.
 */
public class CircularQueue<T> {

    public CircularQueue() {

    }

    /**
     * agrega un elemento al final de la cola
     * 
     * @param item
     */
    public void push(T item) {
        queue.add(item);
    }

    /**
     * elimina el primer elemento de la cola
     */
    public T pop() {
        return queue.poll();
    }

    /**
     * envia el primer elemento de la cola al final
     */
    public T rotate() {
        T val = pop();
        queue.add(val);
        return val;
    }

    /**
     * elimina un elemento de la cola
     */
    public void delete(Object o) {
        queue.remove(o);
    }

    private ConcurrentLinkedQueue<T> queue = new ConcurrentLinkedQueue<>();

}
