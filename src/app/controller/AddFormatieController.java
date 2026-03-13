package app.controller;

import app.db.DBOperations;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddFormatieController {

    @FXML private TextField tfNume;
    @FXML private TextField tfGen;
    @FXML private TextField tfAn;
    @FXML private TextField tfTara;

    private final DBOperations db = new DBOperations();

    @FXML
    public void save() {
        try {
            String nume = tfNume.getText().trim();
            String gen = tfGen.getText().trim();
            int an = Integer.parseInt(tfAn.getText().trim());
            String tara = tfTara.getText().trim();

            db.connect();
            db.addFormatie(nume, gen, an, tara);
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