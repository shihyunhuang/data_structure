
public class BST {
    BSTNode root;
    int size;

    // Constructor
    public BST(){
        root = null;
        size = 0;
    }
    // Insert method
    public void insert(BSTNode new_Node){
        root = insertR(root, new_Node);
        size++;
    }
    // Recursive insert method
    BSTNode insertR(BSTNode node, BSTNode new_Node){
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
    int compare(BSTNode node1, BSTNode node2){ return Integer.compare(node1.data, node2.data);}                     // Compare two nodes
    int h(BSTNode node){ return node == null? 0 : node.height;}                                        // Get height
    int hc(BSTNode node){ return node == null? 0 : h(node.left) - h(node.right); }                     // Get height difference
    void upHeight(BSTNode node){ node.height = Math.max(h(node.left), h(node.right)) + 1;}             // Update height

    // Right rotation
    BSTNode rotateRight(BSTNode x){
        BSTNode pivot = x.left;
        BSTNode temp = pivot.right;
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
    BSTNode rotateLeft(BSTNode x){
        BSTNode pivot = x.right;
        BSTNode temp = pivot.left;
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
}
