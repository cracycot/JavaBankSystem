package entites.bankAccounts;

public class DepositAccount extends BankAccount{
    private float interestBalance;

    public float getInterestBalance() {
        return interestBalance;
    }

    public void setInterestBalance(float interestBalance) {
        this.interestBalance = interestBalance;
    }

    @Override
    void updateSum() {
        balance += (interestBalance / 365)*balance;
    }
}
