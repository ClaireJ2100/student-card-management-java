package ui;

import model.Account;
import model.Accounts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Represents the GUI when user want to manage accounts
public class ManageGUI extends JFrame implements ActionListener {

    private Account account;
    private Accounts accounts;
    private JButton createAccount;
    private JButton deleteAccount;
    private JButton printAccounts;
    private JButton addAccount;
    private JPanel menu;
    private JTextField t1;
    private JLabel name;
    private JLabel id;
    private JTextField t2;
    private JTextField t3;
    private JLabel initialBalance;
    private JTextField t4;
    private JLabel password;
    private JPanel listingsPage;
    private JLabel listings;
    private boolean listed;
    private JLabel accountListed;
    private JPanel accountListingsPanel;
    private JPanel deleteListingsPage;
    private JTextField d1;
    private JLabel name1;
    private JLabel id1;
    private JTextField d2;
    private JTextField d3;
    private JLabel initialBalance1;
    private JTextField d4;
    private JLabel password1;


    // EFFECTS: Create layout for ManageGUI
    public ManageGUI(Account account, Accounts accounts, JLabel listings) {
        super("Manage Accounts");

        this.account = account;
        this.accounts = accounts;
        this.listings = listings;

        createAccount = new JButton("Create Account");
        deleteAccount = new JButton("Delete Account");
        printAccounts = new JButton("Print Accounts");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(700, 600));

        initializeMenu();

        addButtons(createAccount, deleteAccount, printAccounts);

        addActionToButton();

        makeListYourAccountPanel();

        makeAccountsPanel();

        menu.setVisible(true);

