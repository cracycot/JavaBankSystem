package entites.transactions;

import entites.bankAccounts.BankAccount;
import entites.transactions.Transaction;

public class WithdrawMoney extends Transaction {
    private BankAccount account;
    public WithdrawMoney(BankAccount account, float amount) {
        this.operationSum = amount;
        this.account = account;
        account.addBalance(amount);
    }

    @Override
    public boolean cancelTransaction() {
        if (!canceled) {
            account.addBalance(operationSum);
            canceled = true;
        }
        return canceled;
    }
}
