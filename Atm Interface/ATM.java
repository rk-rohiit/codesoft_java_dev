import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}

public class ATM extends JFrame {
    private BankAccount account;
    private JLabel balanceLabel;
    private JTextField amountField;

    public ATM(BankAccount account) {
        this.account = account;
        createUI();
        setLocationRelativeTo(null); 
    }

    private void createUI() {
        setTitle("Apna Bank - ATM Machine");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 102, 204));
        JLabel headerLabel = new JLabel("Apna Bank - ATM Machine", SwingConstants.CENTER);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        balanceLabel = new JLabel("Balance: ₹" + account.getBalance(), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(balanceLabel);

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        mainPanel.add(amountField);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton checkBalanceButton = new JButton("Check Balance");

        depositButton.setBackground(new Color(34, 177, 76));
        depositButton.setForeground(Color.WHITE);
        withdrawButton.setBackground(new Color(237, 28, 36));
        withdrawButton.setForeground(Color.WHITE);
        checkBalanceButton.setBackground(new Color(0, 102, 204));
        checkBalanceButton.setForeground(Color.WHITE);

        depositButton.setFont(new Font("Arial", Font.BOLD, 16));
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkBalanceButton.setFont(new Font("Arial", Font.BOLD, 16));

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw();
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBalancePopup();
            }
        });

        mainPanel.add(depositButton);
        mainPanel.add(withdrawButton);
        mainPanel.add(checkBalanceButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void deposit() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount > 0) {
                account.deposit(amount);
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Deposit Successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Enter a valid amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Enter a number.");
        }
    }

    private void withdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (account.withdraw(amount)) {
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Withdrawal Successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient balance or invalid amount.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid input! Enter a number.");
        }
    }

    private void showBalancePopup() {
        JOptionPane.showMessageDialog(this, "Your current balance is: ₹" + account.getBalance(), "Balance Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: ₹" + account.getBalance());
    }

    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(0); // Initial balance: ₹0
        ATM atm = new ATM(userAccount);
        atm.setVisible(true);
    }
}
