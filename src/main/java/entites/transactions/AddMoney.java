package entites.transactions;

import entites.bankAccounts.BankAccount;
import entites.transactions.Transaction;

public class AddMoney extends Transaction {
    private BankAccount account;
    public void addMoney(BankAccount account, float amount) {
        this.operationSum = amount;
        this.account = account;
        account.addBalance(amount);
    }
    @Override
    void cancelTransaction() {
        if (!canceled) {
            account.addBalance(-operationSum);
            canceled = true;
        }
    }
}
