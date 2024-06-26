package entites;



public class User {
    private static int counterId = 0;
    private int id;
    private boolean access = false;
    private String name;
    private String lastName;
    private String address;
    private String numberPassport;

    private void updateAccess() {
        access = !(address.equals("") || numberPassport.equals(""));
    }

    public static class Builder {
        public static  User user;

        public void Builder(String name, String lastName)  {
            user = new User();
            user.id = counterId;
            counterId += 1;
            user.name = name;
            user.lastName = lastName;
        }

        public Builder addres(String address) {
            user.address = address;
            return this;
        }

        public Builder numberPassport(String numberPassport) {
            user.numberPassport = numberPassport;
            return this; 
        }

        public User build() {
            return user;
        }

    }
}
