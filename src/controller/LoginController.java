package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Administrator;
import model.Administrators;
import model.Agency;
import model.Exceptions.ErrorModel;
import model.Exceptions.InvalidCredentialsException;

public class LoginController extends Controller<Agency> {

    @FXML
    private TextField usernameTf;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button exitBtn;

    public final Agency getAgency() {
        return model;
    }

    private String getUsername() {
        return usernameTf.getText();
    }

    private String getPassword() {
        return passwordField.getText();
    }

    @FXML
    private void initialize() {
        loginBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> {
            return usernameTf.getText().isEmpty() || passwordField.getText().isEmpty();
        }, usernameTf.textProperty(), passwordField.textProperty()));
    }

    @FXML
    private void handleLogin(ActionEvent event) throws Exception {
        Administrators admins = getAgency().getAdministrators();
        try {
            if (admins.hasAdministrator(getUsername(), getPassword())) {
                model.setLoggedInUser(admins.getAdministrator(getUsername(), getPassword()));
                stage.getIcons().set(0, (new Image("image/agency_icon.png")));
                stage.close();
                ViewLoader.showStage(model, "/view/AgencyView.fxml", "Prog2 Travel Agency", new Stage());
            }
        } catch (InvalidCredentialsException e) {
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/error_icon.png"));
            ViewLoader.showStage(new ErrorModel(e, "brah that aint right xdddd"),
                    "/view/Error/ErrorView.fxml", "Error",
                    newStage);
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        stage.close();
    }

}
