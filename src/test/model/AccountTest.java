package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account accountTest1;
    private Account accountTest2;

    @BeforeEach
    public void runBefore() {
        accountTest1 = new Account("Amy", 123456, 2, 1234);
        accountTest2 = new Account("Kitty", 222, -1, 111);
    }

    @Test
    public void testSetAccount() {
        assertEquals(accountTest1.getName(), "Amy");
        assertEquals(accountTest1.getId(), 123456);
        assertEquals(accountTest1.getBalance(), 2);
        assertEquals(accountTest1.getPassword(), 1234);

        assertEquals(accountTest2.getBalance(), 0);

    }

    @Test
    public void testChangingPassword() {
        accountTest1.setPassword(2222);
        assertEquals(accountTest1.getPassword(), 2222);
    }

    @Test
    public void testAddMoney() {
        accountTest1.addMoney(20);
        assertEquals(accountTest1.getBalance(), 22);
    }

}
