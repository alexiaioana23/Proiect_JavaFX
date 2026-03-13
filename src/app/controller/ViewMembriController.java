package app.controller;

import app.db.DBOperations;
import app.model.Membru;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewMembriController {

    @FXML private TableView<Membru> tableMembri;

    @FXML private TableColumn<Membru, Integer> colId;
    @FXML private TableColumn<Membru, String> colNume;
    @FXML private TableColumn<Membru, String> colPrenume;
    @FXML private TableColumn<Membru, Integer> colVarsta;
    @FXML private TableColumn<Membru, String> colInstrument;
    @FXML private TableColumn<Membru, String> colNationalitate;

    private final DBOperations db = new DBOperations();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("idmembru"));
        colNume.setCellValueFactory(new PropertyValueFactory<>("nume"));
        colPrenume.setCellValueFactory(new PropertyValueFactory<>("prenume"));
        colVarsta.setCellValueFactory(new PropertyValueFactory<>("varsta"));
        colInstrument.setCellValueFactory(new PropertyValueFactory<>("instrument"));
        colNationalitate.setCellValueFactory(new PropertyValueFactory<>("nationalitate"));

        loadData();
    }

    private void loadData() {
        try {
            db.connect();
            ObservableList<Membru> list =
                    FXCollections.observableArrayList(db.getMembri());
            tableMembri.setItems(list);
            db.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}