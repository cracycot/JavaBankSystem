package entites;

import entites.bankAccounts.BankAccount;

import java.util.HashMap;

public class MainBank {
    private HashMap<String, Bank> bankHashMap;

    public void createBank(String Name, Float commission, Float interestOnDeposit) {
        Bank bank = new Bank(Name, commission, interestOnDeposit);
        bankHashMap.put(Name, bank);
    }
    public void interbankTransfer(String bankFirstName, String bankSecondName, int idFirstAccount, int idSecondAccount) {
        Bank firstBank = bankHashMap.get(bankFirstName);
        Bank secondBank = bankHashMap.get(bankSecondName);
        BankAccount firstBankAccount = firstBank.getBankAccountById(idFirstAccount);
        BankAccount secondBankAccount = secondBank.getBankAccountById(idSecondAccount);
    }
}
