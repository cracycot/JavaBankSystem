package entites;
abstract class Transaction {
    private static int counterId = 0;
    public int id;
    public Transaction() {
        id = counterId;
        counterId += 1;
    }
}
