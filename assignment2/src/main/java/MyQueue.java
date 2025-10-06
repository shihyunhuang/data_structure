public class MyQueue<T> {
    private final MyLinkedList<T> list;

    // Constructor
    public MyQueue(){ list = new MyLinkedList<>(); }

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
