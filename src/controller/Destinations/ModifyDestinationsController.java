package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Destination;
import model.Destinations;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyDestinationsController extends Controller<Destinations> {

    @FXML
    private GridPane gridPane;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField countryTf;

    TextField[] textFields = { nameTf, countryTf };

    @FXML
    private Button addDestinationBtn;
    @FXML
    private Button removeDestinationBtn;
    @FXML
    private Button exitBtn;

    @FXML
    private void initialize() {
        if (addDestinationBtn != null) {
            addDestinationBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> {
                return nameTf.getText().isEmpty() || countryTf.getText().isEmpty();
            }, nameTf.textProperty(), countryTf.textProperty()));
        }
        if (removeDestinationBtn != null) {
            removeDestinationBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> {
                return nameTf.getText().isEmpty() || countryTf.getText().isEmpty();
            }, nameTf.textProperty(), countryTf.textProperty()));
        }

    }

    @FXML
    private void handleAddDestination(ActionEvent event) throws Exception {
        try {

            model.addDestination(new Destination(nameTf.getText(), countryTf.getText()));
            stage.close();

        } catch (DuplicateItemException e) {
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/error_icon.png"));
            ViewLoader.showStage(new ErrorModel(e, "Destination exists already"),
                    "/view/Error/ErrorView.fxml", "Error",
                    newStage);
        }
    }

    @FXML
    private void handleRemoveDestination(ActionEvent event) throws Exception {
        try {

            model.removeDestination(model.destination(nameTf.getText(),
                    countryTf.getText()));
            stage.close();

        } catch (ItemNotFoundException e) {
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/error_icon.png"));
            ViewLoader.showStage(new ErrorModel(e, "Destination does not exist"),
                    "/view/Error/ErrorView.fxml", "Error",
                    newStage);
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        stage.close();
    }
}
