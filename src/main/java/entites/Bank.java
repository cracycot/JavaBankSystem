package entites;

public class Bank {
    private Float commission;
    private Float interestOnDeposit;

    public Bank(Float commission, Float interestOnDeposit) {
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
