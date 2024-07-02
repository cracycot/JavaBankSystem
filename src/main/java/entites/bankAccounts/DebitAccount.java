package entites.bankAccounts;

import entites.User;

public class DebitAccount extends BankAccount {
    public DebitAccount(int idClient, float maxAmountBlocked, float interestBalance) {
        super(idClient, maxAmountBlocked, interestBalance);
    }

    @Override
    public void updateCommissionAmount(int days) {
        commissionAmount += (interestBalance / 365)*balance* days;
    }
}
