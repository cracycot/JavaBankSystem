package entites.bankAccounts;

import entites.exceptions.InsufficientFundsException;
import entites.transactions.TransferMoney;
import entites.transactions.WithdrawMoney;

public class CreditAccount extends BankAccount {

    private float creditLimit;

//    @Override
//    public void withdrawMoney(float amount, ) {
//        WithdrawMoney withdrawBalance = new WithdrawMoney();
//        withdrawBalance.withdrawMoney(this, amount);
//        transactions.put(withdrawBalance.getId(), withdrawBalance);
//        balance -= amount;
//    }
//    @Override
//    public void transferMoney(float amount, BankAccount secondBank){
//        TransferMoney transferBalance = new TransferMoney();
//        transferBalance.transferMoney(this, secondBank, amount);
//        transactions.put(transferBalance.getId(), transferBalance);
//        balance -= amount;
//    }
    @Override
    public void updateCommissionAmount(int days) {
        if (balance < 0) {
            commissionAmount -= (interestBalance / 365 * days);
        }
    }
}
