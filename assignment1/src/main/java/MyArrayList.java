import java.util.Iterator;

public class MyArrayList<E> implements Iterable<E> {
    private E[] data;               
    private int size;               
    private static final int DEFAULT_CAPACITY = 10; 

    // Constructor
    public MyArrayList() {
        // Java doesn't allow direct generic array creation (e.g., new E[]).
        // So we create an Object array and cast it to E[].
        data = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    
    // Add method
    public void add(E element) {
        ensureCapacity();      
        data[size++] = element; 
    }

    // Ensure the capacity
    private void ensureCapacity() {
        if (size == data.length) {
            int newCapacity = data.length * 2; 
            E[] newData = (E[]) new Object[newCapacity];
            for(int i = 0; i < size; i++){
                newData[i] = data[i];        
            }
            data = newData;
        }
    }

    //Implement iterator, make it can run in for(E item : myArrayList)
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < size;
            }
            @Override
            public E next() {
                return data[index++];
            }
        };
    }
}
