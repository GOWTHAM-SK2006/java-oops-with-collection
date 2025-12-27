import java.util.*;


interface Transaction {
    void deposit(double amount);
    void withdraw(double amount);
}


abstract class Account implements Transaction {

    private int accountNo;
    private String holderName;
    protected double balance;

    public Account(int accountNo, String holderName, double balance) {
        this.accountNo = accountNo;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public abstract void calculateInterest();

    public void display() {
        System.out.println(
            "Acc No: " + accountNo +
            " | Name: " + holderName +
            " | Balance: " + balance
        );
    }
}


class SavingsAccount extends Account {

    public SavingsAccount(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    @Override
    public void calculateInterest() {
        balance += balance * 0.04;
        System.out.println("Savings interest added");
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount deposited");
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Amount withdrawn");
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

class CurrentAccount extends Account {

    public CurrentAccount(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    @Override
    public void calculateInterest() {
        System.out.println("No interest for current account");
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount deposited");
    }

    @Override
    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Amount withdrawn");
        } else {
            System.out.println("Insufficient balance");
        }
    }
}


class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public void addAccount(Account acc) {
        accounts.put(acc.getAccountNo(), acc);
    }

    public Account getAccount(int accNo) {
        return accounts.get(accNo);
    }

    public void showAllAccounts() {
        System.out.println("\n===== ALL ACCOUNTS =====");
        for (Account acc : accounts.values()) {
            acc.display();
        }
    }
}


public class BankingSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.print("Enter number of accounts: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.println("\nAccount " + (i + 1));
            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter Holder Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Balance: ");
            double balance = sc.nextDouble();

            System.out.print("Account Type (1-Savings, 2-Current): ");
            int type = sc.nextInt();

            Account acc;
            if (type == 1) {
                acc = new SavingsAccount(accNo, name, balance);
            } else {
                acc = new CurrentAccount(accNo, name, balance);
            }

            bank.addAccount(acc);

            System.out.print("Deposit amount: ");
            double dep = sc.nextDouble();
            acc.deposit(dep);

            System.out.print("Withdraw amount: ");
            double with = sc.nextDouble();
            acc.withdraw(with);

            acc.calculateInterest();
        }

        bank.showAllAccounts();
        sc.close();
    }
}
