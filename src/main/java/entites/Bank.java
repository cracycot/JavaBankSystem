package entites;

import entites.bankAccounts.BankAccount;
import entites.bankAccounts.CreditAccount;
import entites.bankAccounts.DebitAccount;
import entites.bankAccounts.DepositAccount;
import entites.transactions.Transaction;

import java.util.HashMap;

public class Bank {
    private String name;
    private float commission;
    private float interestOnDeposit;
    private float maxAmountBlocked;


    private HashMap<Integer, BankAccount> bankAccountHashMap;
    public Bank(String name, float commission, float interestOnDeposit, float maxAmountBlocked) {
        this.name = name;
        this.commission = commission;
        this.interestOnDeposit = interestOnDeposit;
        this.maxAmountBlocked = maxAmountBlocked;
    }

    public BankAccount getBankAccountById (int idBankAccount) {
        return bankAccountHashMap.get(idBankAccount);
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public void setInterestOnDeposit(Float interestOnDeposit) {
        this.interestOnDeposit = interestOnDeposit;
    }

    public Float getCommission() {
        return commission;
    }

    public Float getInterestOnDeposit() {
        return interestOnDeposit;
    }
    
    public void interestUpdate(int days) {
        for (Integer idAccount : bankAccountHashMap.keySet()) {
            BankAccount account = bankAccountHashMap.get(idAccount);
            account.updateCommissionAmount(days);
        }
    }
    public void interestUpdate() {
        for (Integer idAccount : bankAccountHashMap.keySet()) {
            BankAccount account = bankAccountHashMap.get(idAccount);
            account.updateCommissionAmount(1);
        }
    }
    public void addCommision() {
        for (Integer idAccount : bankAccountHashMap.keySet()) {
            BankAccount account = bankAccountHashMap.get(idAccount);
            account.addCommision();
        }
    }
    public void cancelTransaction(int idBankAccount, int idTransaction) {
        BankAccount bankAccount = bankAccountHashMap.get(idBankAccount);
        Transaction transaction = bankAccount.getTransaction(idTransaction);
        transaction.cancelTransaction();
    }
    public void createDebitAccount(int userId) {
        DebitAccount debitAccount = new DebitAccount(userId, maxAmountBlocked);
        bankAccountHashMap.put(debitAccount.getIdClient(), debitAccount);
    }

    public void createCreditAccount(int userId) {
        CreditAccount creditAccount = new CreditAccount(userId, maxAmountBlocked, commission);
        bankAccountHashMap.put(creditAccount.getIdClient(), creditAccount);
    }

    public void createDepositAccount(int userId) {
        DepositAccount depositAccount = new DepositAccount(userId, maxAmountBlocked);
        bankAccountHashMap.put(depositAccount.getIdClient(), depositAccount);
    }
}
