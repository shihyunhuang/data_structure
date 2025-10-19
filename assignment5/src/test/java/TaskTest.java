import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TaskTest {
    List<Integer> value = new ArrayList<>(Arrays.asList(2, 5, 7, 9, 3, 4, 6, 1));
    @Test
    void Max_Heap(){
        HeapBuilder hb = new HeapBuilder();
        Node max = hb.createMaxHeap(value);

        assertEquals(max.data, 9);
    }

    @Test
    void Min_Heap(){
        HeapBuilder hb = new HeapBuilder();
        Node min = hb.createMinHeap(value);
        
        assertEquals(min.data, 1);
    }

    @Test
    void BstToMaxHeap(){
        // Build BST
        BST bst = new BST();
        for(int i : value){
            BSTNode newNode = new BSTNode(i);
            bst.insert(newNode);
        }
        // Transform BST to Max-Heap
        BSTToHeapTransformer transform = new BSTToHeapTransformer();
        List<BSTNode> list = transform.bstToMaxHeap(bst);
        assertEquals(list.get(0).data, 9);
        assertTrue(list.get(0).data >= list.get(0).left.data);
        assertTrue(list.get(0).data >= list.get(0).right.data);
        assertTrue(list.get(1).data >= list.get(1).left.data);
        assertTrue(list.get(1).data >= list.get(1).right.data);
        assertTrue(list.get(2).data >= list.get(2).left.data);
        assertTrue(list.get(2).data >= list.get(2).right.data);
        assertTrue(list.get(3).data >= list.get(3).left.data);
    }

    @Test
    void BstToMinHeap(){
        // Build BST
        BST bst = new BST();
        for(int i : value){
            BSTNode newNode = new BSTNode(i);
            bst.insert(newNode);
        }
        // Transform BST to Max-Heap
        BSTToHeapTransformer transform = new BSTToHeapTransformer();
        List<BSTNode> list = transform.bstToMinHeap(bst);
        assertEquals(list.get(0).data, 1);
        assertTrue(list.get(0).data <= list.get(0).left.data);
        assertTrue(list.get(0).data <= list.get(0).right.data);
        assertTrue(list.get(1).data <= list.get(1).left.data);
        assertTrue(list.get(1).data <= list.get(1).right.data);
        assertTrue(list.get(2).data <= list.get(2).left.data);
        assertTrue(list.get(2).data <= list.get(2).right.data);
        assertTrue(list.get(3).data <= list.get(3).left.data);
    }
}
