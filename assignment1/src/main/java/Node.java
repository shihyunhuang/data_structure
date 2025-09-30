public class Node {
    // Node attributes
    int id = 0;
    String name;
    String address;
    String social_security;
    double initial_deposit;
    Node next;

    // Constructor with id
    Node(int id, String name, String address, String social_security, double initial_deposit){
        this.id = id;
        this.name = name;
        this.address = address;
        this.social_security = social_security;
        this.initial_deposit = initial_deposit;
        this.next = null;
    }
    // Constructor without id
    Node(String name, String address, String social_security, double initial_deposit){
        this.name = name;
        this.address = address;
        this.social_security = social_security;
        this.initial_deposit = initial_deposit;
        this.next = null;
    }
}
