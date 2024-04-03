package am.cs322.model;

import jakarta.annotation.Nonnull;

public record CreateBankAccountRequest(@Nonnull String accountNumber, double balance) {
}
