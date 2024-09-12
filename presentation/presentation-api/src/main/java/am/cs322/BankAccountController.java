package am.cs322;

import am.cs322.model.CreateBankAccountRequest;
import am.cs322.model.TransactionRequest;
import am.cs322.model.BankAccountDTO;

public interface BankAccountController {

    public BankAccountDTO createBankAccount(CreateBankAccountRequest request);
    public BankAccountDTO credit(TransactionRequest request);
    public BankAccountDTO debit(TransactionRequest request);

}
