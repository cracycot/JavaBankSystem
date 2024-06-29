package entites.exceptions;

public class BankNotFoundException extends Exception {
    public BankNotFoundException() { super("Bank did not find "); }
}
