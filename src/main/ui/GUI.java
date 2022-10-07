package ui;

import model.Account;
import model.Accounts;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Represents the user interface
public class GUI extends JFrame implements ActionListener {

    private static final String JSON_STORE = "./data/accounts.json";
    private Accounts listOfAccounts;
    private Account account;

    private JPanel mainMenu;
    private JButton manageBt;
    private JButton selfBt;
    private JButton saveBt;
    private JButton loadBt;
    private JButton quitBt;
    private JLabel listings;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    // EFFECTS: Construct the student card management system
    public GUI() {

        super("Student Card Management Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 600));
        initializeMenu();


        JLabel welcomeMeg = new JLabel("Welcome to student card system!");
        ImageIcon icon = new ImageIcon("./data/interface_view.jpeg");
        JLabel mainImage = new JLabel(icon, JLabel.CENTER);
        addLabel(welcomeMeg);
        mainMenu.add(mainImage);

        JLabel infoMeg = new JLabel("Please load accounts first!");
        infoMeg.setFont(new Font("Dialog", Font.BOLD, 15));
        mainMenu.add(infoMeg);

        initializeMenuButtons();

        addButtons(manageBt, selfBt, saveBt, loadBt, quitBt);

        addActionToButton();

        account = new Account("A", 1, 1, 1);
        mainMenu.setVisible(true);


    }

    // EFFECTS: Initialize main menu panel and set background color
    public void initializeMenu() {
        mainMenu = new JPanel();
        mainMenu.setBackground(Color.lightGray);
        add(mainMenu);
        listings = new JLabel();
        listings.setText("No accounts now");

    }

    // EFFECTS: Creates the welcome text label
    public void addLabel(JLabel jlabel) {
        jlabel.setFont(new Font("Dialog", Font.BOLD, 25));
        mainMenu.add(jlabel);
    }


    // EFFECTS: Initializes main menu buttons and set labels
    public void initializeMenuButtons() {
        manageBt = new JButton("Manage accounts");
        selfBt = new JButton("Self-account service");
        saveBt = new JButton("Save accounts");
        loadBt = new JButton("Load accounts");
        quitBt = new JButton("Exit application");
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to mainMenu
    public void addButton(JButton manageBt, JPanel panel) {
        manageBt.setFont(new Font("Dialog", Font.BOLD, 12));
        manageBt.setBackground(Color.white);
        panel.add(manageBt);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: add every button
    public void addButtons(JButton manageBt, JButton selfBt,
                           JButton saveBt, JButton loadBt, JButton quitBt) {

        addButton(manageBt, mainMenu);
        addButton(selfBt, mainMenu);
        addButton(saveBt, mainMenu);
        addButton(loadBt, mainMenu);
        addButton(quitBt, mainMenu);
    }


    // MODIFIES: this
    // EFFECTS: Set action to each button
    public void addActionToButton() {

        manageBt.addActionListener(this);
        manageBt.setActionCommand("Manage Accounts");
        selfBt.addActionListener(this);
        selfBt.setActionCommand("Self Service");
        saveBt.addActionListener(this);
        saveBt.setActionCommand("Save Accounts");
        loadBt.addActionListener(this);
        loadBt.setActionCommand("Load Accounts");
        quitBt.addActionListener(this);
        quitBt.setActionCommand("Quit Application");
    }

    // MODIFIES: this
    // EFFECTS: Buttons on landing page
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Manage Accounts")) {
            new ManageGUI(account, listOfAccounts, listings);
        } else if (e.getActionCommand().equals("Self Service")) {
            new SelfServiceGUI(listOfAccounts);
        } else if (e.getActionCommand().equals("Save Accounts")) {
            saveAccounts();
        } else if (e.getActionCommand().equals("Load Accounts")) {
            loadAccounts();
        } else if (e.getActionCommand().equals("Quit Application")) {
            System.exit(0);
        }
    }

    // EFFECTS: saves the accounts to file
    private void saveAccounts() {
        jsonWriter = new JsonWriter(JSON_STORE);
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
        jsonReader = new JsonReader(JSON_STORE);
        try {
            listOfAccounts = jsonReader.read();
            listings.setText("<html><pre>Current Listings: \n" + listOfAccounts.getListings() + "\n</pre></html>");
            System.out.println("Loaded file from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
