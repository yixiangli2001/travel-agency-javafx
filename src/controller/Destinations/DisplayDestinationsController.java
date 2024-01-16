package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Destination;
import model.Destinations;

public class DisplayDestinationsController extends Controller<Destinations> {
    @FXML
    private TextField filterTf;
    @FXML
    private TableView<Destination> tableView;
    @FXML
    private TableColumn<Destination, String> nameCol;
    @FXML
    private TableColumn<Destination, String> countryCol;
    @FXML
    private Button exitBtn;

    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<Destination, String>("name"));
        countryCol.setCellValueFactory(new PropertyValueFactory<Destination, String>("country"));
        tableView.setItems(model.getDestinations());

        if (filterTf != null) {
            filterTf.textProperty().addListener(event -> {
                tableView.setItems(model.getFilteredDestinations(filterTf.textProperty().get()));
            });
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        stage.close();
    }
}
