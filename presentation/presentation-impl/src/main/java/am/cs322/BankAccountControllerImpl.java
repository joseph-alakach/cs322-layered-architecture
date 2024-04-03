package am.cs322;

import am.cs322.model.BankAccountDTO;
import am.cs322.model.CreateBankAccountRequest;
import am.cs322.model.TransactionRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/bankAccount")
public class BankAccountControllerImpl implements BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountControllerImpl(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @Override
    @PostMapping
    public BankAccountDTO createBankAccount(@RequestBody CreateBankAccountRequest request) {
        return bankAccountService.createBankAccount(request.accountNumber(), request.balance());
    }

    @Override
    @PostMapping("/credit")
    public BankAccountDTO credit(@RequestBody TransactionRequest request) {
        return bankAccountService.credit(request.accountNumber(), request.amount());
    }

    @Override
    @PostMapping("/debit")
    public BankAccountDTO debit(@RequestBody TransactionRequest request) {
        return bankAccountService.debit(request.accountNumber(), request.amount());
    }
}
