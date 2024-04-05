package am.cs322;

import am.cs322.model.BankAccountDTO;

public interface BankAccountService {

    public BankAccountDTO createBankAccount(String firstName, String lastName, String accountNumber, double balance);
    public BankAccountDTO credit(String accountNumber, double amount);
    public BankAccountDTO debit(String accountNumber, double amount);

}