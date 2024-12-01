package kz.kalabay.designpattern.contllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kz.kalabay.designpattern.BankApplication;
import kz.kalabay.designpattern.modules.BankFacade;

public class SignupController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    private BankFacade bankFacade = new BankFacade();

    @FXML
    public void handleSignup() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        boolean success = bankFacade.signUp(username, password);
        if (success) {
            messageLabel.setText("Sign-up successful! You can now log in.");
            BankApplication.changeScene("login-view.fxml", "Login Page");
        } else {
            messageLabel.setText("Sign-up failed. Username may already exist.");
        }
    }

    @FXML
    public void handleBackToLogin() {
        BankApplication.changeScene("login-view.fxml", "Login Page");
    }
}
