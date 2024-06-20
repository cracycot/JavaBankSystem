package entites;

import java.util.ArrayList;

abstract class BankAccount {
   protected String id;
   protected String idClient;

   protected ArrayList<Transaction> transactions;

   public void addMoney(Float amount) {
      Transaction addBalance = new TransferMoney();

   }

}
