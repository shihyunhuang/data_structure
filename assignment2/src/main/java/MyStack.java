import java.util.LinkedList;

public class MyStack<T> {
    private final LinkedList<T> list;

    // Constructor
    public MyStack(){ list = new LinkedList<>(); }

    // Push method
    public void push(T obj){ list.add(obj); }

    // Pop method
    public T pop(){
        if(list.isEmpty()) return null;
        return list.removeLast();
    }

    //Peek method
    public T peek(){
        if(list.isEmpty()) return null;
        return list.getLast();
    }

    // Size method
    public int size(){
        return list.size();
    }

    // Calculate method
    public int calculate(String str){
        // Check for valid input
        MyStack<Character> input = new MyStack<>();
        if(str.charAt(0) == '+' || str.charAt(0) == '*' || str.charAt(0) == '/'){ 
            throw new IllegalArgumentException("Invalid input");}   // Starts with invalid character
        for(char ch : str.toCharArray()){ 
            if(Character.isWhitespace(ch)) continue;
            if(Character.isDigit(ch) || ch == '+' || ch == '-' || ch == '*' || ch == '/') input.push(ch);
            else throw new IllegalArgumentException("Invalid input");                   // Invalid character
        }
        if(input.peek() == null) throw new IllegalArgumentException("Empty input");     // Empty input
        if(input.peek() == '+' || input.peek() == '-' || input.peek() == '*' || input.peek() == '/') throw new IllegalArgumentException("Invalid input");   // Ends with operator
        
        // Stacks for numbers and operators
        MyStack<Integer> num = new MyStack<>();
        MyStack<Integer> afternum = new MyStack<>();
        MyStack<Character> op = new MyStack<>();
        MyStack<Character> afterop = new MyStack<>();

        int count = 1;
        Boolean isNegative = false;
        Boolean doubleOperator = true;
        // Parse the input
        while(input.peek() != null){
            char c = input.peek();
            switch (c) {
                case '+', '-', '*', '/' -> {
                    // Handle negative numbers
                    if(doubleOperator && !isNegative) throw new IllegalArgumentException("Invalid input");
                    else if(isNegative){
                        if( c == '+' || c == '-') throw new IllegalArgumentException("Invalid input");
                        num.push( 0 - num.pop());
                        op.pop();
                        op.push(input.pop());
                        isNegative = false;
                        count = 1;
                    }
                    else{
                        op.push(input.pop());
                        count = 1;
                        doubleOperator = true;
                        if(c == '-') isNegative = true;
                    }
                    break;
                }
                default -> {
                    if(count == 1){
                        num.push( input.pop() - '0' );
                        count *= 10;
                        doubleOperator = false;
                        isNegative = false;
                    }
                    else{
                        num.push((input.pop() - '0') * count + num.pop());
                        count *= 10;
                    }
                    break;
                }
            }
        }
        if(isNegative){
            num.push(0 - num.pop());
            op.pop();
        }

        // Perform *, / first
        while(op.peek() != null){
            switch (op.peek()) {
                case '*' -> {
                    num.push(num.pop() * num.pop());
                    op.pop();
                }
                case '/' -> {
                    int pop1 = num.pop();
                    int pop2 = num.pop();
                    if(pop2 == 0) throw new IllegalArgumentException("Division by zero");
                    num.push(pop1 / pop2);
                    op.pop();
                }
                default -> {
                    afterop.push(op.pop());
                    afternum.push(num.pop());
                }
            }
        }
        afternum.push(num.pop());

        // Perform +, -
        while(afterop.peek() != null){
            if(afterop.peek().equals('+')){
                afternum.push(afternum.pop() + afternum.pop());
                afterop.pop();
            }
            else if(afterop.peek().equals('-')){
                int pop1 = afternum.pop();
                int pop2 = afternum.pop();
                afternum.push(pop2 - pop1);
                afterop.pop();
            }
        }
        return afternum.pop();
    }
}
