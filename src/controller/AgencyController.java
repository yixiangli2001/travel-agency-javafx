package controller;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Agency;
import model.Trip;
import model.Exceptions.ErrorModel;

public class AgencyController extends Controller<Agency> {
    @FXML
    private Label welcomeMessage;
    @FXML
    private Button exploreFlightsBtn;
    @FXML
    private Button exploreDestinationsBtn;
    @FXML
    private Button bookATripBtn;
    @FXML
    private Button exitBtn;

    @FXML
    private void initialize() {
        welcomeMessage.setText("Hi " + model.getLoggedInUser().getName() + ", welcome to the Prog2 Travel Agency");

    }

    @FXML
    private void handleExploreFlights(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/flights_icon.png"));
        ViewLoader.showStage(model.getFlights(),
                "/view/Flights/ExploreFlightsView.fxml", "Explore Flights",
                newStage);
    }

    @FXML
    private void handleExploreDestinations(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/destinations_icon.png"));
        ViewLoader.showStage(model.getDestinations(),
                "/view/Destinations/ExploreDestinationsView.fxml", "Explore Destinations",
                newStage);
    }

    @FXML
    private void handleBookATrip(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/trip_icon.png"));
        ViewLoader.showStage(new Trip(model),
                "/view/Trip/BookTripView.fxml", "Book a Trip",
                newStage);
    }

    @FXML
    private void handleExit(ActionEvent event) {
        // stage.close();
        Platform.exit();
    }
}
