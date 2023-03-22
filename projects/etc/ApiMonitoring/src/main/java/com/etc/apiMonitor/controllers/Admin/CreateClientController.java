package com.etc.apiMonitor.controllers.Admin;

import com.etc.apiMonitor.models.Client;
import com.etc.apiMonitor.models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField email_fld;
    public TextField password_fld;
    public Button create_client_btn;
    public Label error_lbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_client_btn.setOnAction(event -> onCreateNewClient());
    }

    private void onCreateNewClient() {
        if(validateInput()) {
            Model.getInstance().createNewClient(fName_fld.getText(), lName_fld.getText(), email_fld.getText(), password_fld.getText(), LocalDate.now());

            //TODO: Create password hashing algorithm
        }


    }

    private boolean validateInput() {
        if (fName_fld.getText().isEmpty()){
            error_lbl.setText("Invalid or missing input in field First Name. User can not be created");
            return false;
        }
        if (lName_fld.getText().isEmpty()){
            error_lbl.setText("Invalid or missing input in field Last Name. User can not be created");
            return false;
        }
        if (email_fld.getText().isEmpty()){
            error_lbl.setText("Invalid or missing input in field E-Mail. User can not be created");
            return false;
        }
        if (password_fld.getText().isEmpty()){
            error_lbl.setText("Invalid or missing input in field Last Name. User can not be created");
            return false;
        }
        return true;
    }
}
