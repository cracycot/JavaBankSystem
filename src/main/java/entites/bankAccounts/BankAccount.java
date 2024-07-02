package entites.bankAccounts;

import entites.exceptions.AccountIsBlockedException;
import entites.exceptions.InsufficientFundsException;
import entites.transactions.AddMoney;
import entites.transactions.Transaction;
import entites.transactions.TransferMoney;
import entites.transactions.WithdrawMoney;

import java.util.HashMap;

/**
 * Абстрактный класс для реализации счетов
 */
public abstract class BankAccount {

   protected static int counterIdAccount = 0;
   protected int idAccount;
   protected int idClient;
   protected float balance;
   protected float commissionAmount;
   protected float interestBalance;
   protected boolean isBlocked;
   protected float maxAmountBlocked;
   protected HashMap<Integer, Transaction> transactions;

   public BankAccount(int idClient, float maxAmountBlocked, float interestBalance) {
      isBlocked = true;
      idAccount = counterIdAccount;
      counterIdAccount += 1;
      this.balance = 0;
      this.commissionAmount = 0;
      this.maxAmountBlocked = maxAmountBlocked;
      this.interestBalance = interestBalance;
      this.idClient = idClient;
      this.transactions = new HashMap<>();
   }

   /**
    * Отмена ограничений на счете
    */
   public void updateBlocked() {
      isBlocked = false;
   }

   /**
    * Пополнение
    * @param amount
    */
   public void addMoney(float amount) { // add money используется как функция отмены
      AddMoney addBalance = new AddMoney(this, amount);
      transactions.put(addBalance.getId(), addBalance);
   }

   /**
    * Снятие
    * @param amount
    * @throws InsufficientFundsException (недостаточно средств)
    * @throws AccountIsBlockedException (превышена сумма перевода (пользователь не завершил регистрацию))
    */
   public void withdrawMoney(float amount) throws InsufficientFundsException, AccountIsBlockedException {
      if (amount <= balance) {
         if (!isBlocked || amount <= maxAmountBlocked) {
            WithdrawMoney withdrawBalance = new WithdrawMoney(this, amount);
            transactions.put(withdrawBalance.getId(), withdrawBalance);
            balance -= amount;
         } else throw new AccountIsBlockedException();
      } else throw new InsufficientFundsException();
   }

   /**
    * Перевод
    * @param amount
    * @param secondBank
    * @throws InsufficientFundsException (недостаточно средств)
    * @throws AccountIsBlockedException (превышена сумма перевода (пользователь не завершил регистрацию))
    */
   public void transferMoney(float amount, BankAccount secondBank) throws InsufficientFundsException, AccountIsBlockedException {
      if (amount <= balance) {
         if (!isBlocked || amount <= maxAmountBlocked) {
            TransferMoney transferBalance = new TransferMoney(this, secondBank, amount);
            transactions.put(transferBalance.getId(), transferBalance);
            balance -= amount;
         } else throw new AccountIsBlockedException();
      } else throw new InsufficientFundsException();
   }

   /**
    * Вывод транзакций
    */
   public void printTransactions() {
      System.out.println("Тип Сумма Id");
      for (int key : transactions.keySet()) {
         Transaction transaction = transactions.get(key);
         if (transaction instanceof AddMoney) {
            System.out.print("Пополнение ");
         } else if (transaction instanceof WithdrawMoney) {
            System.out.print("Снятие ");
         } else if (transaction instanceof  TransferMoney) {
            System.out.print("Перевод ");
         }
         System.out.println(transaction.getOperationSum() + " " + transaction.getId());
      }
   }

   public float getBalance() {
      return balance;
   }

   public void addBalance(float balance) { this.balance += balance; }

   public float getInterestBalance() {
      return interestBalance;
   }

   public void setInterestBalance(float interestBalance) {
      this.interestBalance = interestBalance;
   }

   public int getIdClient() {
      return idClient;
   }

   public void setIdClient(int idClient) {
      this.idClient = idClient;
   }

   public int getIdAccount() { return idAccount; }

   public void addCommission() {
      addMoney(commissionAmount);
      commissionAmount = 0;
   }

   public float getCommissionAmount() {
      return commissionAmount;
   }

   public Transaction getTransaction(int id) {
      return transactions.get(id);
   }

   public abstract void updateCommissionAmount(int days);

}
