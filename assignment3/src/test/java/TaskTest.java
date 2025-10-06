import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    void testBasicFunction(){
        MyHashTable myHashTable = new MyHashTable(5);
        // Test insert and isContain and hash method
        myHashTable.insert("Tom");
        assertTrue(myHashTable.isContain("Tom"));
        assertEquals(myHashTable.hash("Tom"), 3);
        // Test size and resize method
        myHashTable.insert("Kelly");
        myHashTable.insert("Bobby");
        myHashTable.insert("Jennie");
        assertEquals(myHashTable.size(), 4);
        assertEquals(myHashTable.capacity, 53);
        assertTrue(myHashTable.isContain("Jennie"));
    }

    @Test
    void testParseTXT(){
        // Test same string 
        MyHashTable t = new MyHashTable(53);
        t.insert("agmno");
        t.insert("agmno");
        assert t.size() == 1;
        assert t.isContain("agmno");
        // Test pase pride-and-prejudice.txt
        MyHashTable myHashTable = new MyHashTable(53);
        String path = "pride-and-prejudice.txt";
        myHashTable.parseTXT(path);
        assertTrue(myHashTable.size() > 1000);
        assertTrue(myHashTable.size() < 500000);
        System.err.println("The size of MyHashTable is " + myHashTable.size());
        for(String str : myHashTable.table[3]){
            System.err.println(str);
        }
    }
}
