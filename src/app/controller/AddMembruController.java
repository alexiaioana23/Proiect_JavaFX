package app.controller;

import app.db.DBOperations;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddMembruController {

    @FXML private TextField tfNume;
    @FXML private TextField tfPrenume;
    @FXML private TextField tfVarsta;
    @FXML private TextField tfInstrument;
    @FXML private TextField tfNationalitate;

    private final DBOperations db = new DBOperations();

    @FXML
    public void save() {
        try {
            String nume = tfNume.getText().trim();
            String prenume = tfPrenume.getText().trim();
            int varsta = Integer.parseInt(tfVarsta.getText().trim());
            String instrument = tfInstrument.getText().trim();
            String nationalitate = tfNationalitate.getText().trim();

            db.connect();
            db.addMembru(nume, prenume, varsta, instrument, nationalitate);
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
        Stage stage = (Stage) tfNume.getScene().getWindow();
        stage.close();
    }
}