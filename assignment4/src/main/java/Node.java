public class Node{
    Node left;
    Node right;
    Node parent;
    String ST_Number;
    String ST_Last_Name;
    String home;
    String program;
    char year;
    int height;

    // Constructor
    public Node(String ST_Number, String ST_Last_Name, String home, String program, char year){
        this.ST_Number = ST_Number;
        this.ST_Last_Name = ST_Last_Name;
        this.home = home;
        this.program = program;
        this.year = year;
        left = null;
        right = null;
    }
}
