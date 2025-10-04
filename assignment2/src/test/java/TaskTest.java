import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void testMyStackBsic(){
        MyStack<Integer> mystack = new MyStack<>();
        // Test push
        mystack.push(1);
        mystack.push(2);
        mystack.push(3);
        // Test size, peek
        assertEquals(mystack.size(), 3);
        assertEquals(mystack.peek(), 3);
        // Test pop
        assertEquals(mystack.pop(), 3);
        assertEquals(mystack.size(), 2);
        assertEquals(mystack.peek(), 2);
    }

    @Test
    void testCalculate(){
        MyStack<Character> mystack = new MyStack<>();
        // Test for valid input
        assertEquals(mystack.calculate("22"), 22);
        assertEquals(mystack.calculate(" 4 * 10 + 2 * 6 "), 52);
        assertEquals(mystack.calculate(" 10 * 2 * 6 "), 120);
        assertEquals(mystack.calculate("100 * 2 + 12 - 4"), 208);
        assertEquals(mystack.calculate("100 * 2 / 4"), 50);
        assertEquals(mystack.calculate("100 * 2 + 12 / 4 - 6 * 3"), 185);
        assertEquals(mystack.calculate("3 + 8 / 2 / 4 - 5"), -1);

        // Test for negative numbers
        assertEquals(mystack.calculate("-10 + 2 * 6 "), 2);
        assertEquals(mystack.calculate("100 * -2 + 12"), -188);
        assertEquals(mystack.calculate("-100 * -2 + 12"), 212);
    }

    @Test
    void testCalculateError(){
        MyStack<Integer> mystack = new MyStack<>();
        // Test for invalid input
        IllegalArgumentException ex1 = assertThrows(IllegalArgumentException.class, () -> mystack.calculate(" 10 + 2 / 0"));
        assertEquals("Division by zero", ex1.getMessage());
        IllegalArgumentException ex2 = assertThrows(IllegalArgumentException.class, () -> mystack.calculate(" 10 + 2 1bc"));
        assertEquals("Invalid input", ex2.getMessage());
        IllegalArgumentException ex3 = assertThrows(IllegalArgumentException.class, () -> mystack.calculate(" 10 + 2 +"));
        assertEquals("Invalid input", ex3.getMessage());
        IllegalArgumentException ex4 = assertThrows(IllegalArgumentException.class, () -> mystack.calculate(" 10 - - 2"));
        assertEquals("Invalid input", ex4.getMessage());
        IllegalArgumentException ex5 = assertThrows(IllegalArgumentException.class, () -> mystack.calculate(" 10 +* 2"));
        assertEquals("Invalid input", ex5.getMessage());
        IllegalArgumentException ex6 = assertThrows(IllegalArgumentException.class, () -> mystack.calculate("   "));
        assertEquals("Empty input", ex6.getMessage());
        IllegalArgumentException ex7 = assertThrows(IllegalArgumentException.class, () -> mystack.calculate("/ 10 + 2"));
        assertEquals("Invalid input", ex7.getMessage());
    }

    @Test
    void testMyQueueBasic(){
        MyQueue<Integer> myqueue = new MyQueue<>();
        // Test enqueue
        myqueue.enqueue(1);
        myqueue.enqueue(2);
        myqueue.enqueue(3);
        // Test size, poll
        assertEquals(myqueue.size(), 3);
        assertEquals(myqueue.poll(), 1);
        // Test dequeue
        assertEquals(myqueue.dequeue(), 1);
        assertEquals(myqueue.size(), 2);
        assertEquals(myqueue.poll(), 2);
    }

    @Test
    void testStackwithTwoQs(){
        StackwithTwoQs<Integer> mystack = new StackwithTwoQs<>();
        // Test push
        mystack.push(1);
        mystack.push(2);
        mystack.push(3);
        // Test size, peek
        assertEquals(mystack.size(), 3);
        assertEquals(mystack.peek(), 3);
        // Test pop
        assertEquals(mystack.pop(), 3);
        assertEquals(mystack.size(), 2);
        assertEquals(mystack.peek(), 2);
    }

}
