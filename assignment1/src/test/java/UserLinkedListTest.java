import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

public class UserLinkedListTest {
    User_LinkedList userList = new User_LinkedList();

    @Test
    void testAddUser() {
        // Add users
        userList.addUser(new Node("John", "Address1", "SSN1", 100));
        userList.addUser(new Node("Alice", "Address2", "SSN2", 200));
        Node new_User = userList.getByID(userList.nextid - 1);
        // Verify user details
        assertNotNull(new_User);
        assertEquals(new_User.name, "Alice");
        assertEquals(new_User.address, "Address2");
        assertEquals(new_User.social_security, "SSN2");
        assertEquals(new_User.initial_deposit, 200);
    }

    @Test
    void testDeleteUser(){
        // Verify deletion
        userList.addUser(new Node("John", "Address1", "SSN1", 100));
        userList.addUser(new Node("Bob", "Address3", "SSN3", 300));
        userList.addUser(new Node("Alice", "Address2", "SSN2", 200));
        userList.deleteUser(2);
        Node deletedUser = userList.getByID(2);
        assertNull(deletedUser);
        // Verify id reassignment
        userList.addUser(new Node("Jack", "Address4", "SSN4", 400));
        Node newUser = userList.getByID(2);
        assertNotNull(newUser);
    }

    @Test
    void testpayUsertoUser(){
        // Verify payment between users
        userList.addUser(new Node("John", "Address1", "SSN1", 100));
        userList.addUser(new Node("Bob", "Address3", "SSN3", 300));
        userList.addUser(new Node("Alice", "Address2", "SSN2", 200));
        userList.payUsertoUser(2, 3, 100);
        Node payer = userList.getByID(2);
        Node payee = userList.getByID(3);
        assertEquals(payer.initial_deposit, 200);
        assertEquals(payee.initial_deposit, 300);
    }

    @Test
    void testgetMedianID(){
        //Verify median for odd number of users
        userList.addUser(new Node("John", "Address1", "SSN1", 100));
        userList.addUser(new Node("Bob", "Address3", "SSN3", 300));
        userList.addUser(new Node("Alice", "Address2", "SSN2", 200));
        assertEquals(userList.getMedianID(), 2);    
        //Verify median for even number of users
        userList.addUser(new Node("Jack", "Address4", "SSN4", 400));
        assertEquals(userList.getMedianID(), 2);    
    }

    @Test
    void testMergeAccounts(){
        // Verify merging of accounts
        userList.addUser(new Node("John", "Address1", "SSN1", 100));
        userList.addUser(new Node("Bob", "Address3", "SSN3", 300));
        userList.addUser(new Node("Bob", "Address3", "SSN3", 500));
        userList.addUser(new Node("Alice", "Address2", "SSN2", 200));
        userList.mergeAccounts(2, 3);
        Node aftermerge = userList.getByID(2);
        assertNotNull(aftermerge);
        assertEquals(aftermerge.initial_deposit, 800);
        assertNull(userList.getByID(3));
    }
    
    @Test
    void testmergeBanks(){
        //Set up two user lists
        User_LinkedList userList2 = new User_LinkedList();
        userList.addUser(new Node("John", "Address1", "SSN1", 100));
        userList.addUser(new Node("Bob", "Address3", "SSN3", 300));
        userList.addUser(new Node("Alice", "Address2", "SSN2", 200));
        userList.addUser(new Node("Jack", "Address4", "SSN4", 400));
        userList.addUser(new Node("Tom", "Address5", "SSN5", 500));

        userList2.addUser(new Node("Jerry", "Address6", "SSN6", 600));
        userList2.addUser(new Node("Mickey", "Address7", "SSN7", 700));
        userList2.addUser(new Node("Donald", "Address8", "SSN8", 800));
        userList2.addUser(new Node("Goofy", "Address9", "SSN9", 900));
        userList2.addUser(new Node("Pluto", "Address10", "SSN10", 1000));
        userList2.addUser(new Node("Daisy", "Address11", "SSN11", 1100));
        userList2.addUser(new Node("Huey", "Address12", "SSN12", 1200));
        
        userList.deleteUser(2);
        userList.deleteUser(3);
        userList2.deleteUser(3);
        userList2.deleteUser(6);
        User_LinkedList merged = userList.mergeBanks(userList, userList2);

        // Verify merged list
        Node mergedUser2 = merged.getByID(2);
        Node mergedUser3 = merged.getByID(3);
        Node mergedUser6 = merged.getByID(6);
        Node mergedUser8 = merged.getByID(8);
        assertEquals(mergedUser2.name, "Mickey");
        assertEquals(mergedUser3.name, "Jerry");
        assertEquals(mergedUser6.name, "Goofy");
        assertEquals(mergedUser8.name, "Pluto");
    }
}
