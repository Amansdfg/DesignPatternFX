public class TransferContext {
    private TransferStrategy strategy;

    public void setStrategy(TransferStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean executeTransfer(User sender, User receiver, double amount) {
        return strategy.transfer(sender, receiver, amount);
    }
}
