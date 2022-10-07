package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class Accounts implements Writable {

    private ArrayList<Account> listOfAccounts;


    // create a new ArrayList in the constructor
    public Accounts() {
        listOfAccounts = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add an account to the list
    public void addAccount(Account account) {
        EventLog.getInstance().logEvent(new Event("Added account: " + "\n" + "name: " + account.getName()
                + "\n" + "ID: " + account.getId() + "\n\n"));
        listOfAccounts.add(account);
    }

    // EFFECTS: return the size of list
    public int getAccountsNumber() {
        return listOfAccounts.size();
    }

    // EFFECTS: return student id
    public int getAccountId(int index) {
        return listOfAccounts.get(index).getId();
    }

    // EFFECTS: return student password
    public int getAccountPassword(int index) {
        return listOfAccounts.get(index).getPassword();
    }

    // EFFECTS: return student account
    public Account getAccount(int index) {
        return listOfAccounts.get(index);
    }

    // EFFECTS: delete student account
    public void deleteAccount(int index) {

        EventLog.getInstance().logEvent(new Event("Removed account: " + "\n" + "name: "
                + listOfAccounts.get(index).getName()
                + "\n" + "ID: " + listOfAccounts.get(index).getId() + "\n\n"));
        listOfAccounts.remove(index);
    }

    // EFFECTS: list all student accounts
    public String getListings() {
        String accountsListings = "";
        for (int i = 0; i < getAccountsNumber(); i++) {
            accountsListings += "\n" + "student name: " + listOfAccounts.get(i).getName() + "\n" + "\n"
                    + "ID: " + listOfAccounts.get(i).getId() + "\n";
        }
        return accountsListings;
    }

    // EFFECTS: returns true if the given account is in list, otherwise return false
    public boolean containsAccount(Account account) {
        return listOfAccounts.contains(account);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("listOfAccounts", accountsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Account a : listOfAccounts) {
            jsonArray.put(a.toJson());
        }

        return jsonArray;
    }
}
