package ui;

import model.Account;
import model.Accounts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelfServiceGUI extends JFrame implements ActionListener {

    private Account account;
    private Accounts accounts;
    private JButton logInAccount;
    private JButton changePassword;
    private JButton checkBalance;
    private JButton viewInfo;
    private JButton addMoney;
    private JPanel menu;
    private JButton logIn;
    private JTextField t1;
    private JLabel name;
    private JLabel id;
    private JTextField t2;
    private JTextField t3;
    private JLabel initialBalance;
    private JTextField t4;
    private JLabel password;
    private JPanel accountPage;
    private JLabel oldPassword;
    private JLabel newPassword;
    private JTextField c1;
    private JTextField c2;
    private JPanel passwordPage;
    private JButton yesPassword;
    private JLabel balance;
    private JTextField b1;
    private JPanel balancePage;
    private JLabel info;
    private JLabel name1;
    private JTextField i1;
    private JLabel id1;
    private JTextField i2;
    private JLabel balance1;
    private JTextField i3;
    private JLabel password1;
    private JTextField i4;
    private JPanel viewInfoPage;
    private JTextField a1;
    private JLabel amount;
    private JPanel moneyPage;
    private JLabel currentBalance;
    private JTextField a2;
    private Account account1;
    private Account account2;
    private Account account3;
    private Account account4;

    // Create layout for self-service
    public SelfServiceGUI(Accounts accounts) {
        super("Self Service");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.accounts = accounts;
        initializeMenu();
        logInAccount = new JButton("Log In Account");
        changePassword = new JButton("Change Password");
        checkBalance = new JButton("Check Balance");
        viewInfo = new JButton("View Info");
        addMoney = new JButton("Add Money");
        addMesPic();
        setPreferredSize(new Dimension(700, 600));

        addButtons(logInAccount, changePassword, checkBalance, viewInfo, addMoney);
        addActionToButton();

        makeListYourAccountPanel();
        makeChangingPasswordPanel();
        makeCheckBalancePanel();
        makeViewInfoPanel();
        makeAddMoneyPanel();

        menu.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to mainMenu
    public void addButton(JButton logInAccount, JPanel panel) {
        logInAccount.setFont(new Font("Dialog", Font.BOLD, 12));
        logInAccount.setBackground(Color.white);
        panel.add(logInAccount);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: add message and picture
    public void addMesPic() {
        ImageIcon icon = new ImageIcon("./data/campus1.jpeg");
        JLabel mainImage = new JLabel(icon, JLabel.CENTER);
        menu.add(mainImage);
        JLabel welcomeMeg = new JLabel("Please log in first!");
        welcomeMeg.setFont(new Font("Dialog", Font.BOLD, 15));
        menu.add(welcomeMeg);
    }

    // EFFECTS: add every button
    public void addButtons(JButton logInAccount, JButton changePassword, JButton checkBalance, JButton viewInfo,
                           JButton addMoney) {

        addButton(logInAccount, menu);
        addButton(changePassword, menu);
        addButton(checkBalance, menu);
        addButton(viewInfo, menu);
        addButton(addMoney, menu);
    }

    // EFFECTS: Initialize main menu panel and set background color
    public void initializeMenu() {
        menu = new JPanel();
        menu.setBackground(Color.lightGray);
        add(menu);

    }

    // MODIFIES: this
    // EFFECTS: Set action to each button
    public void addActionToButton() {

        logInAccount.addActionListener(this);
        logInAccount.setActionCommand("Log In Account");
        changePassword.addActionListener(this);
        changePassword.setActionCommand("Change Password");
        checkBalance.addActionListener(this);
        checkBalance.setActionCommand("Check Balance");
        viewInfo.addActionListener(this);
        viewInfo.setActionCommand("View Info");
        addMoney.addActionListener(this);
        addMoney.setActionCommand("Add Money");
    }

    // MODIFIES: this
    // EFFECTS: Buttons on landing page
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Log In Account")) {
            initialLogging();
        } else if (e.getActionCommand().equals("log in account")) {
            loginAccount();
        } else if (e.getActionCommand().equals("Change Password")) {
            initializePassword();
        } else if (e.getActionCommand().equals("Yes, change password")) {
            changePassword();
        } else if (e.getActionCommand().equals("Check Balance")) {
            initializeBalance();
        } else if (e.getActionCommand().equals("check balance")) {
            setCheckBalance();
        } else if (e.getActionCommand().equals("View Info")) {
            initializeView();
        } else if (e.getActionCommand().equals("view info")) {
            setViewInfo();
        } else if (e.getActionCommand().equals("Add Money")) {
            initializeMoney();
        } else if (e.getActionCommand().equals("add money")) {
            addMoney();
        }
    }

    // MODIFIES: this
    // EFFECTS: Generates the fields for the user to type into
    public void setLogInAccount() {

        logIn = new JButton("Log in account");
        logIn.setActionCommand("log in account");
        logIn.addActionListener(this);

        name = new JLabel("Name:");
        t1 = new JTextField(10);
        id = new JLabel("ID:");
        t2 = new JTextField(10);
        initialBalance = new JLabel("Initial Balance:");
        t3 = new JTextField(10);
        password = new JLabel("Password:");
        t4 = new JTextField(10);

        accountLabelSettings();

    }

    // EFFECTS: Changes certain attributes of the labels and text fields
    private void accountLabelSettings() {

        name.setFont(new Font("Dialog", Font.BOLD, 15));
        id.setFont(new Font("Dialog", Font.BOLD, 15));
        initialBalance.setFont(new Font("Dialog", Font.BOLD, 15));
        password.setFont(new Font("Dialog", Font.BOLD, 15));

        t1.setMaximumSize(new Dimension(1200, 400));
        t2.setMaximumSize(new Dimension(1200, 400));
        t3.setMaximumSize(new Dimension(1200, 400));
        t4.setMaximumSize(new Dimension(1200, 400));

    }

    // EFFECTS: initialize log in panel
    public void initialLogging() {
        add(accountPage);
        menu.setVisible(false);
        accountPage.setVisible(true);
        passwordPage.setVisible(false);
        viewInfoPage.setVisible(false);
        balancePage.setVisible(false);
        moneyPage.setVisible(false);
    }


    // MODIFIES: this
    // EFFECTS: Creates the panel for the user to input new account
    public void makeListYourAccountPanel() {

        accountPage = new JPanel(new GridLayout(2, 2));

        setLogInAccount();
        addLabels();
    }

    // EFFECTS: Adds the user input labels onto the panel
    public void addLabels() {
        accountPage.add(logIn);

        accountPage.add(name);
        accountPage.add(t1);
        accountPage.add(id);
        accountPage.add(t2);
        accountPage.add(initialBalance);
        accountPage.add(t3);
        accountPage.add(password);
        accountPage.add(t4);
    }

    // MODIFIES: this
    // EFFECTS: Log in account
    public void loginAccount() {

        try {
            account = new Account(t1.getText(), Integer.parseInt(t2.getText()), Double.parseDouble(t3.getText()),
                    Integer.parseInt(t4.getText()));

            accounts.addAccount(account);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please try again");
        } catch (IndexOutOfBoundsException e) {
            //
        }
    }

    // EFFECTS: initialize password panel
    public void initializePassword() {
        add(passwordPage);
        passwordPage.setVisible(true);
        menu.setVisible(false);
        accountPage.setVisible(false);
        viewInfoPage.setVisible(false);
        balancePage.setVisible(false);
        moneyPage.setVisible(false);

    }


    // EFFECTS: create fields for changing password
    public void createPasswordPage() {

        oldPassword = new JLabel("Old Password:");
        c1 = new JTextField(5);
        newPassword = new JLabel("New Password:");
        c2 = new JTextField(5);

        yesPassword = new JButton("Yes");

        yesPassword.setActionCommand("Yes, change password");
        yesPassword.addActionListener(this);

        passwordLabelsSettings();

    }

    // EFFECTS: change labels and text fields for changing password
    public void passwordLabelsSettings() {
        yesPassword.setFont(new Font("Dialog", Font.BOLD, 12));

        oldPassword.setFont(new Font("Dialog", Font.BOLD, 15));
        newPassword.setFont(new Font("Dialog", Font.BOLD, 15));

        c1.setMaximumSize(new Dimension(1200, 400));
        c2.setMaximumSize(new Dimension(1200, 400));
    }

    // EFFECTS: Adds the user input labels onto the panel
    public void addLabelsToPassword() {
        passwordPage.add(yesPassword);

        passwordPage.add(oldPassword);
        passwordPage.add(c1);
        passwordPage.add(newPassword);
        passwordPage.add(c2);

    }

    // MODIFIES: this
    // EFFECTS: create the panel for the user to change password
    public void makeChangingPasswordPanel() {
        passwordPage = new JPanel(new GridLayout(2, 2));

        createPasswordPage();
        addLabelsToPassword();
    }

    // MODIFIES: this
    // EFFECTS: change password
    public void changePassword() {
        int b = 0;
        b = accounts.getAccountsNumber();
        account2 = accounts.getAccount(b - 1);

        account2.setPassword(Integer.parseInt(c2.getText()));
    }


    // EFFECTS: create fields for changing password
    public void createInfoPage() {

        info = new JLabel("Information: ");

        id1 = new JLabel("ID:");
        i1 = new JTextField(10);
        name1 = new JLabel("Name:");
        i2 = new JTextField(10);
        balance1 = new JLabel("Balance:");
        i3 = new JTextField(10);
        password1 = new JLabel("Password:");
        i4 = new JTextField(10);


        viewInfo = new JButton("View Information");

        viewInfo.setActionCommand("view info");
        viewInfo.addActionListener(this);

        viewInfoLabelsSettings();

    }

    // EFFECTS: change labels and text fields for changing password
    public void viewInfoLabelsSettings() {
        viewInfo.setFont(new Font("Dialog", Font.BOLD, 12));

        name1.setFont(new Font("Dialog", Font.BOLD, 24));
        id1.setFont(new Font("Dialog", Font.BOLD, 24));
        balance1.setFont(new Font("Dialog", Font.BOLD, 24));
        password1.setFont(new Font("Dialog", Font.BOLD, 24));

        i1.setMaximumSize(new Dimension(1200, 400));
        i2.setMaximumSize(new Dimension(1200, 400));
        i3.setMaximumSize(new Dimension(1200, 400));
        i4.setMaximumSize(new Dimension(1200, 400));
    }

    // EFFECTS: Adds the user input labels onto the panel
    public void addLabelsToViewInfo() {
        viewInfoPage.add(viewInfo);

        viewInfoPage.add(id1);
        viewInfoPage.add(i1);
        viewInfoPage.add(name1);
        viewInfoPage.add(i2);
        viewInfoPage.add(balance1);
        viewInfoPage.add(i3);
        viewInfoPage.add(password1);
        viewInfoPage.add(i4);

    }

    // EFFECTS: initialize view info panel
    public void initializeView() {
        add(viewInfoPage);
        balancePage.setVisible(false);
        passwordPage.setVisible(false);
        menu.setVisible(false);
        accountPage.setVisible(false);
        viewInfoPage.setVisible(true);
        moneyPage.setVisible(false);
    }


    // MODIFIES: this
    // EFFECTS: create the panel for the user to change password
    public void makeViewInfoPanel() {
        viewInfoPage = new JPanel(new GridLayout(2, 1));

        createInfoPage();
        addLabelsToViewInfo();
    }

    // EFFECTS: View student card information
    public void setViewInfo() {

        int a = 0;
        a = accounts.getAccountsNumber();
        account1 = accounts.getAccount(a - 1);

        i1.setText(Integer.toString(account1.getId()));
        i2.setText(account1.getName());
        i3.setText(Double.toString(account1.getBalance()));
        i4.setText(Integer.toString(account1.getPassword()));
    }

    // EFFECTS: check balance
    public void setCheckBalance() {
        int c = 0;
        c = accounts.getAccountsNumber();
        account3 = accounts.getAccount(c - 1);

        b1.setText(Double.toString(account3.getBalance()));
    }

    // EFFECTS: create fields for changing password
    public void createBalancePage() {

        balance = new JLabel("Balance:");
        b1 = new JTextField(10);

        checkBalance = new JButton("Check Balance");

        checkBalance.setActionCommand("check balance");
        checkBalance.addActionListener(this);

        balanceLabelsSettings();

    }

    // EFFECTS: change labels and text fields for changing password
    public void balanceLabelsSettings() {
        checkBalance.setFont(new Font("Dialog", Font.BOLD, 12));


        balance.setFont(new Font("Dialog", Font.BOLD, 24));

        b1.setMaximumSize(new Dimension(1200, 400));
    }

    // EFFECTS: Adds the user input labels onto the panel
    public void addLabelsToBalance() {
        balancePage.add(checkBalance);
        balancePage.add(balance);
        balancePage.add(b1);

    }

    // EFFECTS: initialize check balance panel
    public void initializeBalance() {
        add(balancePage);
        balancePage.setVisible(true);
        passwordPage.setVisible(false);
        menu.setVisible(false);
        accountPage.setVisible(false);
        viewInfoPage.setVisible(false);
        moneyPage.setVisible(false);

    }

    // MODIFIES: this
    // EFFECTS: create the panel for the user to add money
    public void makeCheckBalancePanel() {
        balancePage = new JPanel(new GridLayout(3, 1));

        createBalancePage();
        addLabelsToBalance();
    }

    // EFFECTS: initialize add money panel
    public void initializeMoney() {
        add(moneyPage);
        balancePage.setVisible(false);
        passwordPage.setVisible(false);
        menu.setVisible(false);
        accountPage.setVisible(false);
        viewInfoPage.setVisible(false);
        moneyPage.setVisible(true);

    }


    // EFFECTS: create fields for changing password
    public void createAddMoneyPage() {

        amount = new JLabel("Amount:");
        a1 = new JTextField(10);
        currentBalance = new JLabel("Current Balance: ");
        a2 = new JTextField(10);

        addMoney = new JButton("Add Money");

        addMoney.setActionCommand("add money");
        addMoney.addActionListener(this);

        moneyLabelsSettings();

    }

    // EFFECTS: change labels and text fields for changing password
    public void moneyLabelsSettings() {

        addMoney.setFont(new Font("Dialog", Font.BOLD, 12));


        amount.setFont(new Font("Dialog", Font.BOLD, 24));
        currentBalance.setFont(new Font("Dialog", Font.BOLD, 24));

        a1.setMaximumSize(new Dimension(1200, 400));
        a2.setMaximumSize(new Dimension(1200, 400));
    }

    // EFFECTS: Adds the user input labels onto the panel
    public void addLabelsToMoney() {
        moneyPage.add(addMoney);
        moneyPage.add(amount);
        moneyPage.add(a1);
        moneyPage.add(currentBalance);
        moneyPage.add(a2);

    }

    // MODIFIES: this
    // EFFECTS: create the panel for the user to change password
    public void makeAddMoneyPanel() {
        moneyPage = new JPanel(new GridLayout(2, 1));

        createAddMoneyPage();
        addLabelsToMoney();
    }

    // EFFECTS: add money to student card
    public void addMoney() {
        int d = 0;
        d = accounts.getAccountsNumber();
        account4 = accounts.getAccount(d - 1);
        account4.addMoney(Double.parseDouble(a1.getText()));
        a2.setText(Double.toString(account4.getBalance()));
    }

}
