package viandasYaModel.Client;

import viandasYaModel.Exceptions.InvalidEmailException;
import viandasYaModel.Exceptions.InvalidPhoneNumberException;

import java.util.regex.Pattern;

public class Client {

    //Parameters

    public String name;
    public String lastname;
    public String address;
    public String state;    //TODO: change to State ENUM ?
    public String email;
    public String phone;

    //Constructor

    public Client(String name, String lastname,
                  String address, String state,
                  String email, String phoneNumber) throws InvalidPhoneNumberException {

        isAValidPhoneNumber(phoneNumber);

        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.state = state;
        this.email = email;
        this.phone = phoneNumber;
    }

    //Methods

        //PhoneNumber

    //TODO: Discuss if we preffer to have this functionality in a PhoneNumberValidor / Manager object
    private void isAValidPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        if (!phoneNumber.matches("^(?:(?:00|\\+)?549?)?0?(?:11|[2368]\\d)(?:(?=\\d{0,2}15)\\d{2})??\\d{8}$"))
            throw new InvalidPhoneNumberException();
    }

    public void updatePhoneNumberTo(String newPhoneNumber) throws InvalidPhoneNumberException {
        isAValidPhoneNumber(newPhoneNumber);

        this.phone = newPhoneNumber;
    }

        // Name

    public void changeNameTo(String newName) {
        this.name = newName;
    }

    public void changeLastnameTo(String newLastname) {
        this.lastname = newLastname;
    }

        //Email

    public void updateEmailTo(String newEmail) throws InvalidEmailException {
        isAValidEmail(newEmail);

        this.email = newEmail;
    }

    private void isAValidEmail(String newEmail) {
        if (!newEmail.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
            throw new InvalidEmailException();
    }

        //Address

    public void updateAddressTo(String address) {
        this.address = address;
    }

        //State

    public void updateStateTo(String state) {
        this.state = state;
    }
}
