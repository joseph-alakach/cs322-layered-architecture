package am.cs322.model;

import jakarta.annotation.Nonnull;

public record TransactionRequest(@Nonnull String accountNumber, double amount) {
}
