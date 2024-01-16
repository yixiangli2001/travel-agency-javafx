package controller.Trip;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Trip;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.InsufficientDestinationsException;

public class BookTripController extends Controller<Trip> {
    @FXML
    private Label welcomeMessage;
    @FXML
    private Button addDestinationBtn;
    @FXML
    private Button removeDestinationBtn;
    @FXML
    private Button addConnectingFlightsBtn;
    @FXML
    private Button viewTripBtn;
    @FXML
    private Button exitBtn;

    @FXML
    private void initialize() {
        welcomeMessage
                .setText("Hi " + model.getAgency().getLoggedInUser().getName() + ", welcome to the Trip section");

    }

    @FXML
    private void handleAddDestination(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/trip_icon.png"));
        ViewLoader.showStage(model.getDestinations(),
                "/view/Destinations/AddDestinationView.fxml", "Add Destination To Trip",
                newStage);
    }

    @FXML
    private void handleRemoveDestination(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/trip_icon.png"));
        ViewLoader.showStage(model.getDestinations(),
                "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination From Trip",
                newStage);
    }

    @FXML
    private void handleAddConnectingFlights(ActionEvent event) throws Exception {
        try {
            model.addConnectingFlights();
            // System.out.println(model.getItinery());
        } catch (DuplicateItemException e) {
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/error_icon.png"));
            ViewLoader.showStage(new ErrorModel(e, "dfsdgfbkjd"),
                    "/view/Error/ErrorView.fxml", "Error",
                    newStage);
        } catch (InsufficientDestinationsException e) {
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/error_icon.png"));
            ViewLoader.showStage(new ErrorModel(e, "dfsdgfbkjd"),
                    "/view/Error/ErrorView.fxml", "Error",
                    newStage);
        }
    }

    @FXML
    private void handleViewTrip(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/trip_icon.png"));
        ViewLoader.showStage(model, "/view/Trip/DisplayTripView.fxml", "Display Trip",
                newStage);

    }

    @FXML
    private void handleExit(ActionEvent event) {
        stage.close();
    }
}
