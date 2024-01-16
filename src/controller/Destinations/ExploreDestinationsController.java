package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Destinations;

public class ExploreDestinationsController extends Controller<Destinations> {
    @FXML
    private Label welcomeMessage;
    @FXML
    private Button viewAllDestinationsBtn;
    @FXML
    private Button viewFilteredDestinationsBtn;
    @FXML
    private Button addDestinationBtn;
    @FXML
    private Button removeDestinationBtn;
    @FXML
    private Button exitBtn;

    @FXML
    private void initialize() {
        welcomeMessage
                .setText("Hi " + model.getAgency().getLoggedInUser().getName()
                        + ", welcome to the Destinations section");

    }

    @FXML
    private void handleViewAllDestinations(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/destinations_icon.png"));
        ViewLoader.showStage(model,
                "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations",
                newStage);
    }

    @FXML
    private void handleViewAllDestinationsByCountry(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/destinations_icon.png"));
        ViewLoader.showStage(model,
                "/view/Destinations/DisplayFilteredDestinationsView.fxml", "Display Destinations Filtered",
                newStage);
    }

    @FXML
    private void handleAddDestination(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/destinations_icon.png"));
        ViewLoader.showStage(model,
                "/view/Destinations/AddDestinationView.fxml", "Add Destination",
                newStage);
    }

    @FXML
    private void handleRemoveDestination(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.getIcons().add(new Image("image/destinations_icon.png"));
        ViewLoader.showStage(model,
                "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination",
                newStage);

    }

    @FXML
    private void handleExit(ActionEvent event) {
        stage.close();
    }

}
