package com.etc.apiMonitor.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty emailAddress;
    private final ObjectProperty<LocalDate> dateCreated;

    public Client(String firstName, String lastName, String emailAddress, LocalDate dateCreated) {
        this.firstName = new SimpleStringProperty(this, "FirstName", firstName);
        this.lastName = new SimpleStringProperty(this, "LastName", lastName);
        this.emailAddress = new SimpleStringProperty(this, "EMailAddress", emailAddress);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date", dateCreated);
    }


    public StringProperty firstNameProperty() {
        return this.firstName;
    }

    public StringProperty lastNameProperty() {
        return this.lastName;
    }

    public StringProperty emailAddressProperty() {
        return this.emailAddress;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return this.dateCreated;
    }
}
