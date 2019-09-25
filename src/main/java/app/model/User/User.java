package app.model.User;

import app.model.Exceptions.InvalidEmailException;
import app.model.Exceptions.InvalidPhoneNumberException;

public class User {

    //Parameters

    public String name;
    public String state;
    public String address;
    public String email;
    public String phone;
    public int accountCredit;

    //Constructor

    public User(String name, String state, String address, String email, String phone) {

        isAValidPhoneNumber(phone);
        isAValidEmail(email);

        this.name = name;
        this.state = state;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.accountCredit = 0;
    }

    //Methods

    //TODO: Discuss if we preffer to have this functionality in a PhoneNumberValidor / Manager object
    protected void isAValidPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException {
        if (!phoneNumber.matches("^(?:(?:00|\\+)?549?)?0?(?:11|[2368]\\d)(?:(?=\\d{0,2}15)\\d{2})??\\d{8}$"))
            throw new InvalidPhoneNumberException();
    }

    public void updatePhoneNumberTo(String newPhoneNumber) throws InvalidPhoneNumberException {
        isAValidPhoneNumber(newPhoneNumber);

        this.phone = newPhoneNumber;
    }

    public void changeNameTo(String newName) {
        this.name = newName;
    }

    public void updateEmailTo(String newEmail) throws InvalidEmailException {
        isAValidEmail(newEmail);

        this.email = newEmail;
    }

    private void isAValidEmail(String newEmail) {
        if (!newEmail.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"))
            throw new InvalidEmailException();
    }

    public String getEmail(){
        return this.email;
    }

    public void updateAddressTo(String address) {
        this.address = address;
    }

    public void updateStateTo(String state) {
        this.state = state;
    }

    public void addCredit(int cred){ this.accountCredit+= cred; }

    public void subtractCredit(int cred){ this.accountCredit-= cred; }

    public int getAccountCredit() { return this.accountCredit; }
}
