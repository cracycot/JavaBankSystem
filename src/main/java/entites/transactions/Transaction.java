package entites.transactions;

import java.util.HashMap;

/**
 * Абстрактный класс транзакции
 */
public abstract class Transaction {
    protected  float operationSum = 0;
    private static int counterId = 0;
    private int id;

    protected boolean canceled = false;

    public float getOperationSum() {
        return operationSum;
    }

    public Transaction() {
        id = counterId;
        counterId += 1;
    }

    public int getId() {
        return id;
    }
    public abstract  boolean cancelTransaction();
}
