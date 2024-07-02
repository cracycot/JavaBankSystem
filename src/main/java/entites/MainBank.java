package entites;

import entites.bankAccounts.BankAccount;
import entites.exceptions.AccountIsBlockedException;
import entites.exceptions.BankNotFoundException;
import entites.exceptions.InsufficientFundsException;
import entites.transactions.Transaction;

import java.util.HashMap;

/**
 * Класс позволяет управлять банковской системой
 */
public class MainBank {

    /**
     * Singleton
     */
    public static final MainBank mainBank = new MainBank();
    /**
     * Поле содержащие все доступные банки
     */
    private HashMap<String, Bank> bankHashMap;

    private MainBank() {
        bankHashMap = new HashMap<>();
    }

    /**
     * Создание банка (добавление в банковскую систему)
     * @param Name
     * @param commission
     * @param interestOnDeposit (процент на остаток)
     * @param maxAmountBlocked (макс сумма доступная к снятию/переводу для неавторизованного пользователя)
     * @param creditLimit (макс сумма кредита)
     */
    public void createBank(String Name, float commission, float interestOnDeposit, float maxAmountBlocked, float creditLimit) {
        Bank bank = new Bank(Name, commission, interestOnDeposit, maxAmountBlocked, creditLimit);
        bankHashMap.put(Name, bank);
    }

    /**
     * Межбанковский перевод
     * @param bankFirstName
     * @param bankSecondName
     * @param idFirstAccount
     * @param idSecondAccount
     * @param amount
     * @throws InsufficientFundsException (недостаточно средств)
     * @throws AccountIsBlockedException (превышение лимита для аккаунта)
     */
    public void interbankTransfer(String bankFirstName, String bankSecondName, int idFirstAccount, int idSecondAccount, float amount) throws InsufficientFundsException, AccountIsBlockedException {
        Bank firstBank = bankHashMap.get(bankFirstName);
        Bank secondBank = bankHashMap.get(bankSecondName);
        BankAccount firstBankAccount = firstBank.getBankAccountById(idFirstAccount);
        BankAccount secondBankAccount = secondBank.getBankAccountById(idSecondAccount);
        firstBankAccount.withdrawMoney(amount);
        secondBankAccount.addMoney(amount);
    }

    /**
     * Отмена межбанковского перевода
     * @param bankFirstName
     * @param bankSecondName
     * @param idFirstAccount
     * @param idSecondAccount
     * @param idFirstTransaction
     * @param idSecondTransaction
     */
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

    /**
     * Сообщение банкам начислить проценты по дням
     * @param days
     */
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
            bank.interestUpdate();
        }
//        System.out.println("EndUpdate");
    }

    public void addCommission() {
        for (String bankName : bankHashMap.keySet()) {
            Bank bank = bankHashMap.get(bankName);
            bank.addCommision();
        }
    }

    /**
     * Получить банк
     * @param bankName
     * @return
     * @throws BankNotFoundException (банка не существует)
     */
    public Bank getBankByName(String bankName) throws BankNotFoundException {
        if (bankHashMap.containsKey(bankName)) {
            return bankHashMap.get(bankName);
        } else throw new BankNotFoundException();
    }

    /**
     * Вывод всех бакнов
     */
    public void printBanks() {
        for (String key : bankHashMap.keySet()) {
            Bank bank = bankHashMap.get(key);
            System.out.print(key);
            System.out.print(" ");
            System.out.print(bank.getCommission());
            System.out.print(" ");
            System.out.print(bank.getInterestOnDeposit());
            System.out.println();
        }
    }

}
