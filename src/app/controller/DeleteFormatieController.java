package app.controller;

import app.db.DBOperations;
import app.model.Formatie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeleteFormatieController {

    @FXML private TableView<Formatie> tableFormatii;

    @FXML private TableColumn<Formatie, Integer> colId;
    @FXML private TableColumn<Formatie, String> colNume;
    @FXML private TableColumn<Formatie, String> colGen;
    @FXML private TableColumn<Formatie, Integer> colAn;
    @FXML private TableColumn<Formatie, String> colTara;

    private final DBOperations db = new DBOperations();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("idformatie"));
        colNume.setCellValueFactory(new PropertyValueFactory<>("nume"));
        colGen.setCellValueFactory(new PropertyValueFactory<>("gen_muzical"));
        colAn.setCellValueFactory(new PropertyValueFactory<>("an_infiintare"));
        colTara.setCellValueFactory(new PropertyValueFactory<>("tara_origine"));

        loadData();
    }

    private void loadData() {
        try {
            db.connect();
            ObservableList<Formatie> list =
                    FXCollections.observableArrayList(db.getFormatii());
            tableFormatii.setItems(list);
            db.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteFormatie() {
        Formatie selected = tableFormatii.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Selectează o formație!");
            return;
        }

        try {
            db.connect();
            db.deleteFormatie(selected.getIdformatie());
            db.disconnect();

            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.showAndWait();
    }
}