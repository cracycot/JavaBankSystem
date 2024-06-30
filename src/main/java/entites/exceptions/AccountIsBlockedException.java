package entites.exceptions;

public class AccountIsBlockedException extends Exception{
    public AccountIsBlockedException() { super("Account is blocked, add user information"); }
}
