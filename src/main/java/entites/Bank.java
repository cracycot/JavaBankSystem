package entites;

import entites.transactions.Transaction;

import java.util.HashMap;

public class Bank {
    private String name;
    private Float commission;
    private Float interestOnDeposit;
    protected HashMap<Integer, Transaction> transactions;

    public Bank(String name, Float commission, Float interestOnDeposit) {
        this.name = name;
        this.commission = commission;
        this.interestOnDeposit = interestOnDeposit;
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
