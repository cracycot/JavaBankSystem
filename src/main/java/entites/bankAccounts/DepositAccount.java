package entites.bankAccounts;

public class DepositAccount extends BankAccount{


    public DepositAccount(int idClient) {
        super(idClient);
    }

    @Override
    public void updateCommissionAmount(int days) {
        float percent = interestBalance;
        if (balance >= 50000) {
            percent += 0.5F;
            if (balance >= 100000) {
                percent += 0.5F;
            }
        }
        commissionAmount += (percent / 365) * balance * days;
    }
}
