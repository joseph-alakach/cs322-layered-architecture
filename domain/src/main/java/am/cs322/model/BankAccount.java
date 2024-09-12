package am.cs322.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "\"BankAccount\"")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BankAccount(){

    }

    public BankAccount(User user, String accountNumber, double balance){
        this.user = user;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount bankAccount = (BankAccount) o;
        return Objects.equals(accountNumber, bankAccount.accountNumber) && balance==bankAccount.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public User getUser() {
        return user;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
