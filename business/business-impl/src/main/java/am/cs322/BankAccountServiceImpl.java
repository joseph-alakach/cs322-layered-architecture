package am.cs322;

import am.cs322.model.BankAccount;
import am.cs322.model.BankAccountDTO;
import am.cs322.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final UserRepository userRepository;

    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BankAccountDTO createBankAccount(String firstName, String lastName, String accountNumber, double balance) {
        Optional<User> optionalUser = userRepository.findByFirstNameAndLastName(firstName, lastName);
        if(!optionalUser.isPresent()){
            return new BankAccountDTO("User with fullName: "+firstName+" "+lastName+" doesn't exist, you need to sign-up for a user account first");
        }
        Optional<BankAccount> optionalBankAccount = getBankAccountByAccountNumber(accountNumber);
        if(optionalBankAccount.isPresent()){
            return new BankAccountDTO("A bank account with account number:"+accountNumber+" already exists");
        }
        User user = optionalUser.get();
        BankAccount createdBankAccount = bankAccountRepository.save(new BankAccount(user, accountNumber, balance));
        user.addBankAccount(createdBankAccount);
        userRepository.save(user);
        return new BankAccountDTO("User FullName: "+createdBankAccount.getUser().getFirstName()+" "+createdBankAccount.getUser().getLastName()+",  Account Number: "+createdBankAccount.getAccountNumber()+ ",  Balance: "+createdBankAccount.getBalance()+"$");
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
            return new BankAccountDTO("User FullName: "+bankAccount.getUser().getFirstName()+" "+bankAccount.getUser().getLastName()+",  Account number:"+bankAccount.getAccountNumber()+ ",  Balance:"+bankAccount.getBalance()+"$");
        }else {
            return new BankAccountDTO("Bank account with account number:"+accountNumber+" does not exists");
        }
    }


    public BankAccountDTO debit(String accountNumber, double amount) {
        Optional<BankAccount> optionalBankAccount = getBankAccountByAccountNumber(accountNumber);
        if(optionalBankAccount.isPresent()){
            BankAccount bankAccount = optionalBankAccount.get();
            if (bankAccount.getBalance() >= amount) {
                bankAccount.setBalance(bankAccount.getBalance() - amount);
                bankAccountRepository.save(bankAccount);
                return new BankAccountDTO("User FullName: "+bankAccount.getUser().getFirstName()+" "+bankAccount.getUser().getLastName()+",  Account number:"+bankAccount.getAccountNumber()+ ",  Balance:"+bankAccount.getBalance()+"$");
            } else {
                return new BankAccountDTO("Insufficient balance :(");
            }
        }else {
            return new BankAccountDTO("Bank account with account number:"+accountNumber+" does not exists");
        }
    }

}