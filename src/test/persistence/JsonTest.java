package persistence;

import model.Account;

import static org.junit.jupiter.api.Assertions.assertEquals;

// inspired by JsonSerializationDemo
public class JsonTest {

    protected void checkAccount(String name, int id, double balance, int password,
                                Account account) {
        assertEquals(name, account.getName());
        assertEquals(id, account.getId());
        assertEquals(balance, account.getBalance());
        assertEquals(password, account.getPassword());
    }
}
