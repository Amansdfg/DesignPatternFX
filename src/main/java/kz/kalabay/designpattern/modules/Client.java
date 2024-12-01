import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        BankFacade bank = new BankFacade();
        User user;
        boolean run;
        boolean exit = true;

        System.out.println("Welcome to the MAD Bank");
        while (exit) {

            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            String choice = in.nextLine();

            if (choice.equals("1")) {

                bank.signUp();
            } else if (choice.equals("2")) {

                System.out.println("Enter account number to log in:");
                String mobileNumber = in.nextLine();
                System.out.println("Enter account password:");
                String password = in.nextLine();
                user = bank.signIn(mobileNumber, password);

                if (user != null) {
                    run = true;
                    System.out.println("Logged in successfully!");
                    user.seeNotifications();
                    while (run) {
                        System.out.println("1. View Account Info");
                        System.out.println("2. Replenish");
                        System.out.println("3. Transfer");
                        System.out.println("4. Transfer History");
                        System.out.println("5. Exit");
                        String option = in.nextLine();

                        switch (option) {
                            case "1":
                                user.displayInfo();
                                break;
                            case "2":
                                System.out.println("Enter amount to replenish:");
                                double depositAmount = Double.parseDouble(in.nextLine());
                                bank.replenish(user, depositAmount);
                                break;
                            case "3":
                                System.out.println("Enter the mobile number of user:");
                                String mobileNumberOfUser = in.nextLine();
                                if (bank.checkIfUserExist(mobileNumberOfUser)){
                                    System.out.println("Write amount that you want to transfer:");
                                    double amount = Double.parseDouble(in.nextLine());
                                    bank.doTransaction(user, mobileNumberOfUser, amount);
                                }else System.out.println("Invalid mobile number!");
                                break;
                            case "4":
                                System.out.println(user.getHistory());
                                break;
                            case "5":
                                run = false;
                                break;
                            default:
                                System.out.println("Invalid option!");
                        }
                    }
                } else {
                    System.out.println("Account not found!");
                }
            } else if (choice.equals("3")) {
                exit = false;
                System.out.println("We appreciate your choice!");
            }
        }
    }
}