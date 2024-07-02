package org.example.entites;

import entites.bankAccounts.BankAccount;
import entites.bankAccounts.CreditAccount;
import entites.bankAccounts.DebitAccount;
import entites.exceptions.AccountIsBlockedException;
import entites.exceptions.InsufficientFundsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    private BankAccount bankAccount;
    private CreditAccount creditAccount;

    @BeforeEach
    void createBankAccount() {
         bankAccount = new DebitAccount(0, 10000, 3);
         bankAccount.addMoney(20000);
         creditAccount = new CreditAccount(0, 10000, 15000, 3);
         creditAccount.addMoney(1000);
         creditAccount.updateBlocked();
    }

    @Test
    void withdrawMoneyInsufficientFunds() {
        Assertions.assertThrows(InsufficientFundsException.class, () -> bankAccount.withdrawMoney(25000));
    }

    @Test
    void withdrawMoneyAccountIsBlocked() {
        Assertions.assertThrows(AccountIsBlockedException.class, () -> bankAccount.withdrawMoney(15000));
    }

    @Test
    void creditAccount() {
        Assertions.assertDoesNotThrow(() -> creditAccount.withdrawMoney(14000));
    }
}
