package entites.transactions;

import entites.bankAccounts.BankAccount;
import entites.transactions.Transaction;

public class TransferMoney extends Transaction {
    private BankAccount AccountFirst;
    private BankAccount AccountSecond;
    public void transferMoney(BankAccount AccountFirst, BankAccount AccountSecond, Float amount) {
        this.operationSum = amount;
        this.AccountFirst = AccountFirst;
        this.AccountSecond = AccountSecond;
        AccountFirst.addBalance(-amount);
        AccountSecond.addBalance(amount);
    }
    @Override
    public void cancelTransaction() {

    }
}
