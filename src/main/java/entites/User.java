package entites;


import entites.bankAccounts.BankAccount;
import entites.exceptions.BankNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Класс пользователь
 */

public class User {
    private static int counterId = 0;
    private int id;
    private boolean access;
    private String name;
    private String lastName;
    private String address = "";
    private String numberPassport = "";
    private HashMap<String, ArrayList<BankAccount>> bankAccountsHashMap = new HashMap<>();

    public static int getCounterId() {
        return counterId;
    }

    public boolean isAccess() {
        return access;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getNumberPassport() {
        return numberPassport;
    }

    /**
     * Обновить статус аккаунта на заполненный
     */
    private void updateAccess() {
        access = !(address.isEmpty() || numberPassport.isEmpty());
        for (String key : bankAccountsHashMap.keySet()) {
            ArrayList<BankAccount> bankAccounts = bankAccountsHashMap.get(key);
            for (BankAccount bankAccount : bankAccounts) {
                bankAccount.updateBlocked();
            }
        }
    }

    /**
     * Builder  с обязательными параметрами имени и фамилии
     */

    public static class Builder {
        public static  User user;

        public  Builder(String name, String lastName)  {
            user = new User();
            user.id = counterId;
            user.access = false;
            counterId += 1;
            user.name = name;
            user.lastName = lastName;
        }

        public Builder addres(String address) {
            user.address = address;
            user.updateAccess();
            return this;
        }

        public Builder numberPassport(String numberPassport) {
            user.numberPassport = numberPassport;
            user.updateAccess();
            return this;
        }

        public User build() {
            user.updateAccess();
            return user;
        }

    }

    /**
     * Создание дебетового счета
     * @param bankName
     * @throws BankNotFoundException (Банк не найден)
     */
    public void createDebitAccount(String bankName) throws BankNotFoundException {
        Bank bank = MainBank.mainBank.getBankByName(bankName);
        bank.createDebitAccount(id);
    }
    /**
     * Создание кредитного счета
     * @param bankName
     * @throws BankNotFoundException (Банк не найден)
     */
    public void createCreditAccount(String bankName) throws BankNotFoundException {
        Bank bank = MainBank.mainBank.getBankByName(bankName);
        bank.createCreditAccount(id);
    }
    /**
     * Создание депозитного счета
     * @param bankName
     * @throws BankNotFoundException (Банк не найден)
     */
    public void createDepositAccount(String bankName) throws BankNotFoundException {
        Bank bank = MainBank.mainBank.getBankByName(bankName);
        bank.createDepositAccount(id);
    }

    public int getId() {
        return id;
    }
}
