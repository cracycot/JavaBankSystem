package entites.transactions;

import java.util.HashMap;

public abstract class Transaction {
    protected  float operationSum = 0;
    private static int counterId = 0;
    private int id;

    protected boolean canceled = false;
    public Transaction() {
        id = counterId;
        counterId += 1;
    }

    public int getId() {
        return id;
    }
    public abstract void cancelTransaction();
}
