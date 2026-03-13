package app.controller;

import app.db.DBOperations;
import app.model.Contract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;

public class ModifyContractController {

    @FXML private TableView<Contract> tableContracte;

    @FXML private TableColumn<Contract, Integer> colId;
    @FXML private TableColumn<Contract, Integer> colIdFormatie;
    @FXML private TableColumn<Contract, Integer> colIdMembru;
    @FXML private TableColumn<Contract, Date> colDataStart;
    @FXML private TableColumn<Contract, Date> colDataEnd;
    @FXML private TableColumn<Contract, String> colStatus;

    @FXML private TextField tfIdFormatie;
    @FXML private TextField tfIdMembru;
    @FXML private DatePicker dpDataStart;
    @FXML private DatePicker dpDataEnd;
    @FXML private TextField tfStatus;

    private final DBOperations db = new DBOperations();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("idcontract"));
        colIdFormatie.setCellValueFactory(new PropertyValueFactory<>("idformatie"));
        colIdMembru.setCellValueFactory(new PropertyValueFactory<>("idmembru"));
        colDataStart.setCellValueFactory(new PropertyValueFactory<>("data_inceput"));
        colDataEnd.setCellValueFactory(new PropertyValueFactory<>("data_sfarsit"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status_contract"));

        loadData();

        tableContracte.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldVal, selected) -> {
                    if (selected != null) {
                        tfIdFormatie.setText(String.valueOf(selected.getIdformatie()));
                        tfIdMembru.setText(String.valueOf(selected.getIdmembru()));
                        dpDataStart.setValue(selected.getData_inceput().toLocalDate());
                        dpDataEnd.setValue(selected.getData_sfarsit().toLocalDate());
                        tfStatus.setText(selected.getStatus_contract());
                    }
                });
    }

    private void loadData() {
        try {
            db.connect();
            ObservableList<Contract> list =
                    FXCollections.observableArrayList(db.getContracts());
            tableContracte.setItems(list);
            db.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateContract() {

        Contract selected = tableContracte.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        try {
            db.connect();
            db.updateContract(
                    selected.getIdcontract(),
                    Integer.parseInt(tfIdFormatie.getText()),
                    Integer.parseInt(tfIdMembru.getText()),
                    Date.valueOf(dpDataStart.getValue()),
                    Date.valueOf(dpDataEnd.getValue()),
                    tfStatus.getText()
            );
            db.disconnect();

            loadData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}