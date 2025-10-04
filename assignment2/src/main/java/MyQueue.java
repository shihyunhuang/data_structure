import java.util.LinkedList;

public class MyQueue<T> {
    private final LinkedList<T> list;

    // Constructor
    public MyQueue(){ list = new LinkedList<>(); }

    //Enqueue method
    public void enqueue(T obj){ list.add(obj); }

    // Dequeue method
    public T dequeue(){
        if(list.isEmpty()) return null;
        return list.removeFirst();
    }

    //Poll method
    public T poll(){
        if(list.isEmpty()) return null;
        return list.getFirst();
    }

    //Size method
    public int size(){ return list.size(); }

}
