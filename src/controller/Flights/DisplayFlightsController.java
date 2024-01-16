package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Flight;
import model.Flights;

public class DisplayFlightsController extends Controller<Flights> {
    @FXML
    private TextField filterTf;
    @FXML
    private TableView<Flight> tableView;
    @FXML
    private TableColumn<Flight, String> airlineCol;
    @FXML
    private TableColumn<Flight, Integer> flightNoCol;
    @FXML
    private TableColumn<Flight, String> takeoffCol;
    @FXML
    private TableColumn<Flight, String> landingCol;
    @FXML
    private TableColumn<Flight, Double> costCol;
    @FXML
    private Button exitBtn;

    @FXML
    private void initialize() {
        airlineCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("airline"));
        flightNoCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("flightNo"));
        takeoffCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("takeoff"));
        landingCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("landing"));
        costCol.setCellValueFactory(new PropertyValueFactory<Flight, Double>("cost"));
        tableView.setItems(model.getFlights());

        if (filterTf != null) {
            filterTf.textProperty().addListener(event -> {
                tableView.setItems(model.getFilteredFlights(filterTf.textProperty().get()));
            });
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        stage.close();
    }
}
