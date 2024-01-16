package controller.Flights;

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
import model.Flight;
import model.Flights;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyFlightsController extends Controller<Flights> {

    @FXML
    private GridPane gridPane;
    @FXML
    private TextField airlineTf;
    @FXML
    private TextField flightNoTf;
    @FXML
    private TextField takeoffTf;
    @FXML
    private TextField landingTf;
    @FXML
    private TextField costTf;

    TextField[] textFields = { airlineTf, flightNoTf, takeoffTf, landingTf,
            costTf };

    @FXML
    private Button addFlightBtn;
    @FXML
    private Button removeFlightBtn;
    @FXML
    private Button exitBtn;

    @FXML
    private void initialize() {
        if (addFlightBtn != null) {
            addFlightBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> {
                return airlineTf.getText().isEmpty() || flightNoTf.getText().isEmpty() || takeoffTf.getText().isEmpty()
                        || landingTf.getText().isEmpty() || costTf.getText().isEmpty();
            }, airlineTf.textProperty(), flightNoTf.textProperty(), takeoffTf.textProperty(), landingTf.textProperty(),
                    costTf.textProperty()));
        }
        if (removeFlightBtn != null) {
            removeFlightBtn.disableProperty().bind(Bindings.createBooleanBinding(() -> {
                return takeoffTf.getText().isEmpty() || landingTf.getText().isEmpty();
            }, takeoffTf.textProperty(), landingTf.textProperty()));
        }

    }

    @FXML
    private void handleAddFlight(ActionEvent event) throws Exception {
        try {

            model.addFlight(new Flight(airlineTf.getText(), Integer.parseInt(flightNoTf.getText()), takeoffTf.getText(),
                    landingTf.getText(), Double.parseDouble(costTf.getText())));
            stage.close();

        } catch (NumberFormatException e) {
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/error_icon.png"));
            ViewLoader.showStage(new ErrorModel(e, "Enter a number"),
                    "/view/Error/ErrorView.fxml", "Error",
                    newStage);
        } catch (DuplicateItemException e) {
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/error_icon.png"));
            ViewLoader.showStage(new ErrorModel(e, "Flight exists already"),
                    "/view/Error/ErrorView.fxml", "Error",
                    newStage);
        }
    }

    @FXML
    private void handleRemoveFlight(ActionEvent event) throws Exception {
        try {

            model.removeFlight(model.getFlight(takeoffTf.getText(),
                    landingTf.getText()));
            stage.close();

        } catch (ItemNotFoundException e) {
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/error_icon.png"));
            ViewLoader.showStage(new ErrorModel(e, "Flight does not exist"),
                    "/view/Error/ErrorView.fxml", "Error",
                    newStage);
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        stage.close();
    }
}
