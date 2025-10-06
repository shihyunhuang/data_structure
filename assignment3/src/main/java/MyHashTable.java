import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class MyHashTable {
    public List<String> [] table;
    public int capacity;
    private int size;
    private static final int[] PRIMES = {
        53, 97, 193, 389, 769,
        1543, 3079, 6151, 12289, 24593,
        49157, 98317, 196613, 393241, 786433,
        1572869, 3145739, 6291469, 12582917, 25165843
    };

    // Constructor
    public MyHashTable(int cap){
        this.capacity = cap;
        this.table = new ArrayList[capacity];
        for(int i = 0; i < cap; i++){
            table[i] = new ArrayList<>();
        }
        this.size = 0;
    }

    // Hash method
    public int hash(String key){
        if(key == null) throw new IllegalArgumentException("Wrong key");
        // Using a polynomial rolling hash (division method)
        final int base = 33;        
        final int p = 109345121;    // A large prime number to reduce overflow and clustering
        long code = 0;              // Use long to avoid integer overflow
        for(int i = 0; i < key.length(); i++){
            code = (code * base + key.charAt(i)) % p;
        }
        // Compress the hash value into the table range
        return (int)code % this.capacity;
    }

    // Insert method
    public void insert(String key){
        int index = this.hash(key);
        // Make sure the key is not contain
        for (String s : table[index]) {
            if (s.equals(key)) return; 
        }
        table[index].add(key);
        size++;
        // Make sure the number of empty space
        if(size > capacity * 0.75){
            this.resize();
        }
    }

    // Get size method
    public int size(){
        return this.size;
    }

    // isContain method
    public boolean isContain(String key){
        int index = this.hash(key);
        for(String str : table[index]){
            if(str.equals(key)) return true;
        }   
        return false;
    }

    // Resize method
    public void resize(){
        // Pick new capacity
        int newCap = capacity;
        for(int i = 0; i < PRIMES.length; i++){
            if(2 * capacity < PRIMES[i]){
                newCap = PRIMES[i];
                break;
            }
        }
        List<String>[] oldTable = this.table;
        List<String> [] newTable = new ArrayList[newCap];
        for(int i = 0; i < newCap; i++) newTable[i] = new ArrayList<>();

        this.table = newTable;
        this.capacity = newCap;
        this.size = 0;

        for(List<String> bucket : oldTable){
            for(String str : bucket){
                int index = hash(str);
                newTable[index].add(str);
                this.size++;
            }
        }
    }

    // Parse and read file
    public void parseTXT(String path){
        // Read the txt
        try (BufferedReader br = new BufferedReader(
            new FileReader(path, java.nio.charset.StandardCharsets.UTF_8))) {
            String line;
            while((line = br.readLine()) != null){
                if (line.isBlank()) continue;
                String[] words = line.split("[^a-zA-Z0-9]+");       // Spilt the line
                for(String word : words){
                    if (word.isEmpty()) continue;
                    word = word.toLowerCase();                            // Convert the word to lower case
                    char [] c = word.toCharArray();
                    Arrays.sort(c);                                       // Sort the char in word
                    String root = new String(c);
                    this.insert(root);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}