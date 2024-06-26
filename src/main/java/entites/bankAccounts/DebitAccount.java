package entites.bankAccounts;

import entites.User;

public class DebitAccount extends BankAccount {
    @Override
    public void updateCommissionAmount(int days) {
        commissionAmount += (interestBalance / 365)*balance* days;
    }
}
