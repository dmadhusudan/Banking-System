import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        Account currentUser = null;

        while (true) {
            if (currentUser == null) {
                System.out.println("\n1. Register\n2. Login\n3. Exit");
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1) {
                    System.out.print("Username: ");
                    String user = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    if (bank.register(user, pass)) {
                        System.out.println("Registered successfully.");
                    } else {
                        System.out.println("Username already exists.");
                    }
                } else if (choice == 2) {
                    System.out.print("Username: ");
                    String user = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    currentUser = bank.login(user, pass);
                    if (currentUser != null) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                } else {
                    break;
                }
            } else {
                System.out.println("\n1. Deposit\n2. Withdraw\n3. Balance\n4. History\n5. Logout");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter amount: ");
                        double dep = sc.nextDouble();
                        currentUser.deposit(dep);
                        System.out.println("Deposited successfully.");
                        break;
                    case 2:
                        System.out.print("Enter amount: ");
                        double wd = sc.nextDouble();
                        if (currentUser.withdraw(wd)) {
                            System.out.println("Withdrawn successfully.");
                        } else {
                            System.out.println("Insufficient balance.");
                        }
                        break;
                    case 3:
                        System.out.println("Balance: $" + currentUser.getBalance());
                        break;
                    case 4:
                        for (Transaction t : currentUser.getHistory()) {
                            System.out.println(t);
                        }
                        break;
                    case 5:
                        currentUser = null;
                        bank.save();
                        break;
                }
            }
        }
        sc.close();
        bank.save();
        System.out.println("Exiting system.");
    }
}
