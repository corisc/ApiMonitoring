package com.etc.apiMonitor.controllers.Admin;

import com.etc.apiMonitor.models.Model;
import com.etc.apiMonitor.views.ClientCellFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;


public class ClientsController implements Initializable {
    public ListView clients_listview;
    public ClientCellFactory cellFactory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getUsersItems().forEach(
                client -> clients_listview.cellFactoryProperty().set(cellFactory));
    }
}
