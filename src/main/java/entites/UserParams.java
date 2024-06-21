package entites;
public class UserParams {
    private String name;
    private String lastName;
    private String address = "";
    private String numberPassport = "";
    public UserParams(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setNumberPassport(String numberPassport) {
        this.numberPassport = numberPassport;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getNumberPassport() {
        return numberPassport;
    }
}