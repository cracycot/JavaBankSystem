package org.example.entites;

import entites.bankAccounts.BankAccount;
import entites.bankAccounts.DebitAccount;
import entites.transactions.AddMoney;
import entites.transactions.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionTest {
    private Transaction transaction;

    @BeforeEach
    void makeTransaction() {
        BankAccount bankAccount = new DebitAccount(0, 10000);
        transaction = new AddMoney(bankAccount, 100000);
    }

    @Test
    void cancelTransaction() {
        boolean result = transaction.cancelTransaction();
        Assertions.assertEquals(result, true);
    }

    @Test
    void cancelTransactionTwice() {
        boolean result = transaction.cancelTransaction();
        result = transaction.cancelTransaction();
        Assertions.assertEquals(result, true);
    }
}
