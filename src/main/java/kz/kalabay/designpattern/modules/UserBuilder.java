public class UserBuilder implements AccountFactory {

    @Override
    public User build(String name, String surname, String mobileNumber, String password) {
        return new User(mobileNumber,name,surname,password);
    }
}
