package persistence;

import model.Account;
import model.Accounts;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// inspired by JsonSerializationDemo
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Accounts as = new Accounts();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyAccounts() {
        try {
            Accounts as = new Accounts();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccounts.json");
            writer.open();
            writer.write(as);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccounts.json");
            as = reader.read();
            assertEquals(0, as.getAccountsNumber());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralAccounts() {
        try {
            Accounts as = new Accounts();
            as.addAccount(new Account("A", 1, 1, 1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccounts.json");
            writer.open();
            writer.write(as);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccounts.json");
            as = reader.read();
            Account account2 = new Account("B", 2, 1,1);
            as.addAccount(account2);
            assertEquals(2, as.getAccountsNumber());
            checkAccount("B", 2,1, 1, as.getAccount(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
