import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HeapBuilder {
    Node root;
    // Max-Heap
    Node createMaxHeap(List<Integer> value){
        if(value.isEmpty()) return null;
        List<Node> list = insert(value);
        // Organize the heap
        for(int n = (list.size() - 1) / 2; n >= 0; n-- ){
            int i = n;
            while(true){
                int left = i * 2 + 1;
                int right = i * 2 + 2;
                int largest = i;
                if(left < list.size() && list.get(largest).data < list.get(left).data) largest = left;
                if(right < list.size() && list.get(largest).data < list.get(right).data) largest = right;
                if(largest == i) break;

                int temp = list.get(largest).data;
                list.get(largest).data = list.get(i).data;
                list.get(i).data = temp;
                i = largest;
            }
        }
        return list.get(0);
    }

    // Min-Heap
    Node createMinHeap(List<Integer> value){
        if(value.isEmpty()) return null;
        List<Node> list = insert(value);
        // Organize the heap
        for(int n = (list.size() - 1) / 2; n >= 0; n-- ){
            int i = n;
            while(true){
                int left = i * 2 + 1;
                int right = i * 2 + 2;
                int smallest = i;
                if(left < list.size() && list.get(smallest).data > list.get(left).data) smallest = left;
                if(right < list.size() && list.get(smallest).data > list.get(right).data) smallest = right;
                if(smallest == i) break;

                int temp = list.get(smallest).data;
                list.get(smallest).data = list.get(i).data;
                list.get(i).data = temp;
                i = smallest;
            }
        }
        return list.get(0);
    }

    List<Node> insert(List<Integer> value){
        // Inset all list into heap
        root = new Node(value.get(0));
        Queue<Node> queue = new LinkedList<>();
        List<Node> list = new ArrayList<>(value.size());
        queue.add(root);
        list.add(root);
        int i = 1;
        while(i < value.size()){
            Node cur = queue.poll();
            // Add left child
            cur.left = new Node(value.get(i++));
            queue.add(cur.left);
            list.add(cur.left);
            // Add right child
            if(i < value.size()){
                cur.right = new Node(value.get(i++));
                queue.add(cur.right);
                list.add(cur.right);
            }
        }
        return list;
    }
}
