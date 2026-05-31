package eu.ase.jfxmltest;

import eu.ase.iojson.User;
import eu.ase.sqldao.SqlDAO;
import eu.ase.sqldao.UsersSubscriberReactStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Window;

import java.util.concurrent.SubmissionPublisher;

public class RegistrationFormController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    @FXML
    private Button submitThButton;
    @FXML
    private Button submitReactStreamsButton;
    @FXML
    private Button displayButton;

    private static SqlDAO sqlDAO;
    private static int objectRegisteredUserCount = 0;

    public RegistrationFormController() {
        sqlDAO = SqlDAO.getInstance();
    }

    private boolean doValidationGUI(Window owner) {
        if(nameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your name");
            return false;
        }
        if(emailField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your email");
            return false;
        }
        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your password");
            return false;
        }
        return true;
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Window owner = submitButton.getScene().getWindow();
        if(!doValidationGUI(owner)) {
            return;
        }
        objectRegisteredUserCount++;
        System.out.println("Registered User: " + nameField.getText());
        sqlDAO.insertIntoDB(objectRegisteredUserCount, nameField.getText(), emailField.getText(), passwordField.getText());
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration successful - standard!", "Welcome " + nameField.getText() + "!");
    }

    @FXML
    protected void handleSubmitMThButtonAction(ActionEvent event) {
        Window window = submitButton.getScene().getWindow();
        doValidationGUI(window);
        objectRegisteredUserCount++;
        System.out.println("Registered User: " + nameField.getText());
        Runnable rth = () -> {
            sqlDAO.insertIntoDB(objectRegisteredUserCount, nameField.getText(), emailField.getText(), passwordField.getText());
        };
        Thread th = new  Thread(rth);
        th.start();
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, window, "Registration successful - multithreading!", "Welcome " + nameField.getText() + "!");
    }

    @FXML
    protected void handleSubmitReactStreamsButtonAction(ActionEvent event) {
        Window window = submitButton.getScene().getWindow();
        doValidationGUI(window);
        objectRegisteredUserCount++;
        System.out.println("Registered User: " + nameField.getText());

        try(SubmissionPublisher<User> userSubmissionPublisher = new SubmissionPublisher<>()) {
            User user = new User(objectRegisteredUserCount, nameField.getText(), emailField.getText(), passwordField.getText());
            UsersSubscriberReactStream usersSubscriberReactStream = new UsersSubscriberReactStream();
            userSubmissionPublisher.subscribe(usersSubscriberReactStream);
            userSubmissionPublisher.submit(user);
        }
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, window, "Registration successful - react streams!", "Welcome " + nameField.getText() + "!");
    }

    @FXML
    protected void handleDisplayButtonAction(ActionEvent event) {
        sqlDAO.displayDB();
    }

}
