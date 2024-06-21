package entites;



public class User {
    private static int counterId = 0;
    private boolean access = false;
    private int id;
    private String name;
    private String lastName;
    private String address;
    private String numberPassport;
    private UserParams params;

    private void updateAccess() {
        access = !(address.equals("") || numberPassport.equals(""));
    }

    public void User(UserParams params) {
        this.name = params.getName();
        this.lastName = params.getLastName();
        this.address = params.getAddress();
        this.numberPassport = params.getNumberPassport();
        this.params = params;
        id = counterId;
        counterId += 1;
        updateAccess();
    }

    public void setAddress(String address) {
        this.address = address;
        updateAccess();
    }

    public void setNumberPassport(String numberPassport) {
        this.numberPassport = numberPassport;
        updateAccess();
    }
}
