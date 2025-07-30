import java.io.*;
import java.util.*;

public class Bank {
    private Map<String, Account> accounts;
    private final String FILE_NAME = "accounts.txt";

    public Bank() {
        accounts = new HashMap<>();
        loadAccounts();
    }

    private void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                Account acc = Account.fromDataString(line);
                accounts.put(acc.getUsername(), acc);
            }
        } catch (IOException e) {
            // file may not exist yet
        }
    }

    private void saveAccounts() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Account acc : accounts.values()) {
                pw.println(acc.toDataString());
            }
        } catch (IOException e) {
            System.out.println("Error saving accounts.");
        }
    }

    public boolean register(String username, String password) {
        if (accounts.containsKey(username)) {
            return false;
        }
        Account acc = new Account(username, password);
        accounts.put(username, acc);
        saveAccounts();
        return true;
    }

    public Account login(String username, String password) {
        if (!accounts.containsKey(username)) return null;
        Account acc = accounts.get(username);
        if (acc.checkPassword(password)) {
            return acc;
        }
        return null;
    }

    public void save() {
        saveAccounts();
    }
}
