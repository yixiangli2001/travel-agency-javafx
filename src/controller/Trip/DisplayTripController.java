package controller.Trip;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Destination;
import model.Destinations;
import model.Flight;
import model.Flights;
import model.Itinery;
import model.Trip;
import model.Exceptions.ErrorModel;
import model.Exceptions.InputMismatchException;

public class DisplayTripController extends Controller<Trip> {

    @FXML
    private ListView<Itinery> listView;
    @FXML
    private Button viewIndividualBtn;
    @FXML
    private Button exitBtn;

    @FXML
    private void initialize() {
        listView.setItems(model.getItinery());
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    private ObservableList<Itinery> getSelectedObjects() {
        return listView.getSelectionModel().getSelectedItems();

    }

    @FXML
    private void handleViewIndividual(ActionEvent event) throws Exception {
        ObservableList<Itinery> objects = getSelectedObjects();
        boolean isDestination = false;
        boolean isFlight = false;

        for (Itinery object : objects) {
            if (object instanceof Destination) {
                isDestination = true;
            }
            if (object instanceof Flight) {
                isFlight = true;
            }
        }
        if (isDestination == true && isDestination != isFlight) {
            // ObservableList<Destination> destinations = getSelectedObjects();
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/destinations_icon.png"));
            ViewLoader.showStage(new Destinations(objects),
                    "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations",
                    newStage);
        } else if (isFlight == true && isDestination != isFlight) {
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/flights_icon.png"));
            ViewLoader.showStage(new Flights(objects),
                    "/view/Flights/DisplayFlightsView.fxml", "Display Flights",
                    newStage);
        } else {
            Stage newStage = new Stage();
            newStage.getIcons().add(new Image("image/error_icon.png"));
            ViewLoader.showStage(
                    new ErrorModel(new InputMismatchException(),
                            "Brah you can't display destination and flight in the same window"),
                    "/view/Error/ErrorView.fxml", "Error",
                    newStage);
        }

    }

    @FXML
    private void handleExit(ActionEvent event) {
        // stage.close();
        Platform.exit();
    }
}
