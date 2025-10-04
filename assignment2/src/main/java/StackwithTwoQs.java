public class StackwithTwoQs<T> {
    private final MyQueue<T> Q1;  // Save the elements
    private final MyQueue<T> Q2;  // Hold queue

    // Constructor
    public StackwithTwoQs(){
        Q1 = new MyQueue<>();
        Q2 = new MyQueue<>();
    }

    // Push method
    public void push(T obj){
        Q1.enqueue(obj);
    }

    // Pop method
    public T pop(){
        int len = Q1.size();
        if(len == 0) return null;
        for(int i = 0; i < len - 1; i++){
            Q2.enqueue(Q1.dequeue());
        }
        T popObj = Q1.dequeue();
        for(int j = 0; j < len - 1; j++){
            Q1.enqueue(Q2.dequeue());
        }
        return popObj;
    }

    // Peek method
    public T peek(){
        int len = Q1.size();
        if(len == 0) return null;
        for(int i = 0; i < len - 1; i++){
            Q2.enqueue(Q1.dequeue());
        }
        T peekObj = Q1.dequeue();
        for(int j = 0; j < len - 1; j++){
            Q1.enqueue(Q2.dequeue());
        }
        Q1.enqueue(peekObj);
        return peekObj;
    }

    // Size method
    public int size(){
        return Q1.size();
    }
}