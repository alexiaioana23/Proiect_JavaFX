package app.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ApplicationController {

    @FXML
    public void openAddFormatie() {
        openWindow("/fxml/AddFormatie.fxml", "Adaugă Formație");
    }

    @FXML
    public void openAddMembru() {
        openWindow("/fxml/AddMembru.fxml", "Adaugă Membru");
    }

    @FXML
    public void openAddContract() {
        openWindow("/fxml/AddContract.fxml", "Adaugă Contract");
    }
    
 // ===== VIZUALIZARE =====
    @FXML
    public void openViewFormatii() {
        openWindow("/fxml/ViewFormatii.fxml", "Vizualizare Formații");
    }

    @FXML
    public void openViewMembri() {
        openWindow("/fxml/ViewMembri.fxml", "Vizualizare Membri");
    }

    @FXML
    public void openViewContracte() {
        openWindow("/fxml/ViewContracte.fxml", "Vizualizare Contracte");
    }

    // ===== MODIFICARE =====
    @FXML
    public void openModifyFormatie() {
        openWindow("/fxml/ModifyFormatie.fxml", "Modifică Formație");
    }

    @FXML
    public void openModifyMembru() {
        openWindow("/fxml/ModifyMembru.fxml", "Modifică Membru");
    }

    @FXML
    public void openModifyContract() {
        openWindow("/fxml/ModifyContract.fxml", "Modifică Contract");
    }

    // ===== ȘTERGERE =====
    @FXML
    public void openDeleteFormatie() {
        openWindow("/fxml/DeleteFormatie.fxml", "Șterge Formație");
    }

    @FXML
    public void openDeleteMembru() {
        openWindow("/fxml/DeleteMembru.fxml", "Șterge Membru");
    }

    @FXML
    public void openDeleteContract() {
        openWindow("/fxml/DeleteContract.fxml", "Șterge Contract");
    }

    private void openWindow(String path, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));

            Scene scene = new Scene(loader.load(), 500, 450); // dimensiune

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene); // folosim scena creată
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}