package entites.exceptions;

public class BankNotFoundException extends Exception {
    public BankNotFoundException() { super("Account is blocked, add user information"); }
}
