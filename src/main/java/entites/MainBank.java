package entites;

import entites.bankAccounts.BankAccount;
import entites.exceptions.AccountIsblockedException;
import entites.exceptions.BankNotFoundException;
import entites.exceptions.InsufficientFundsException;
import entites.transactions.Transaction;

import java.util.HashMap;

public class MainBank {
    public static final MainBank mainBank = new MainBank(); // Singleton
    private HashMap<String, Bank> bankHashMap;

    private MainBank() { // Приватный конструктор
        bankHashMap = new HashMap<>();
    }

    public void createBank(String Name, float commission, float interestOnDeposit, float maxAmountBlocked) {
        Bank bank = new Bank(Name, commission, interestOnDeposit, maxAmountBlocked);
        bankHashMap.put(Name, bank);
    }
    public void interbankTransfer(String bankFirstName, String bankSecondName, int idFirstAccount, int idSecondAccount, float amount) throws InsufficientFundsException, AccountIsblockedException {
        Bank firstBank = bankHashMap.get(bankFirstName);
        Bank secondBank = bankHashMap.get(bankSecondName);
        BankAccount firstBankAccount = firstBank.getBankAccountById(idFirstAccount);
        BankAccount secondBankAccount = secondBank.getBankAccountById(idSecondAccount);
        firstBankAccount.withdrawMoney(amount);
        secondBankAccount.addMoney(amount);
    }
    public void cancelInterbankTransfer(String bankFirstName, String bankSecondName,  int idFirstAccount, int idSecondAccount, int idFirstTransaction, int idSecondTransaction) {
        Bank firstBank = bankHashMap.get(bankFirstName);
        Bank secondBank = bankHashMap.get(bankSecondName);
        BankAccount firstBankAccount = firstBank.getBankAccountById(idFirstAccount);
        BankAccount secondBankAccount = secondBank.getBankAccountById(idSecondAccount);
        Transaction firstTransaction = firstBankAccount.getTransaction(idFirstTransaction);
        Transaction secondTransaction = secondBankAccount.getTransaction(idSecondTransaction);
        firstTransaction.cancelTransaction();
        secondTransaction.cancelTransaction();
    }
    // можно подвязать kafka
    public void interestUpdate(int days) {
        for (String bankName : bankHashMap.keySet()) {
            Bank bank = bankHashMap.get(bankName);
            bank.interestUpdate(days);
        }
    }
    public void interestUpdate() {
        for (String bankName : bankHashMap.keySet()) {
            Bank bank = bankHashMap.get(bankName);
            bank.interestUpdate(0);
        }
    }
    public Bank getBankByName(String bankName) throws BankNotFoundException {
        if (bankHashMap.containsKey(bankName)) {
            return bankHashMap.get(bankName);
        } else throw new BankNotFoundException();
    }

}
