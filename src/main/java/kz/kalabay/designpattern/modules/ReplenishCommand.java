public class ReplenishCommand implements Command {

    @Override
    public void execute(User user, double amount) {
        user.addBalance(amount);
        user.addTransaction("Replenished %.2f".formatted(amount));
    }
}
