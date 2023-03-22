package com.etc.apiMonitor.models;

import com.etc.apiMonitor.views.AccountType;
import com.etc.apiMonitor.views.ViewFactory;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Model {
    private final ViewFactory viewFactory;
    private static Model model;
    private static Client client;
    private final DatabaseDriver databaseDriver;
    private final AccountType loginAccountType = AccountType.CLIENT;
    private boolean adminLoginSuccessFlag = false;
    private boolean clientLoginSuccessFlag = false;

    private Model() {

        this.databaseDriver = new DatabaseDriver();
        this.viewFactory = new ViewFactory();
        //Client Data Section
        this.clientLoginSuccessFlag = false;
        //Admin Data Section

    }

    public static synchronized Model getInstance() {
        if (model == null)  {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }



    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    /* Client Method Section */

    public boolean isClientLoginSuccessFlag() {
        return clientLoginSuccessFlag;
    }

    public void setClientLoginSuccessFlag(boolean clientLoginSuccessFlag) {
        this.clientLoginSuccessFlag = clientLoginSuccessFlag;
    }

    public void evaluateClientCred(String eMailAddress, String password){
        ResultSet resultSet = databaseDriver.getUserData(eMailAddress, password);
        try {
            if (resultSet.isBeforeFirst()) {
                client = new Client(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email_address"),
                        LocalDate.parse(resultSet.getString("date_created")
                ));

                this.clientLoginSuccessFlag =true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void evaluateAdminCred(String username, String password){

        if (username.equals("admin_user")  || password.equals("adminpassword") ) {
            this.adminLoginSuccessFlag = true;
        }

    }

    public boolean isAdminLoginSuccessFlag() {
        return adminLoginSuccessFlag;
    }

    public void setAdminLoginSuccessFlag(boolean adminLoginSuccessFlag) {
        this.adminLoginSuccessFlag = adminLoginSuccessFlag;
    }

    public void createNewClient(String firstName, String lastName, String email, String password, LocalDate date) {
        if (databaseDriver.insertNewUser(firstName, lastName, email, password, date)) {
            System.out.println("insert of user into database successful");
        } else {
            System.out.println("Error creating user");
        }
    }

    public ObservableList<Client> getUsersItems() {
        return databaseDriver.getAllUsersData();

    }
}
