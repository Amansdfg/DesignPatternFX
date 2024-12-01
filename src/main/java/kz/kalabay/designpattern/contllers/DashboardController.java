package kz.kalabay.designpattern.contllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import kz.kalabay.designpattern.modules.BankFacade;
import kz.kalabay.designpattern.modules.User;

public class DashboardController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private TextArea historyArea;

    @FXML
    private TextField amountField;

    @FXML
    private TextField recipientField;

    private static User loggedInUser;
    private static BankFacade bankFacade;

    public static void setLoggedInUser(User user, BankFacade bankFacadeInstance) {
        loggedInUser = user;
        bankFacade = bankFacadeInstance;
    }

    @FXML
    public void initialize() {
        if (loggedInUser != null) {
            welcomeLabel.setText("Welcome, " + loggedInUser.getName());
            historyArea.setText(loggedInUser.getHistory());
        }
    }

    @FXML
    public void handleViewAccountInfo() {
        historyArea.setText(loggedInUser.displayInfo());
    }

    @FXML
    public void handleReplenish() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            bankFacade.replenish(loggedInUser, amount);
            historyArea.setText("Account replenished successfully. \n" + loggedInUser.getHistory());
        } catch (NumberFormatException e) {
            historyArea.setText("Invalid amount. Please try again.");
        }
    }

    @FXML
    public void handleTransfer() {
        String recipient = recipientField.getText();
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (bankFacade.checkIfUserExist(recipient)) {
                bankFacade.doTransaction(loggedInUser, recipient, amount);
                historyArea.setText("Transfer successful. \n" + loggedInUser.getHistory());
            } else {
                historyArea.setText("Recipient does not exist. Please check the mobile number.");
            }
        } catch (NumberFormatException e) {
            historyArea.setText("Invalid amount. Please try again.");
        }
    }

    @FXML
    public void handleLogout() {
        loggedInUser = null;
        BankApplication.changeScene("login-view.fxml", "Login Page");
    }
}
