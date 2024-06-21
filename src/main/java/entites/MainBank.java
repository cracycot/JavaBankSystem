package entites;

import java.util.HashMap;

public class MainBank {
    private HashMap<String, Bank> bankHashMap;

    public void createBank(String Name, Float commission, Float interestOnDeposit) {
        Bank bank = new Bank(Name, commission, interestOnDeposit);
        bankHashMap.put(Name, bank);
    }
    public void interbankTransfer(String bankFirstName, String bankSecondName) {
        Bank firstBank = bankHashMap.get(bankFirstName);
        Bank secondBank = bankHashMap.get(bankSecondName);
    }
}
