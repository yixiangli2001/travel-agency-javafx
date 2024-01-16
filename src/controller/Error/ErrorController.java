package controller.Error;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Agency;
import model.Exceptions.ErrorModel;

public class ErrorController extends Controller<ErrorModel> {
    @FXML
    private Label errorMessageTxt;
    @FXML
    private Label hintMessageTxt;
    @FXML
    private Button closeBtn;

    @FXML
    private void initialize() {
        errorMessageTxt.setText(model.getException().getClass().getSimpleName());
        hintMessageTxt.setText(model.getMessage());
    }

    @FXML
    private void handleClose(ActionEvent event) {
        stage.close();
    }
}
