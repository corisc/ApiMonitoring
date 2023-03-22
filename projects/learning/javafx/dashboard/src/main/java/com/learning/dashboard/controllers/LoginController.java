package com.learning.dashboard.controllers;

import com.learning.dashboard.models.Model;
import com.learning.dashboard.views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_fld;
    public Label password_lbl;
    public TextField password_fld;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue()));
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();

        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){
            //Evaluate Client Login Credentials
            Model.getInstance().evaluateClientCred(payee_address_fld.getText(), password_fld.getText());
            if (Model.getInstance().isClientLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showClientWindow();
                //Close login stage
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                payee_address_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No such login credentials");
            }
        } else {
            Model.getInstance().evaluateAdminCred(payee_address_fld.getText(), password_fld.getText());
            if (Model.getInstance().isAdminLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }
        }


    }
}
