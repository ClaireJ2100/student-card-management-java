package persistence;

import model.Account;
import model.Accounts;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// inspired by JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Accounts as = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccounts() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccounts.json");
        try {
            Accounts as = reader.read();
            assertEquals(0, as.getAccountsNumber());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccounts() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAccounts.json");
        try {
            Accounts as = reader.read();
            Account account1 = new Account("A", 1, 1, 1);
            as.addAccount(account1);
            assertEquals(1, as.getAccountsNumber());
            checkAccount("A", 1, 1, 1, as.getAccount(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
