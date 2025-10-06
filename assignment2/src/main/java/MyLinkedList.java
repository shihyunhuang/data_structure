public class MyLinkedList<T> {
    // Define node
    private static class Node<T>{
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size; 

    // Constructor
    public MyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    // Check empty and size
    public boolean isEmpty(){ return size == 0; }
    public int size(){ return size; }

    // Add method
    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) head = tail = newNode;
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T getFirst(){
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return head.data;
    }

    public T getLast(){
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return tail.data;
    }

    // Remove the Fist node
    public T removeFirst(){
        if (isEmpty()) throw new IllegalStateException("List is empty");
        T removedData = head.data;
        head = head.next;
        size--;
        if (size == 0) tail = null;
        return removedData;
    }

    // Remove the last node
    public T removeLast(){
        if (isEmpty()) throw new IllegalStateException("List is empty");
        // Only one Node
        if (head == tail) {
            T removedData = head.data;
            head = tail = null;
            size = 0;
            return removedData;
        }

        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }

        T removedData = tail.data;
        tail = current;
        tail.next = null;
        size--;
        return removedData;
    }
}
