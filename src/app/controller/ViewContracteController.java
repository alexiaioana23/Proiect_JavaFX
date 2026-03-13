package app.controller;

import app.db.DBOperations;
import app.model.Contract;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewContracteController {

    @FXML private TableView<Contract> tableContracte;

    @FXML private TableColumn<Contract, Integer> colId;
    @FXML private TableColumn<Contract, Integer> colIdFormatie;
    @FXML private TableColumn<Contract, Integer> colIdMembru;
    @FXML private TableColumn<Contract, String> colDataStart;
    @FXML private TableColumn<Contract, String> colDataEnd;
    @FXML private TableColumn<Contract, String> colStatus;

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
}