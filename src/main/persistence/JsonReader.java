package persistence;

import model.Account;
import model.Accounts;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// A reader that can read accounts from a file
// inspired by JsonSerializationDemo
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads accounts from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Accounts read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccounts(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses accounts from JSON object and returns it
    private Accounts parseAccounts(JSONObject jsonObject) {
        Accounts as = new Accounts();
        addAccounts(as, jsonObject);
        return as;
    }

    // MODIFIES: as
    // EFFECTS: parses accounts from JSON object and adds them to accounts
    private void addAccounts(Accounts as, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("listOfAccounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addAccount(as, nextAccount);
        }
    }

    // MODIFIES: as
    // EFFECTS: parses account from JSON object and adds it to accounts
    private void addAccount(Accounts as, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int id = jsonObject.getInt("id");
        double balance = jsonObject.getDouble("balance");
        int password = jsonObject.getInt("password");
        Account account = new Account(name, id, balance, password);
        as.addAccount(account);
    }
}
