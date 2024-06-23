package entites.bankAccounts;

import entites.User;

public class DebitAccount extends BankAccount {
    private float interestBalance;

    public float getInterestBalance() {
        return interestBalance;
    }

    public void setInterestBalance(float interestBalance) {
        this.interestBalance = interestBalance;
    }
    @Override
    public void updateSum() {
        balance += (interestBalance / 365)*balance;
    }
}
