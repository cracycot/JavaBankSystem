package entites.bankAccounts;

import entites.exceptions.InsufficientFundsException;
import entites.transactions.TransferMoney;
import entites.transactions.WithdrawMoney;

public class CreditAccount extends BankAccount {

    private float creditLimit;

    public CreditAccount(int idClient, float creditLimit) {
        super(idClient);
        this.creditLimit = creditLimit;
    }

    @Override
    public void updateCommissionAmount(int days) {
        if (balance < 0) {
            commissionAmount -= (interestBalance / 365 * days);
        }
    }
}
