package app.model.User.Client;

import app.model.Exceptions.InvalidPhoneNumberException;
import app.model.User.User;

public class Client extends User {

    //Parameters

    public String lastname;

    //Constructor

    public Client(String name, String lastname,
                  String address, String state,
                  String email, String phoneNumber) throws InvalidPhoneNumberException {
        super(name,state,address,email,phoneNumber);

        this.lastname = lastname;
    }

    //Methods

    public void changeLastnameTo(String newLastname) {
        this.lastname = newLastname;
    }


}