        makeDeleteAccountPanel();


    }

    // EFFECTS: Initialize main menu panel and set background color
    public void initializeMenu() {
        menu = new JPanel();
        menu.setBackground(Color.lightGray);
        add(menu);
        listings.setText("No accounts now");
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to mainMenu
    public void addButton(JButton createAccount, JPanel panel) {
        createAccount.setFont(new Font("Dialog", Font.BOLD, 12));
        createAccount.setBackground(Color.white);
        panel.add(createAccount);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: add every button
    public void addButtons(JButton createAccount, JButton deleteAccount, JButton printAccounts) {

        addButton(createAccount, menu);
        addButton(deleteAccount, menu);
        addButton(printAccounts, menu);
    }

    // MODIFIES: this
    // EFFECTS: Set action to each button
    public void addActionToButton() {

        createAccount.addActionListener(this);
        createAccount.setActionCommand("Create Account");
        deleteAccount.addActionListener(this);
        deleteAccount.setActionCommand("Delete Account");
        printAccounts.addActionListener(this);
        printAccounts.setActionCommand("Print Account");
    }


    // MODIFIES: this
    // EFFECTS: Buttons on landing page
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create Account")) {
            initializeCreatePanel();
        } else if (e.getActionCommand().equals("Add account to listings")) {
            addAccountToListings();
        } else if (e.getActionCommand().equals("Delete Account")) {
            initDelete();
        } else if (e.getActionCommand().equals("delete account")) {
            removeListing();
        } else if (e.getActionCommand().equals("Print Account")) {
            showAccountListingPanel();
        }
    }

    // EFFECTS: make the creating new account panel true
    public void initializeCreatePanel() {
        add(listingsPage);
        listingsPage.setVisible(true);
        menu.setVisible(false);
        deleteListingsPage.setVisible(false);
    }


    // MODIFIES: this
    // EFFECTS: Creates the panel for the user to input new account
    public void makeListYourAccountPanel() {

        listingsPage = new JPanel(new GridLayout(2, 2));

        createListingsPage();
        addLabelsToListings();
    }

    // MODIFIES: this
    // EFFECTS: Generates the fields for the user to type into
    public void createListingsPage() {

        addAccount = new JButton("Add account");
        addAccount.setActionCommand("Add account to listings");
        addAccount.addActionListener(this);

        id = new JLabel("Name:");
        t1 = new JTextField(5);
        name = new JLabel("ID:");
        t2 = new JTextField(5);
        initialBalance = new JLabel("Initial Balance:");
        t3 = new JTextField(5);
        password = new JLabel("Password:");
        t4 = new JTextField(5);

        listed = false;
        listingLabelSettings();
        accountListed = new JLabel();

    }

    // EFFECTS: Changes certain attributes of the labels and text fields
    private void listingLabelSettings() {

        addAccount.setFont(new Font("Dialog", Font.BOLD, 12));

        name.setFont(new Font("Dialog", Font.BOLD, 15));
        id.setFont(new Font("Dialog", Font.BOLD, 15));
        initialBalance.setFont(new Font("Dialog", Font.BOLD, 15));
        password.setFont(new Font("Dialog", Font.BOLD, 15));

        t1.setMaximumSize(new Dimension(1200, 400));
        t2.setMaximumSize(new Dimension(1200, 400));
        t3.setMaximumSize(new Dimension(1200, 400));
        t4.setMaximumSize(new Dimension(1200, 400));


    }

    // EFFECTS: Adds the user input labels onto the panel
    public void addLabelsToListings() {

        listingsPage.add(addAccount);

        listingsPage.add(id);
        listingsPage.add(t1);
        listingsPage.add(name);
        listingsPage.add(t2);
        listingsPage.add(initialBalance);
        listingsPage.add(t3);
        listingsPage.add(password);
        listingsPage.add(t4);

    }

    // MODIFIES: this
    // EFFECTS: Adds the user given listing into the Cars object to be displayed
    public void addAccountToListings() {

        try {
            account = new Account(t1.getText(), Integer.parseInt(t2.getText()), Double.parseDouble(t3.getText()),
                    Integer.parseInt(t4.getText()));
            accounts.addAccount(account);
            // Uses HTML tags to create a multi line text label
            listings.setText("<html><pre>Current Listings: \n" + accounts.getListings() + "\n</pre></html>");
            listed = true;
            accountListed.setText("account listed? " + listed);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please try again");
        } catch (IndexOutOfBoundsException e) {
            listings.setText("Please initialize listings file before proceeding");
        }

    }

    // MODIFIES: this
    // EFFECTS: Removes account from accounts listings
    public void removeListing() {

        try {
            account = new Account(d1.getText(), Integer.parseInt(d2.getText()), Double.parseDouble(d3.getText()),
                    Integer.parseInt(d4.getText()));
            int id = account.getId();
            boolean exist = false;
            for (int i = 0; i < accounts.getAccountsNumber(); i++) {
                if (id == accounts.getAccountId(i)) {
                    exist = true;
                    accounts.deleteAccount(i);
                    System.out.println("delete successfully");
                    break;
                }
            }
            if (!exist) {
                System.out.println("Account does not exist, you can not delete");
            }

        } catch (NullPointerException e) {
            System.out.println("Please add an account before attempting to remove it");
        } catch (IndexOutOfBoundsException e) {
            listings.setText("Please initialize listings file before proceeding");
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates the panel that displays the current accounts
    public void makeAccountsPanel() {

        JLabel pic = new JLabel();
        accountListingsPanel = new JPanel(new GridLayout(2, 1));
        JScrollPane scrollPane = new JScrollPane(listings, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        pic.setIcon(new ImageIcon("./data/students.png"));
        pic.setMinimumSize(new Dimension(5, 5));

        accountListingsPanel.add(pic);

        listings.setFont(new Font("Dialog", Font.BOLD, 13));
        accountListingsPanel.add(scrollPane);
        listings.setText("<html><pre>Current Listings: \n" + accounts.getListings() + "\n</pre></html>");
    }

    // EFFECTS: Add accountListingPanel
    public void showAccountListingPanel() {
        add(accountListingsPanel);
        accountListingsPanel.setVisible(true);
        menu.setVisible(false);
        listingsPage.setVisible(false);
        accountListed.setText("Account listed?" + listed);
    }

    // delete account

    // EFFECTS: initialize delete panel
    public void initDelete() {
        add(deleteListingsPage);
        deleteListingsPage.setVisible(true);
        menu.setVisible(false);
        listingsPage.setVisible(false);
    }

    // MODIFIES: this
    // EFFECTS: Creates the panel for the user to delete account
    public void makeDeleteAccountPanel() {

        deleteListingsPage = new JPanel(new GridLayout(2, 2));

        createDeleteListingsPage();
        addLabelsToDeleteListings();
    }

    // MODIFIES: this
    // EFFECTS: Generates the fields for the user to type into
    public void createDeleteListingsPage() {

        deleteAccount = new JButton("Delete account");
        deleteAccount.setActionCommand("delete account");
        deleteAccount.addActionListener(this);

        id1 = new JLabel("Name:");
        d1 = new JTextField(5);
        name1 = new JLabel("ID:");
        d2 = new JTextField(5);
        initialBalance1 = new JLabel("Initial Balance:");
        d3 = new JTextField(5);
        password1 = new JLabel("Password:");
        d4 = new JTextField(5);

        deleteListingLabelSettings();

    }

    // EFFECTS: Changes certain attributes of the labels and text fields
    private void deleteListingLabelSettings() {

        deleteAccount.setFont(new Font("Dialog", Font.BOLD, 12));

        name1.setFont(new Font("Dialog", Font.BOLD, 15));
        id1.setFont(new Font("Dialog", Font.BOLD, 15));
        initialBalance1.setFont(new Font("Dialog", Font.BOLD, 15));
        password1.setFont(new Font("Dialog", Font.BOLD, 15));

        d1.setMaximumSize(new Dimension(1200, 400));
        d2.setMaximumSize(new Dimension(1200, 400));
        d3.setMaximumSize(new Dimension(1200, 400));
        d4.setMaximumSize(new Dimension(1200, 400));

    }

    // EFFECTS: Adds the user input labels onto the panel
    public void addLabelsToDeleteListings() {

        deleteListingsPage.add(deleteAccount);

        deleteListingsPage.add(id1);
        deleteListingsPage.add(d1);
        deleteListingsPage.add(name1);
        deleteListingsPage.add(d2);
        deleteListingsPage.add(initialBalance1);
        deleteListingsPage.add(d3);
        deleteListingsPage.add(password1);
        deleteListingsPage.add(d4);

    }

}
