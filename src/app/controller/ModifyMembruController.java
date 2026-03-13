package app.controller;

import app.db.DBOperations;
import app.model.Membru;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModifyMembruController {

    @FXML private TableView<Membru> tableMembri;

    @FXML private TableColumn<Membru, Integer> colId;
    @FXML private TableColumn<Membru, String> colNume;
    @FXML private TableColumn<Membru, String> colPrenume;
    @FXML private TableColumn<Membru, Integer> colVarsta;
    @FXML private TableColumn<Membru, String> colInstrument;
    @FXML private TableColumn<Membru, String> colNationalitate;

    @FXML private TextField tfNume;
    @FXML private TextField tfPrenume;
    @FXML private TextField tfVarsta;
    @FXML private TextField tfInstrument;
    @FXML private TextField tfNationalitate;

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

        tableMembri.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, selected) -> {
                    if (selected != null) {
                        tfNume.setText(selected.getNume());
                        tfPrenume.setText(selected.getPrenume());
                        tfVarsta.setText(String.valueOf(selected.getVarsta()));
                        tfInstrument.setText(selected.getInstrument());
                        tfNationalitate.setText(selected.getNationalitate());
                    }
                });
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

    @FXML
    public void updateMembru() {

        Membru selected = tableMembri.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try {
            db.connect();
            db.updateMembru(
                    selected.getIdmembru(),
                    tfNume.getText(),
                    tfPrenume.getText(),
                    Integer.parseInt(tfVarsta.getText()),
                    tfInstrument.getText(),
                    tfNationalitate.getText()
            );
            db.disconnect();

            loadData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}