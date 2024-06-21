package entites.transactions;
public abstract class Transaction {
    float operationSum = 0;
    private static int counterId = 0;
    private int id;
    public Transaction() {
        id = counterId;
        counterId += 1;
    }

    public int getId() {
        return id;
    }
    abstract void cancelTransaction();
}
