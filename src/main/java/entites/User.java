package entites;


import entites.bankAccounts.BankAccount;
import entites.exceptions.BankNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private static int counterId = 0;
    private int id;
    private boolean access;
    private String name;
    private String lastName;
    private String address;
    private String numberPassport;
    private HashMap<String, ArrayList<BankAccount>> bankAccountsHashMap;

    private void updateAccess() {
        access = !(address.equals("") || numberPassport.equals(""));
        for (String key : bankAccountsHashMap.keySet()) {
            ArrayList<BankAccount> bankAccounts = bankAccountsHashMap.get(key);
            for (BankAccount bankAccount : bankAccounts) {
                bankAccount.updateBlocked();
            }
        }
    }

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

    public void createDebitAccount(String bankName) throws BankNotFoundException {
        Bank bank = MainBank.mainBank.getBankByName(bankName);
        bank.createDebitAccount(id);
    }

    public void createCreditAccount(String bankName) throws BankNotFoundException {
        Bank bank = MainBank.mainBank.getBankByName(bankName);
        bank.createCreditAccount(id);
    }

    public void createDepositAccount(String bankName) throws BankNotFoundException {
        Bank bank = MainBank.mainBank.getBankByName(bankName);
        bank.createDepositAccount(id);
    }

    public int getId() {
        return id;
    }
}
