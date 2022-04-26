package se.hkr.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class RegisterController {
    @FXML
    TextField registerPersonnummer;

    @FXML
    TextField registerName;

    @FXML
    TextField registerEmailField;

    @FXML
    PasswordField registerPasswordField;

    @FXML
    PasswordField registerRepPasswordField;

    Authentication auth = new Authentication();

    // Register Button
    public void onRegisterBtnClick(ActionEvent event) throws IOException {
        Boolean availability = auth.checkAvailability(auth.getUserData("personnummer"), registerPersonnummer.getText());

        if (registerPasswordField.getText().equals(registerRepPasswordField.getText()) && !availability) {
            auth.registerUser(registerPersonnummer, registerName, registerEmailField, registerPasswordField);
            auth.printUsers();
            auth.successRegistration();
            auth.switchToWelcome(event);
        } else if (availability) {
            auth.registerError();
        } else {
            auth.registerPasswordError();
        }
        auth.resetRegField(registerPersonnummer, registerName, registerEmailField, registerPasswordField, registerRepPasswordField);
    }

    // Back Button
    public void onBackBtnClick(ActionEvent event) throws IOException {
        auth.switchToWelcome(event);
    }

}
