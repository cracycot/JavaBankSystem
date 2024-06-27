package entites.bankAccounts;

import entites.User;

public class DebitAccount extends BankAccount {
    public DebitAccount(int idClient, float maxAmountBlocked) {
        super(idClient, maxAmountBlocked);
    }

    @Override
    public void updateCommissionAmount(int days) {
        commissionAmount += (interestBalance / 365)*balance* days;
    }
}
