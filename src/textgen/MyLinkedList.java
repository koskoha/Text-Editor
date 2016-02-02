package textgen;

import java.util.AbstractList;
import java.util.LinkedList;


/**
 * A class that implements a doubly linked list
 *
 * @param <E> The type of the elements stored in the list
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class MyLinkedList<E> extends AbstractList<E> {
    LLNode<E> head;
    LLNode<E> tail;
    int size = 0;

    /**
     * Create a new empty LinkedList
     */
    public MyLinkedList() {
        // TODO: Implement this method
    /*    size =0;
        head = new LLNode<E>(null);
        tail = new LLNode<E>(null);
        head.next = tail;
        tail.prev = head;*/
    }

    /**
     * Appends an element to the end of the list
     *
     * @param element The element to add
     */
    public boolean add(E element) {
        // TODO: Implement this method
        if (element == null){
            throw  new NullPointerException();
        }
        LLNode<E> l = tail;
        LLNode<E> newNode = new LLNode<>(element);
        newNode.prev = l;
        newNode.next = null;
        tail = newNode;
        if (l == null)
            head = newNode;
        else
            l.next = newNode;
        size++;

        return true;
    }

    /**
     * Get the element at position index
     *
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E get(int index) {
        // TODO: Implement this method.
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("wrong index" + index);
        } else {
            return llNode(index).data;
        }
    }

    /**
     * Add an element to the list at the specified index
     *
     * @param index   where the element should be added
     * @param element The element to add
     */
    public void add(int index, E element) {
        // TODO: Implement this method
        if (element == null){
            throw new NullPointerException();
        }
        else if (index == size){
            add(element);
        }else if (index == 0 && size == 0) {
            add(element);
        } else {
            if (!(index > 0 && index < size)) {
                throw new IndexOutOfBoundsException();
            } else {
                LLNode<E> succ = llNode(index);
                final LLNode<E> pred = succ.prev;
                final LLNode<E> newNode = new LLNode<>(element);
                newNode.prev = pred;
                newNode.next = succ;
                succ.prev = newNode;
                if (pred == null)
                    head = newNode;
                else
                    pred.next = newNode;
                size++;
            }
        }
    }


    /**
     * Return the size of the list
     */

    public int size() {
        // TODO: Implement this method
        return size;
    }

    /**
     * Remove a node at the specified index and return its data element.
     *
     * @param index The index of the element to remove
     * @return The data element removed
     * @throws IndexOutOfBoundsException If index is outside the bounds of the list
     */
    public E remove(int index) {
        // TODO: Implement this method
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("wrong index" + index);
        } else {
            LLNode<E> x = llNode(index);
            final E element = x.data;
            final LLNode<E> next = x.next;
            final LLNode<E> prev = x.prev;

            if (prev == null) {
                head = next;
            } else {
                prev.next = next;
                x.prev = null;
            }

            if (next == null) {
                tail = prev;
            } else {
                next.prev = prev;
                x.next = null;
            }

            x.data = null;
            size--;
            return element;
        }

    }

    /**
     * Set an index position in the list to a new element
     *
     * @param index   The index of the element to change
     * @param element The new element
     * @return The element that was replaced
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public E set(int index, E element) {
        if (element == null){
            throw new NullPointerException();
        }
        // TODO: Implement this
        if (element == null){
            throw new NullPointerException();
        }
        if (!(index >= 0 && index < size)) {
            throw new IndexOutOfBoundsException("wrong index" + index);
        } else {
            LLNode<E> succ = llNode(index);
            LLNode<E> x = succ;
            E oldVal = x.data;
            x.data = element;
            return oldVal;
        }
    }

    LLNode<E> llNode(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            LLNode<E> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            LLNode<E> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
}

class LLNode<E> {
    LLNode<E> prev;
    LLNode<E> next;
    E data;

    // TODO: Add any other methods you think are useful here
    // E.g. you might want to add another constructor
    LLNode(E element) {
        this.data = element;
        this.next = null;
        this.prev = null;
    }

  /*  LLNode(LLNode<E> prev, E element, LLNode<E> next) {
        this.data = element;
        this.next = next;
        this.prev = prev;
    }*/

}
