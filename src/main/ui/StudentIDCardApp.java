package ui;

import model.Account;
import model.Accounts;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// student ID management system application
public class StudentIDCardApp {
    private static final String JSON_STORE = "./data/accounts.json";
    private Account studentAccount;
    private Scanner input;
    private boolean quitApp = false;
    private Accounts listOfAccounts;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: run this application
    public StudentIDCardApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: user command
    //          inspired by TellerApp https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
    //          class: TellerApp, method: runTeller
    private void runApp() {
        String command = null;
        input = new Scanner(System.in);
        System.out.println("Welcome to StudentCard Management System! ");


        listOfAccounts = new Accounts();

        while (!quitApp) {
            displayMenu();
            command = input.next();
            processCommand(command);
        }
        System.out.println("You will quit the Application");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            createAccount();
        } else if (command.equals("2")) {
            logInAccount();
        } else if (command.equals("3")) {
            deleteAccount();
        } else if (command.equals("4")) {
            viewInfo();
        } else if (command.equals("5")) {
            checkBalance();
        } else if (command.equals("6")) {
            changePassword();
        } else if (command.equals("7")) {
            addMoney();
        } else if (command.equals("8")) {
            printAccounts();
        } else if (command.equals("9")) {
            quitApp = true;
        } else if (command.equals("10")) {
            saveAccounts();
        } else if (command.equals("11")) {
            loadAccounts();
        }
    }

    // EFFECTS: display menu
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\n1 -> create account");
        System.out.println("\n2 -> log in account");
        System.out.println("\n3 -> delete account");
        System.out.println("\n4 -> viewInfo");
        System.out.println("\n5 -> checkBalance");
        System.out.println("\n6 -> change password");
        System.out.println("\n7 -> add money");
        System.out.println("\n8 -> print accounts");
        System.out.println("\n9 -> quit application");
        System.out.println("\n10 -> save accounts");
        System.out.println("\n11 -> load accounts");
    }

    // MODIFIES: this
    // EFFECTS: log in account
    private void logInAccount() {
        System.out.println("Enter your student ID: ");
        int id = input.nextInt();
        boolean exist = false;
        for (int i = 0; i < listOfAccounts.getAccountsNumber(); i++) {
            if (id == listOfAccounts.getAccountId(i)) {
                System.out.println("Account exists! You can log! Please enter your password: ");
                exist = true;
                int password = input.nextInt();
                if (password == listOfAccounts.getAccountPassword(i)) {
                    System.out.println("Log in successfully");
                    studentAccount = listOfAccounts.getAccount(i);
                } else {
                    System.out.println("Sorry, password is not correct");
                }
            }

        }
        if (!exist) {
            System.out.println("ID does not exist");
        }

    }

    // MODIFIES: this
    // EFFECTS: create account
    private void createAccount() {
        System.out.println("Enter your student ID: ");
        int id = input.nextInt();
        boolean exist = false;

        for (int i = 0; i < listOfAccounts.getAccountsNumber(); i++) {
            if (id == listOfAccounts.getAccountId(i)) {
                System.out.println("ID exits, you don't need to create");
                exist = true;
                break;
            }
        }
        if (!exist) {
            System.out.println("Please enter the following information to create your account:  ");
            System.out.println("Enter your name: ");
            String name = input.next();
            System.out.println("Enter your initialBalance: ");
            double initialBalance = input.nextDouble();
            System.out.println("Enter your password: ");
            int password = input.nextInt();
            studentAccount = new Account(name, id, initialBalance, password);
            listOfAccounts.addAccount(studentAccount);
        }

    }

    // MODIFIES: this
    // EFFECTS: delete account
    private void deleteAccount() {
        System.out.println("Enter your ID: ");
        int id = input.nextInt();
        boolean exist = false;
        for (int i = 0; i < listOfAccounts.getAccountsNumber(); i++) {
            if (id == listOfAccounts.getAccountId(i)) {
                exist = true;
                listOfAccounts.deleteAccount(i);
                System.out.println("delete successfully");
                break;
            }
        }
        if (!exist) {
            System.out.println("Account does not exist, you can not delete");
        }


    }


    // MODIFIES: this
    // EFFECTS: change password
    private void changePassword() {

        System.out.println("Enter the new password: ");
        int newPassword = input.nextInt();

        if (newPassword >= 0) {
            studentAccount.setPassword(newPassword);
        } else {
            System.out.println("Sorry, cannot change the password.\n");
        }
        System.out.println("The current password is: " + studentAccount.getPassword());
    }


    // MODIFIES: this
    // EFFECTS: add money to student card
    private void addMoney() {
        System.out.println("Enter the amount you want add: ");
        double amount = input.nextDouble();

        if (amount > 0) {
            studentAccount.addMoney(amount);
        } else {
            System.out.println("Sorry, cannot add money.\n");
        }
        System.out.println("The number of current balance: " + studentAccount.getBalance());
    }

    // MODIFIES: this
    // EFFECTS: view the information of student card
    private void viewInfo() {
        System.out.println("The information of this account:");
        System.out.println("Student name: " + studentAccount.getName());
        System.out.println("Student ID: " + studentAccount.getId());
        System.out.println("Balance: " + studentAccount.getBalance());
        System.out.println("Password: " + studentAccount.getPassword());
    }

    // MODIFIES: this
    // EFFECTS: check the current balance
    private void checkBalance() {
        System.out.println("the current balance is: " + studentAccount.getBalance());
    }


    // MODIFIES: this
    // EFFECTS: print accounts
    private void printAccounts() {
        System.out.println("accounts: " + listOfAccounts.getListings());
    }

    // EFFECTS: saves the accounts to file
    private void saveAccounts() {
        try {
            jsonWriter.open();
            jsonWriter.write(listOfAccounts);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from file
    private void loadAccounts() {
        try {
            listOfAccounts = jsonReader.read();
            System.out.println("Loaded file from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}


