package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a student account which include student id, student name, balance, password and
// number of sheets for printing
public class Account implements Writable {
    private String name; // the student's name
    private int id; // the student's id
    private double balance; // the current balance of the student card
    private int password; // the password of the account


    // REQUIRES: name has a non-zero length, id and password cannot be null, sheets >= 0 and balance >= 0
    // MODIFIES: this
    // EFFECTS: create a student account; if initialBalance >= 0, then balance is set to initialBalance, otherwise
    // balance is zero; if initialSheets >= 0. then sheets is set to initialSheets, otherwise balance is zero.
    public Account(String name, int id, double initialBalance, int password) {
        this.name = name;
        this.id = id;
        this.password = password;
        if (initialBalance >= 0) {
            balance = initialBalance;
        } else {
            balance = 0;
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public int getPassword() {
        return password;
    }


    // REQUIRES: newPassword should not be null
    // EFFECTS: set a new password
    public void setPassword(int newPassword) {
        password = newPassword;
    }

    // REQUIRES: amount >= 0
    // EFFECTS: add money
    public void addMoney(double amount) {
        balance = balance + amount;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("id", id);
        json.put("balance", balance);
        json.put("password", password);
        return json;
    }
}




