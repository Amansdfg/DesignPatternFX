public class TransferByNumber implements TransferStrategy{

    @Override
    public boolean transfer(User fromUser, User toUser, double amount) {
        boolean can = fromUser.getFromBalance(amount);
        if (can){
            toUser.addBalance(amount);
            fromUser.addTransaction("Transferred %.2f to: %s %s".formatted(amount, toUser.getName(), toUser.getSurname()));
            toUser.addTransaction("Received %.2f from: %s %s".formatted(amount, fromUser.getName(), fromUser.getSurname()));
            toUser.addNotification("Replenish %.2f from: %s %s".formatted(amount, fromUser.getName(), fromUser.getSurname()));
            return true;
        }
        return false;
    }
}
