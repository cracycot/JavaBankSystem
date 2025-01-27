package entites.transactions;

import entites.bankAccounts.BankAccount;
import entites.transactions.Transaction;

public class TransferMoney extends Transaction {
    private BankAccount AccountFirst;
    private BankAccount AccountSecond;
    public TransferMoney(BankAccount AccountFirst, BankAccount AccountSecond, float amount) {
        this.operationSum = amount;
        this.AccountFirst = AccountFirst;
        this.AccountSecond = AccountSecond;
        AccountFirst.addBalance(-amount);
        AccountSecond.addBalance(amount);
    }
    @Override
    public boolean  cancelTransaction() {
        if (!canceled) {
            AccountFirst.addBalance(operationSum);
            AccountSecond.addBalance(-operationSum);
            canceled = true;
        }
        return canceled;
    }
}
