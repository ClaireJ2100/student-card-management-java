package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountsTest {

    private Accounts accountTest;
    private Account account1;
    private Account account2;

    @BeforeEach
    public void runBefore() {
        accountTest = new Accounts();
        account1 = new Account("A", 123, 123, 123);
        account2 = new Account("B", 1, 1, 1);
    }

    @Test
    public void testAddAccount() {
        accountTest.addAccount(account1);
        assertTrue(accountTest.containsAccount(account1));
        assertFalse(accountTest.containsAccount(account2));
    }

    @Test
    public void testGetAccountsNumber() {
        accountTest.addAccount(account1);
        assertEquals(accountTest.getAccountsNumber(), 1);
    }

    @Test
    public void testGetListings() {
        accountTest.addAccount(account1);
        assertEquals(accountTest.getListings(), "\n" + "student name: " + "A" + "\n" + "\n"
                + "ID: " + 123 + "\n");
    }

    @Test
    public void testGetAccountId() {
        accountTest.addAccount(account1);
        assertEquals(123, accountTest.getAccountId(0));
    }

    @Test
    public void testGetAccountPassword() {
        accountTest.addAccount(account1);
        assertEquals(123, accountTest.getAccountPassword(0));
    }

    @Test
    public void testGetAccount() {
        accountTest.addAccount(account1);
        assertEquals(account1, accountTest.getAccount(0));
    }

    @Test
    public void testDeleteAccount() {
        accountTest.addAccount(account1);
        assertEquals(account1, accountTest.getAccount(0));
        accountTest.deleteAccount(0);
        assertEquals(0, accountTest.getAccountsNumber());
    }

}
