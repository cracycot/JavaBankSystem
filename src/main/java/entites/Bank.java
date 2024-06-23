package entites;

import entites.bankAccounts.BankAccount;
import entites.transactions.Transaction;

import java.util.HashMap;

public class Bank {
    private String name;
    private Float commission;
    private Float interestOnDeposit;

    private HashMap<Integer, BankAccount> bankAccountHashMap;
    public Bank(String name, Float commission, Float interestOnDeposit) {
        this.name = name;
        this.commission = commission;
        this.interestOnDeposit = interestOnDeposit;
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
}
