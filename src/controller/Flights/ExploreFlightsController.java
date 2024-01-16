package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Flights;

public class ExploreFlightsController extends Controller<Flights> {
    @FXML
    private Label welcomeMessage;
    @FXML
    private Button viewAllFlightsBtn;
    @FXML
    private Button viewAllFlightsByCountryBtn;
    @FXML
    private Button addFlightBtn;
    @FXML
    private Button removeFlightBtn;
    @FXML
    private Button exitBtn;

    @FXML
    private void initialize() {
        welcomeMessage
                .setText("Hi " + model.getAgency().getLoggedInUser().getName() + ", welcome to the Flights section");

    }

    @FXML
    private void handleViewAllFlights(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/flights_icon.png"));
        ViewLoader.showStage(model,
                "/view/Flights/DisplayFlightsView.fxml", "Display Flights",
                newStage);
    }

    @FXML
    private void handleViewAllFlightsByCountry(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/flights_icon.png"));
        ViewLoader.showStage(model,
                "/view/Flights/DisplayFilteredFlightsView.fxml", "Display Flights Filtered",
                newStage);
    }

    @FXML
    private void handleAddFlight(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/flights_icon.png"));
        ViewLoader.showStage(model,
                "/view/Flights/AddFlightView.fxml", "Add Flight",
                newStage);
    }

    @FXML
    private void handleRemoveFlight(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/flights_icon.png"));
        ViewLoader.showStage(model,
                "/view/Flights/RemoveFlightView.fxml", "Remove Flight",
                newStage);

    }

    @FXML
    private void handleExit(ActionEvent event) {
        stage.close();
    }
}
