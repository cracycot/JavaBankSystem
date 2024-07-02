package entites.bankAccounts;

import entites.exceptions.AccountIsBlockedException;
import entites.exceptions.InsufficientFundsException;
import entites.transactions.TransferMoney;
import entites.transactions.WithdrawMoney;

public class CreditAccount extends BankAccount {

    private float creditLimit;

    public CreditAccount(int idClient, float maxAmountBlocked, float interestBalance, float creditLimit) {
        super(idClient, maxAmountBlocked, interestBalance);
        this.creditLimit = creditLimit;
    }

    @Override
    public void withdrawMoney(float amount) throws InsufficientFundsException, AccountIsBlockedException {
        if (amount <= (balance + creditLimit)) {
            if (!isBlocked || amount <= maxAmountBlocked) {
                WithdrawMoney withdrawBalance = new WithdrawMoney(this, amount);
                transactions.put(withdrawBalance.getId(), withdrawBalance);
                addBalance(-amount);
            } else throw new AccountIsBlockedException();
        } else throw new InsufficientFundsException();
    }

    @Override
    public void transferMoney(float amount, BankAccount secondBank) throws InsufficientFundsException, AccountIsBlockedException {
        if (amount <= (balance + creditLimit)) {
            if (!isBlocked || amount <= maxAmountBlocked) {
                TransferMoney transferBalance = new TransferMoney(this, secondBank, amount);
                transactions.put(transferBalance.getId(), transferBalance);
                balance -= amount;
            } else throw new AccountIsBlockedException();
        } else throw new InsufficientFundsException();
    }

    @Override
    public void updateCommissionAmount(int days) {
        if (balance < 0) {
            commissionAmount -= (interestBalance / 365 * days);
        }
    }
}
