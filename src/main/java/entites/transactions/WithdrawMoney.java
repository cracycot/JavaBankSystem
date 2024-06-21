package entites.transactions;

import entites.bankAccounts.BankAccount;
import entites.transactions.Transaction;

public class WithdrawMoney extends Transaction {
    private BankAccount account;
    public void withdrawMoney(BankAccount account, float amount) {
        this.operationSum = amount;
        this.account = account;
        account.addBalance(amount);
    }

    @Override
    void cancelTransaction() {
    }
}