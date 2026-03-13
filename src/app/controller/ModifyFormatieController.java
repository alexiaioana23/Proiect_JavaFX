package app.controller;

import app.db.DBOperations;
import app.model.Formatie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ModifyFormatieController {

    @FXML private TableView<Formatie> tableFormatii;
    @FXML private TableColumn<Formatie, Integer> colId;
    @FXML private TableColumn<Formatie, String> colNume;
    @FXML private TableColumn<Formatie, String> colGen;
    @FXML private TableColumn<Formatie, Integer> colAn;
    @FXML private TableColumn<Formatie, String> colTara;

    @FXML private TextField tfNume;
    @FXML private TextField tfGen;
    @FXML private TextField tfAn;
    @FXML private TextField tfTara;

    private final DBOperations db = new DBOperations();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("idformatie"));
        colNume.setCellValueFactory(new PropertyValueFactory<>("nume"));
        colGen.setCellValueFactory(new PropertyValueFactory<>("gen_muzical"));
        colAn.setCellValueFactory(new PropertyValueFactory<>("an_infiintare"));
        colTara.setCellValueFactory(new PropertyValueFactory<>("tara_origine"));

        loadData();

        tableFormatii.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, selected) -> {
                    if (selected != null) {
                        tfNume.setText(selected.getNume());
                        tfGen.setText(selected.getGen_muzical());
                        tfAn.setText(String.valueOf(selected.getAn_infiintare()));
                        tfTara.setText(selected.getTara_origine());
                    }
                });
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
    public void updateFormatie() {

        Formatie selected = tableFormatii.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try {
            db.connect();
            db.updateFormatie(
                    selected.getIdformatie(),
                    tfNume.getText(),
                    tfGen.getText(),
                    Integer.parseInt(tfAn.getText()),
                    tfTara.getText()
            );
            db.disconnect();

            loadData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}