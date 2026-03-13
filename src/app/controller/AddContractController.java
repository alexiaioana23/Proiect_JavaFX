package app.controller;

import app.db.DBOperations;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;

public class AddContractController {

    @FXML private TextField tfIdFormatie;
    @FXML private TextField tfIdMembru;
    @FXML private DatePicker dpDataStart;
    @FXML private DatePicker dpDataEnd;
    @FXML private TextField tfStatus;

    private final DBOperations db = new DBOperations();

    @FXML
    public void save() {
        try {
            int idFormatie = Integer.parseInt(tfIdFormatie.getText().trim());
            int idMembru = Integer.parseInt(tfIdMembru.getText().trim());

            Date dataStart = Date.valueOf(dpDataStart.getValue());
            Date dataEnd = Date.valueOf(dpDataEnd.getValue());

            String status = tfStatus.getText().trim();

            db.connect();
            db.addContract(idFormatie, idMembru, dataStart, dataEnd, status); // ← AICI ERA GREȘEALA
            db.disconnect();

            close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancel() {
        close();
    }

    private void close() {
        Stage stage = (Stage) tfIdFormatie.getScene().getWindow();
        stage.close();
    }
}