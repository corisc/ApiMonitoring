package com.etc.apiMonitor.controllers.Admin;

import com.etc.apiMonitor.models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label fName_lbl;
    public Label lName_lbl;
    public Label email_lbl;
    public Label date_lbl;
    public Button delete_btn;
    private final Client client;

    public ClientCellController(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fName_lbl.setText(this.client.firstNameProperty().getValue());
        lName_lbl.setText(this.client.lastNameProperty().getValue());
        email_lbl.setText(this.client.emailAddressProperty().getValue());
        date_lbl.setText(this.client.dateProperty().getValue().toString());
        delete_btn.setOnAction(actionEvent -> onDelete());
    }

    private void onDelete() {

    }
}
