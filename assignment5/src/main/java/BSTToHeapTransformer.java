import java.util.ArrayList;
import java.util.List;

public class BSTToHeapTransformer {
    List<BSTNode> bstToMaxHeap(BST bst){
        List<BSTNode> list = new ArrayList<>();
        BSTNode root = bst.root;
        root.parent = null;
        list = bstToMaxList(root, list);
        return listToHeap(list);
    }
    List<BSTNode> bstToMinHeap(BST bst){
        List<BSTNode> list = new ArrayList<>();
        BSTNode root = bst.root;
        root.parent = null;
        list = bstToMinList(root, list);
        return listToHeap(list);
    }
    // Add the nodes into the list descending
    List<BSTNode> bstToMaxList(BSTNode node, List<BSTNode> list){
        if(node != null){
            bstToMaxList(node.right, list);
            list.add(node);
            bstToMaxList(node.left, list);
        }
        return list;
    }
    // Add the nodes into the list ascending
    List<BSTNode> bstToMinList(BSTNode node, List<BSTNode> list){
        if(node != null){
            bstToMinList(node.left, list);
            list.add(node);
            bstToMinList(node.right, list);
        }
        return list;
    }
    // Reassign the children of nodes
    List<BSTNode> listToHeap(List<BSTNode> list){
        for(int i = 0; i < list.size(); i++){
            if(i * 2 + 1 < list.size()) list.get(i).left = list.get(i * 2 + 1);
            if(i * 2 + 2 < list.size()) list.get(i).right = list.get(i * 2 + 2);
            list.get(i).parent = null;
        }
        return list;
    }
}
