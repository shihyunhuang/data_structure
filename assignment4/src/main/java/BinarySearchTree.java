import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    Node root;
    int size;

    // Constructor
    public BinarySearchTree(){
        root = null;
        size = 0;
    }
    // Insert method
    public void insert(Node new_Node){
        root = insertR(root, new_Node);
        size++;
    }
    // Recursive insert method
    Node insertR(Node node, Node new_Node){
        // Find the correct position and insert the node
        if(node == null){
            new_Node.height = 1;
            return new_Node;
        }
        else if(compare(new_Node, node) < 0){
            node.left = insertR(node.left, new_Node);
            node.left.parent = node;
        }
        else{
            node.right = insertR(node.right, new_Node);
            node.right.parent = node;
        }
        // Update height of parent node
        upHeight(node);
        // Balance the tree
        int balance = hc(node);
        if(balance > 1 && compare(new_Node, node.left) < 0){
            return rotateRight(node);               // LL
        }
        if(balance < -1 && compare(new_Node, node.right) > 0){
            return rotateLeft(node);              // RR
        }
        if(balance > 1 && compare(new_Node, node.left) > 0){
            // LR: rotate left child left, then node right
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if(balance < -1 && compare(new_Node, node.right) < 0){
            // RL: rotate right child right, then node left
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        
        return node;
    }
    int compare(Node node1, Node node2){ return node1.ST_Last_Name.compareTo(node2.ST_Last_Name);}  // Compare two nodes
    int h(Node node){ return node == null? 0 : node.height;}                                        // Get height
    int hc(Node node){ return node == null? 0 : h(node.left) - h(node.right); }                     // Get height difference
    void upHeight(Node node){ node.height = Math.max(h(node.left), h(node.right)) + 1;}             // Update height

    // Right rotation
    Node rotateRight(Node x){
        Node pivot = x.left;
        Node temp = pivot.right;
        x.left = temp;
        if(temp != null) temp.parent = x;
        // Connect grandparent
        pivot.parent = x.parent;
        if(x.parent != null){
            if(x.parent.left == x) x.parent.left = pivot;
            else x.parent.right = pivot;
        }
        // Rotate
        pivot.right = x;
        x.parent = pivot;
        // Update heights
        upHeight(x);
        upHeight(pivot);
        return pivot;
    }
    // Left rotation
    Node rotateLeft(Node x){
        Node pivot = x.right;
        Node temp = pivot.left;
        x.right = temp;
        if(temp != null) temp.parent = x;
        // Connect grandparent
        pivot.parent = x.parent;
        if(x.parent != null){
            if(x.parent.left == x) x.parent.left = pivot;
            else x.parent.right = pivot;
        }
        // Rotate
        pivot.left = x;
        x.parent = pivot;
        // Update heights
        upHeight(x);
        upHeight(pivot);
        return pivot;
    }

    // Traverse method and write to file
    public void DFS(Node node, PrintWriter writer) {
        if(node != null){
            DFS(node.left, writer);
            writer.println("Student Number: " + node.ST_Number + ", Last Name: " + node.ST_Last_Name + ", Home: " + node.home + ", Program: " + node.program + ", Year: " + node.year);
            DFS(node.right, writer);
        }
    }
    public void writeDFSToFile(String filename){
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, false))) {
            DFS(root, writer);
        } catch (Exception e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // BFS method and write to file
    public void BFS(PrintWriter writer, Queue<Node> queue) {
        while(!queue.isEmpty()){
            Node first = queue.poll();
            if(first.left != null){
                queue.add(first.left);
            }
            if(first.right != null){
                queue.add(first.right);
            }
            writer.println("Student Number: " + first.ST_Number + ", Last Name: " + first.ST_Last_Name + ", Home: " + first.home + ", Program: " + first.program + ", Year: " + first.year);
        }
    }
    public void  writeBFSToFile(String filename){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, false))) {
            BFS(writer, queue);
        } catch (Exception e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Delete metohd
    public void delete(Node delete_Node){
        root = deleteR(root, delete_Node);
        size--;
    }

    Node deleteR(Node node, Node delete_Node){
        // Find the node to be deleted
        if(compare(delete_Node, node) < 0) node.left = deleteR(node.left, delete_Node);
        else if(compare(delete_Node, node) > 0) node.right = deleteR(node.right, delete_Node);
        else{
            // Node with no child
            if(node.left == null && node.right == null) return null;
            // Node with one child
            else if(node.left == null) {
                node.right.parent = node.parent;
                return node.right;
            }
            else if(node.right == null) {
                node.left.parent = node.parent;
                return node.left;
            }
            // Node with two children
            else{
                // Replace deleted node to next larger node
                Node successor = node.right;
                while(successor.left != null) successor = successor.left;
                node.ST_Number = successor.ST_Number;
                node.ST_Last_Name = successor.ST_Last_Name;
                node.home = successor.home;
                node.program = successor.program;
                node.year = successor.year;
                node.right = deleteR(node.right, successor);
            }
        }
        // Update height
        upHeight(node);
        // Balance the tree
        int balance = hc(node);
        if(balance > 1 && hc(node.left) >= 0) return rotateRight(node);
        if(balance < -1 && hc(node.right) <= 0) return rotateLeft(node);
        if(balance > 1 && hc(node.left) < 0){
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if(balance < -1 && hc(node.right) > 0){
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }
}
