package entites;

import entites.bankAccounts.BankAccount;
import entites.bankAccounts.CreditAccount;
import entites.bankAccounts.DebitAccount;
import entites.bankAccounts.DepositAccount;
import entites.transactions.Transaction;

import java.util.HashMap;

/**
 * Класс банка
 */
public class Bank {
    private String name;
    private float commission;
    private float interestOnDeposit;
    private float maxAmountBlocked;
    private float creditLimit;
    private HashMap<Integer, BankAccount> bankAccountHashMap;

    public Bank(String name, float commission, float interestOnDeposit, float maxAmountBlocked, float creditLimit) {
        this.bankAccountHashMap = new HashMap<>();
        this.name = name;
        this.commission = commission;
        this.interestOnDeposit = interestOnDeposit;
        this.maxAmountBlocked = maxAmountBlocked;
        this.creditLimit = creditLimit;
    }

    public BankAccount getBankAccountById (int idBankAccount) {
        return bankAccountHashMap.get(idBankAccount);
    }

    /**
     * Обновить коммиссию для всех счетов
     * @param commission
     */
    public void setCommission(Float commission) {
        this.commission = commission;
        for (int key : bankAccountHashMap.keySet()) {
            BankAccount bankAccount = bankAccountHashMap.get(key);
            if (bankAccount instanceof CreditAccount) {
                bankAccount.setInterestBalance(commission);
            }
        }
    }

    /**
     * Обновить процент на остаток
     * @param interestOnDeposit
     */
    public void setInterestOnDeposit(Float interestOnDeposit) {
        this.interestOnDeposit = interestOnDeposit;
        for (int key : bankAccountHashMap.keySet()) {
            BankAccount bankAccount = bankAccountHashMap.get(key);
            if (!(bankAccount instanceof CreditAccount)) {
                bankAccount.setInterestBalance(interestOnDeposit);
            }
        }
    }

    public Float getCommission() {
        return commission;
    }

    public Float getInterestOnDeposit() {
        return interestOnDeposit;
    }

    /**
     *  Начислить вознаграждение/коммиссию на счет
     * @param days
     */
    public void interestUpdate(int days) {
        for (Integer idAccount : bankAccountHashMap.keySet()) {
            BankAccount account = bankAccountHashMap.get(idAccount);
            account.updateCommissionAmount(days);
        }
    }
    public void interestUpdate() {
        for (int idAccount : bankAccountHashMap.keySet()) {
            BankAccount account = bankAccountHashMap.get(idAccount);
            account.updateCommissionAmount(1);
        }
    }
    public void addCommision() {
        for (Integer idAccount : bankAccountHashMap.keySet()) {
            BankAccount account = bankAccountHashMap.get(idAccount);
            account.addCommission();
        }
    }

    /**
     * Отмена внутрибанковской транзакции
     * @param idBankAccount
     * @param idTransaction
     */
    public void cancelTransaction(int idBankAccount, int idTransaction) {
        BankAccount bankAccount = bankAccountHashMap.get(idBankAccount);
        Transaction transaction = bankAccount.getTransaction(idTransaction);
        transaction.cancelTransaction();
    }

    /**
     * Создание счетов
     * @param userId
     */
    public void createDebitAccount(int userId) {
        DebitAccount debitAccount = new DebitAccount(userId, maxAmountBlocked, interestOnDeposit);
        bankAccountHashMap.put(debitAccount.getIdClient(), debitAccount);
    }

    public void createCreditAccount(int userId) {
        CreditAccount creditAccount = new CreditAccount(userId, maxAmountBlocked, commission, commission );
        bankAccountHashMap.put(creditAccount.getIdClient(), creditAccount);
    }

    public void createDepositAccount(int userId) {
        DepositAccount depositAccount = new DepositAccount(userId, maxAmountBlocked,  interestOnDeposit);
        bankAccountHashMap.put(depositAccount.getIdClient(), depositAccount);
    }

    /**
     * Вывод всех счетов и транзакций на них
     */
    public void printAccounts() {
        for (int key : bankAccountHashMap.keySet()) {
            BankAccount  bankAccount = bankAccountHashMap.get(key);
            System.out.println("Тип счета баланс Id Id владельца сумма неначисленных процентов");
            if (bankAccount instanceof CreditAccount) {
                System.out.print("Кредитный ");
            } else if (bankAccount instanceof DebitAccount) {
                System.out.print("Дебитовый ");
            } else if (bankAccount instanceof DepositAccount) {
                System.out.print("Депозитный ");
            }
            System.out.println(bankAccount.getBalance() + " " + bankAccount.getIdAccount() + " " + bankAccount.getIdClient() + " " + bankAccount.getCommissionAmount());
            bankAccount.printTransactions();
        }
    }
}
