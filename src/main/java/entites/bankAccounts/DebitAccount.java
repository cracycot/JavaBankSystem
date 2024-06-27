package entites.bankAccounts;

import entites.User;

public class DebitAccount extends BankAccount {
    public DebitAccount(int idClient) {
        super(idClient);
    }

    @Override
    public void updateCommissionAmount(int days) {
        commissionAmount += (interestBalance / 365)*balance* days;
    }
}
