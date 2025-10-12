
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class TaskTest {
    BinarySearchTree userTree = new BinarySearchTree();
    BinarySearchTree testTree = new BinarySearchTree();

    @Test
    void insertTaskwithTraversal(){
        System.out.println("Regular:");
        testTree.insert(new Node("S001", "D", "Toronto", "CS", '2'));
        testTree.insert(new Node("S002", "B", "Vancouver", "SE", '3'));
        testTree.insert(new Node("S003", "F", "Montreal", "IT", '1'));
        testTree.insert(new Node("S004", "A", "Calgary", "CS", '4'));
        testTree.insert(new Node("S005", "G", "Ottawa", "CS", '2'));
        testTree.insert(new Node("S006", "C", "Halifax", "SE", '3'));
        testTree.insert(new Node("S007", "E", "Edmonton", "IT", '1'));
        testTree.writeBFSToFile("output/BFS_output.txt");
        testTree.writeDFSToFile("output/DFS_output.txt");
    }

    @Test
    void insertTaskwithLL(){
        System.out.println("TaskLL:");
        testTree.insert(new Node("S001", "F", "Toronto", "CS", '2'));
        testTree.insert(new Node("S002", "G", "Vancouver", "SE", '3'));
        testTree.insert(new Node("S003", "D", "Montreal", "IT", '1'));
        testTree.insert(new Node("S004", "C", "Calgary", "CS", '4'));
        testTree.insert(new Node("S005", "E", "Halifax", "SE", '3'));
        testTree.insert(new Node("S006", "B", "Ottawa", "CS", '2'));
        testTree.insert(new Node("S007", "A", "Edmonton", "IT", '1'));
        testTree.writeBFSToFile("output/BFS_output_LL.txt");
        testTree.writeDFSToFile("output/DFS_output_LL.txt");
    }

    @Test
    void insertTaskwithRR(){
        System.out.println("TaskRR:");
        testTree.insert(new Node("S001", "B", "Toronto", "CS", '2'));
        testTree.insert(new Node("S002", "A", "Vancouver", "SE", '3'));
        testTree.insert(new Node("S003", "D", "Montreal", "IT", '1'));
        testTree.insert(new Node("S004", "E", "Calgary", "CS", '4'));
        testTree.insert(new Node("S005", "C", "Halifax", "SE", '3'));
        testTree.insert(new Node("S006", "F", "Ottawa", "CS", '2'));
        testTree.insert(new Node("S007", "G", "Edmonton", "IT", '1'));
        testTree.writeBFSToFile("output/BFS_output_RR.txt");
        testTree.writeDFSToFile("output/DFS_output_RR.txt");
    }

    @Test
    void insertTaskwithLR(){
        System.out.println("TaskLR:");
        testTree.insert(new Node("S001", "F", "Toronto", "CS", '2'));
        testTree.insert(new Node("S002", "G", "Vancouver", "SE", '3'));
        testTree.insert(new Node("S003", "B", "Montreal", "IT", '1'));
        testTree.insert(new Node("S004", "A", "Calgary", "CS", '4'));
        testTree.insert(new Node("S005", "D", "Halifax", "SE", '3'));
        testTree.insert(new Node("S006", "C", "Ottawa", "CS", '2'));
        testTree.insert(new Node("S007", "E", "Edmonton", "IT", '1'));
        testTree.writeBFSToFile("output/BFS_output_LR.txt");
        testTree.writeDFSToFile("output/DFS_output_LR.txt");
    }

    @Test
    void insertTaskwithRL(){
        System.out.println("TaskRL:");
        testTree.insert(new Node("S001", "B", "Toronto", "CS", '2'));
        testTree.insert(new Node("S002", "A", "Vancouver", "SE", '3'));
        testTree.insert(new Node("S003", "F", "Montreal", "IT", '1'));
        testTree.insert(new Node("S004", "G", "Calgary", "CS", '4'));
        testTree.insert(new Node("S005", "D", "Halifax", "SE", '3'));
        testTree.insert(new Node("S006", "C", "Ottawa", "CS", '2'));
        testTree.insert(new Node("S007", "E", "Edmonton", "IT", '1'));
        testTree.writeBFSToFile("output/BFS_output_RL.txt");
        testTree.writeDFSToFile("output/DFS_output_RL.txt");
    }

    @Test
    void deleteTask(){
        testTree.insert(new Node("S001", "D", "Toronto", "CS", '2'));
        testTree.insert(new Node("S002", "B", "Vancouver", "SE", '3'));
        testTree.insert(new Node("S003", "F", "Montreal", "IT", '1'));
        testTree.insert(new Node("S004", "A", "Calgary", "CS", '4'));
        testTree.insert(new Node("S005", "G", "Ottawa", "CS", '2'));
        testTree.insert(new Node("S006", "C", "Halifax", "SE", '3'));
        testTree.insert(new Node("S007", "E", "Edmonton", "IT", '1'));
        testTree.delete(new Node("S006", "C", "Halifax", "SE", '3'));
        testTree.delete(new Node("S003", "F", "Montreal", "IT", '1'));
        testTree.delete(new Node("S002", "B", "Vancouver", "SE", '3'));
        testTree.writeBFSToFile("output/BFS_delete_output.txt");
        testTree.writeDFSToFile("output/DFS_delete_output.txt");
    }

    @Test
    void deleteTaskwithLL(){
        testTree.insert(new Node("S001", "D", "Toronto", "CS", '2'));
        testTree.insert(new Node("S002", "B", "Vancouver", "SE", '3'));
        testTree.insert(new Node("S003", "F", "Montreal", "IT", '1'));
        testTree.insert(new Node("S004", "A", "Calgary", "CS", '4'));
        testTree.insert(new Node("S006", "C", "Halifax", "SE", '3'));
        testTree.delete(new Node("S003", "F", "Montreal", "IT", '1'));
        testTree.writeBFSToFile("output/BFS_delete_output_LL.txt");
        testTree.writeDFSToFile("output/DFS_delete_output_LL.txt");
    }

    @Test
    void deleteTaskwithRR(){
        testTree.insert(new Node("S001", "D", "Toronto", "CS", '2'));
        testTree.insert(new Node("S002", "B", "Vancouver", "SE", '3'));
        testTree.insert(new Node("S003", "F", "Montreal", "IT", '1'));
        testTree.insert(new Node("S005", "G", "Ottawa", "CS", '2'));
        testTree.insert(new Node("S007", "E", "Edmonton", "IT", '1'));
        testTree.delete(new Node("S002", "B", "Vancouver", "SE", '3'));
        testTree.writeBFSToFile("output/BFS_delete_output_RR.txt");
        testTree.writeDFSToFile("output/DFS_delete_output_RR.txt");
    }

    @Test
    void deleteTaskwithLR(){
        testTree.insert(new Node("S001", "D", "Toronto", "CS", '2'));
        testTree.insert(new Node("S002", "B", "Vancouver", "SE", '3'));
        testTree.insert(new Node("S003", "F", "Montreal", "IT", '1'));
        testTree.insert(new Node("S004", "A", "Calgary", "CS", '4'));
        testTree.insert(new Node("S006", "C", "Halifax", "SE", '3'));
        testTree.delete(new Node("S004", "A", "Calgary", "CS", '4'));
        testTree.delete(new Node("S003", "F", "Montreal", "IT", '1'));
        testTree.writeBFSToFile("output/BFS_delete_output_LR.txt");
        testTree.writeDFSToFile("output/DFS_delete_output_LR.txt");
    }

    @Test
    void deleteTaskwithRL(){
        testTree.insert(new Node("S001", "D", "Toronto", "CS", '2'));
        testTree.insert(new Node("S002", "B", "Vancouver", "SE", '3'));
        testTree.insert(new Node("S003", "F", "Montreal", "IT", '1'));
        testTree.insert(new Node("S005", "G", "Ottawa", "CS", '2'));
        testTree.insert(new Node("S007", "E", "Edmonton", "IT", '1'));
        testTree.delete(new Node("S005", "G", "Ottawa", "CS", '2'));
        testTree.delete(new Node("S002", "B", "Vancouver", "SE", '3'));
        testTree.writeBFSToFile("output/BFS_delete_output_RR.txt");
        testTree.writeDFSToFile("output/DFS_delete_output_RR.txt");
    }

    @Test
    void insertStudent() throws IOException{
        BufferedReader br1 = new BufferedReader(new FileReader("tree-input.txt", java.nio.charset.StandardCharsets.UTF_8));
        String line;
        while((line = br1.readLine()) != null){
            String op = line.substring(0, 1);
            String stuNum = line.substring(1, 8);
            String stuLastName = line.substring(8, 33).trim();
            String stuHome = line.substring(33, 37).trim();
            String stuProg = line.substring(37, 41).trim();
            char stuYear = line.charAt(41); 
            if(op.equals("I")){
                userTree.insert(new Node(stuNum, stuLastName, stuHome, stuProg, stuYear));
            }
            else if(op.equals("D")){
                userTree.delete(new Node(stuNum, stuLastName, stuHome, stuProg, stuYear));
            }
        }
        br1.close();
        userTree.writeBFSToFile("output/BFS_insert_user_output.txt");
        userTree.writeDFSToFile("output/DFS_insert_user_output.txt");

        //userTree.delete();
        BufferedReader br2 = new BufferedReader(new FileReader("tree-custom.txt", java.nio.charset.StandardCharsets.UTF_8));
        while((line = br2.readLine()) != null){
            String op = line.substring(0, 1);
            String stuNum = line.substring(1, 8);
            String stuLastName = line.substring(8, 33).trim();
            String stuHome = line.substring(33, 37).trim();
            String stuProg = line.substring(37, 41).trim();
            char stuYear = line.charAt(41); 
            if(op.equals("I")){
                userTree.insert(new Node(stuNum, stuLastName, stuHome, stuProg, stuYear));
            }
            else if(op.equals("D")){
                userTree.delete(new Node(stuNum, stuLastName, stuHome, stuProg, stuYear));
            }
        }
        br2.close();
        userTree.writeBFSToFile("output/BFS_delete_user_output.txt");
        userTree.writeDFSToFile("output/DFS_delete_user_output.txt");
    }
}
