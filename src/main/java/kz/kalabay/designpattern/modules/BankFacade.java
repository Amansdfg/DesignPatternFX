import java.util.Scanner;

public class BankFacade {
    private Database database;
    private ReplenishCommand replenish;
    private UserBuilder userBuilder;
    private TransferContext context;
    private final Scanner in = new Scanner(System.in);

    public BankFacade() {
        database = Database.getInstance();
        replenish = new ReplenishCommand();
        userBuilder = new UserBuilder();
        context = new TransferContext();
    }

    public User signUp() {
        System.out.println("Write your mobile number:");
        String mobileNumber = in.nextLine();
        if (database.get(mobileNumber) != null) {
            System.out.println("User with this number is already registered");
            return null;
        }
        System.out.println("Write your name:");
        String name = in.nextLine();
        System.out.println("Write your surname:");
        String surname = in.nextLine();
        System.out.println("Write your account password:");
        String password = in.nextLine();

        User user = userBuilder.build(name,surname,mobileNumber,password);
        database.save(mobileNumber, user);
        System.out.println("Successfully Signed Up");
        return user;
    }

    public User signIn(String mobileNumber, String password) {
        User user = database.get(mobileNumber);
        if (user == null || !user.checkPassword(password)) {
            System.out.println("Invalid mobile number or password!");
            return null;
        }
        return user;
    }

    public void replenish(User user, double amount) {
        replenish.execute(user,amount);
    }

    public void doTransaction(User fromUser, String mobileNumber, double amount){
        User toUser = database.get(mobileNumber);
        if (toUser != null) {
            context.setStrategy(new TransferByNumber());
            context.executeTransfer(fromUser, toUser, amount);
        }
    }

    public boolean checkIfUserExist(String mobileNumber){
        return database.get(mobileNumber) != null;
    }
}
