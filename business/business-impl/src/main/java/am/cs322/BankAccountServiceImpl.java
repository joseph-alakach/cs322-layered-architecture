package am.cs322;

import am.cs322.model.BankAccount;
import am.cs322.model.BankAccountDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public BankAccountDTO createBankAccount(String accountNumber, double balance) {
        BankAccount createdBankAccount = bankAccountRepository.save(new BankAccount(accountNumber, balance));
        return new BankAccountDTO("Account number: "+createdBankAccount.getAccountNumber()+ " , Balance: "+createdBankAccount.getBalance()+"$");
    }

    public Optional<BankAccount> getBankAccountByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber);
    }
    public BankAccountDTO credit(String accountNumber, double amount) {
        Optional<BankAccount> optionalBankAccount = getBankAccountByAccountNumber(accountNumber);
        if(optionalBankAccount.isPresent()){
            BankAccount bankAccount = optionalBankAccount.get();
            bankAccount.setBalance(bankAccount.getBalance() + amount);
            bankAccountRepository.save(bankAccount);
            return new BankAccountDTO("Account number:"+bankAccount.getAccountNumber()+ " , Balance:"+bankAccount.getBalance()+"$");
        }else {
            throw new IllegalArgumentException("Bank account with account number:"+accountNumber+" does not exist");
        }
    }


    public BankAccountDTO debit(String accountNumber, double amount) {
        Optional<BankAccount> optionalBankAccount = getBankAccountByAccountNumber(accountNumber);
        if(optionalBankAccount.isPresent()){
            BankAccount bankAccount = optionalBankAccount.get();
            if (bankAccount.getBalance() >= amount) {
                bankAccount.setBalance(bankAccount.getBalance() - amount);
                bankAccountRepository.save(bankAccount);
                return new BankAccountDTO("Account number:"+bankAccount.getAccountNumber()+ " , Balance:"+bankAccount.getBalance()+"$");
            } else {
                throw new IllegalArgumentException("Insufficient balance :(");
            }
        }else {
            throw new IllegalArgumentException("Bank account with account number:"+accountNumber+" does not exist");
        }
    }

}