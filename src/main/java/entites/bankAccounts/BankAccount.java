package entites.bankAccounts;

import entites.transactions.AddMoney;
import entites.transactions.Transaction;
import entites.transactions.TransferMoney;
import entites.transactions.WithdrawMoney;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BankAccount {
   protected String idAccount;
   protected String idClient;
   protected float balance;

   public void addMoney(Float amount) {
      AddMoney addBalance = new AddMoney();
      addBalance.addMoney(this, amount);
//      transactions.put(addBalance.getId(), addBalance);
   }
   public void withdrawMoney(Float amount) {
      WithdrawMoney withdrawBalance = new WithdrawMoney();
      withdrawBalance.withdrawMoney(this, amount);
//      transactions.put(withdrawBalance.getId(), withdrawBalance);
   }
   public float getBalance() {
      return balance;
   }

   public void addBalance(float balance) {
      this.balance += balance;
   }
}
