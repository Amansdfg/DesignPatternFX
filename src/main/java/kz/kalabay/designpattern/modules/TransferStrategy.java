public interface TransferStrategy {
    boolean transfer(User fromUser, User toUser, double amount);
}
