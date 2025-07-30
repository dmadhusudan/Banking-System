import java.util.ArrayList;
import java.util.List;

public class Account {
    private String username;
    private String password;
    private double balance;
    private List<Transaction> history;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
        this.history = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        history.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            history.add(new Transaction("Withdraw", amount));
            return true;
        }
        return false;
    }

    public List<Transaction> getHistory() {
        return history;
    }

    public String toDataString() {
        return username + "," + password + "," + balance;
    }

    public static Account fromDataString(String line) {
        String[] parts = line.split(",");
        Account acc = new Account(parts[0], parts[1]);
        acc.balance = Double.parseDouble(parts[2]);
        return acc;
    }
}
