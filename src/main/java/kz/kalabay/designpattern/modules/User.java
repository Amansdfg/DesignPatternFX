import java.util.ArrayList;
import java.util.List;

public class User{
    private String mobileNumber;
    private String name;
    private String surname;
    private String password;
    private double balance;
    private String history;
    private String card;
    private List<String> notifications;

    public User(String mobileNumber, String name, String surname, String password) {
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.balance = 0.0;
        this.history = "";
        card = BankCardGenerator.generateCardNumber();
        notifications = new ArrayList<>();
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getName() {
        return name;
    }
    public String getSurname(){
        return surname;
    }

    public double getBalance() {
        return balance;
    }

    public String getHistory() {
        return history;
    }

    protected void addBalance(double amount) {
        this.balance += amount;
    }

    public void addTransaction(String transaction) {
        this.history += transaction + "\n";
    }

    public void addNotification(String notification){
        notifications.add(notification);
    }

    public void seeNotifications(){
        if (!notifications.isEmpty()){
            System.out.println("!----------New Notifications---------!");
            for (String n: notifications){
                System.out.println(n);
            }
            System.out.println();
        }
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
    protected boolean getFromBalance(double amount){
        if (balance < amount){
            return false;
        }
        balance -= amount;
        this.history += "Replenished: -" + amount + "\n";
        return true;
    }

    public void displayInfo(){
        System.out.println(("Mobile Number: %s\nBank Card: %s\nName: %s\nSurname: %s\nBalance: %.2f").formatted
                (mobileNumber,card, name,surname,balance));
    }
}
