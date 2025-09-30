import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

public class User_LinkedList {
    private Node head;
    public int nextid = 1;
    private PriorityQueue<Integer> freeid = new PriorityQueue<>();

    // Get user by ID
    public Node getByID(int id){
        Node current = head;
        while(current != null){
            if(current.id == id){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Get user id
    public int getFreeID(){
        if(!freeid.isEmpty()){
            return freeid.poll();
        }
        return nextid++;
    }

    // Method to add new user
    public void addUser(Node newUser){
        newUser.id = getFreeID();
        if(head == null || head.id > newUser.id){
            newUser.next = head;
            head = newUser;
        }
        else{
            Node current = head;
            // Find the last node
            while(current.next != null && current.next.id < newUser.id){
                current = current.next;
            }
            newUser.next = current.next;
            current.next = newUser;
        }
    }

    // Method to delete user
    public void deleteUser(int id){
        Node current = head;
        // Check if list is empty
        if(head == null){
            System.out.println("List is empty");
            return;
        }
        else if(current.id == id){
            head = head.next;
            freeid.add(id);
            return;
        }
        else{
            while(current.next != null && current.next.id != id){
                current = current.next;
            }
            // Check if user exist
            if(current.next == null){
                System.out.println("Cannot find User");
                return;
            }
        }
        current.next = current.next.next;
        freeid.add(id);
    }

    // Method to pay money
    public void payUsertoUser(int payer, int payee, double amount){
        Node current = head;
        Node payerNode = null;
        Node payeeNode = null;
        // Check if list is empty
        if(head == null){
            System.out.println("List is empty");
            return;
        }
        while(current != null){
            if(current.id == payer){    
                payerNode = current;
            }
            if(current.id == payee){
                payeeNode = current;
            }
            current = current.next;
        }
        // Check if both users exist
        if(payerNode == null || payeeNode == null){
            System.out.println("Cannot find User");
            return;
        }
        payerNode.initial_deposit -= amount;
        payeeNode.initial_deposit += amount;
    }

    // Method to ge median ID
    public int getMedianID(){
        // Check if list is empty
        if(head == null){
            System.out.println("List is empty");
            return -1;
        }
        Node current = head;
        Node median = head;
        int count = 0;
        while(current != null){
            count++;
            current = current.next;
        }
        if(count % 2 == 0){
            for(int i = 1; i < count / 2; i++){
                median = median.next;
            }
            return median.id;
        }
        for(int i = 0; i < count / 2; i++){
            median = median.next;
        }
        return median.id;
    }

    // Method to merge two accounts
    public void mergeAccounts(int a, int b){
        Node node_A = getByID(a);
        Node node_B = getByID(b);
        if(node_A == null || node_B == null){
            System.out.println("Cannot find User");
        }
        else if(node_A.name.equals(node_B.name) && node_A.address.equals(node_B.address) && node_A.social_security.equals(node_B.social_security)){
            if(node_A.id < node_B.id){
                node_A.initial_deposit += node_B.initial_deposit;
                deleteUser(node_B.id);
            }
            else{
                node_B.initial_deposit += node_A.initial_deposit;
                deleteUser(node_A.id);
            }
        }
    }

    // Method to merge two banks
    public User_LinkedList mergeBanks(User_LinkedList a, User_LinkedList b){
        User_LinkedList mergedList = new User_LinkedList();
        Node a_cur = a.head;
        Node b_cur = b.head;
        Node current = mergedList.head;
        ArrayList<Node> same = new ArrayList<>();

        // Add first node
        if(a_cur == null && b_cur == null){
            return mergedList;
        }
        else if(a_cur == null || a_cur.id > b_cur.id){
            Node headUser = new Node(b_cur.id, b_cur.name, b_cur.address, b_cur.social_security, b_cur.initial_deposit);
            mergedList.head = headUser;
            b_cur = b_cur.next;
            current = mergedList.head;
        }
        else if(b_cur == null || b_cur.id > a_cur.id){
            Node headUser = new Node(a_cur.id, a_cur.name, a_cur.address, a_cur.social_security, a_cur.initial_deposit);
            mergedList.head = headUser;
            a_cur = a_cur.next;
            current = mergedList.head;
        }
        else{
            mergedList.head = new Node(a_cur.id, a_cur.name, a_cur.address, a_cur.social_security, a_cur.initial_deposit);
            same.add(b_cur);
            a_cur = a_cur.next;
            b_cur = b_cur.next;
            current = mergedList.head;
        }
        // Merge two lists
        while(a_cur != null && b_cur != null){
            if(a_cur.id < b_cur.id){
                current = mergedList.addUserWithID(a_cur.id, a_cur.name, a_cur.address, a_cur.social_security, a_cur.initial_deposit, current);
                a_cur = a_cur.next;
            }
            else if(b_cur.id < a_cur.id){
                current = mergedList.addUserWithID(b_cur.id, b_cur.name, b_cur.address, b_cur.social_security, b_cur.initial_deposit, current);
                b_cur = b_cur.next;
            }
            // If both ids are same, add one to mergedlist and store the other in array
            else{
                current = mergedList.addUserWithID(a_cur.id, a_cur.name, a_cur.address, a_cur.social_security, a_cur.initial_deposit, current);
                same.add(b_cur);
                a_cur = a_cur.next;
                b_cur = b_cur.next;  
            }
        }

        // Add remaining nodes
        while(a_cur != null){
            current = mergedList.addUserWithID(a_cur.id, a_cur.name, a_cur.address, a_cur.social_security, a_cur.initial_deposit, current);
            a_cur = a_cur.next;
        }
        while(b_cur != null){
            current = mergedList.addUserWithID(b_cur.id, b_cur.name, b_cur.address, b_cur.social_security, b_cur.initial_deposit, current);
            b_cur = b_cur.next;
        }

        // Build set of present ids and determine max id
        HashSet<Integer> present = new HashSet<>();
        int maxId = 0;
        Node walker = mergedList.head;
        while(walker != null){
            present.add(walker.id);
            if(walker.id > maxId) maxId = walker.id;
            walker = walker.next;
        }

        // Fill freeid with missing ids between 1 and maxId
        for(int i = 1; i <= maxId; i++){
            if(!present.contains(i)) mergedList.freeid.add(i);
        }

        int free_id;
        // Add same users, assigning smallest available id or next new id
        for (Node n : same) {
            if(!mergedList.freeid.isEmpty()){
                free_id = mergedList.freeid.poll();
                // insert node with free_id in sorted position
                Node newUser = new Node(free_id, n.name, n.address, n.social_security, n.initial_deposit);
                if(free_id < mergedList.head.id){
                    newUser.next = mergedList.head;
                    mergedList.head = newUser;
                } else {
                    Node prev = mergedList.head;
                    while(prev.next != null && prev.next.id < free_id){
                        prev = prev.next;
                    }
                    newUser.next = prev.next;
                    prev.next = newUser;
                }
            }
            else{
                // assign new id at end
                maxId++;
                Node newUser = new Node(maxId, n.name, n.address, n.social_security, n.initial_deposit);
                current.next = newUser;
                current = newUser;
            }
        }
        // Update nextid of merged list
        mergedList.nextid = maxId + 1;
        return mergedList;
    }

    // Method to add user with specific ID (used in merging)
    public Node addUserWithID(int id, String name, String address, String social_security, double initial_deposit, Node current){
        Node newUser = new Node(id, name, address, social_security, initial_deposit);
        current.next = newUser;
        return newUser;
    }
    
}
