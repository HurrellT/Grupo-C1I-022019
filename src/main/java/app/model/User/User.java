package app.model.User;

import app.model.Exceptions.NoEnoughCreditException;
import app.model.Exceptions.NoItemsInTheOrderException;
import app.model.Menu.Menu;
import app.model.Purchase.Purchase;
import app.model.Validators.EmailValidation;
import app.model.Exceptions.InvalidEmailException;
import app.model.Exceptions.InvalidPhoneNumberException;
import app.model.Validators.PhoneNumberValidation;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",discriminatorType = DiscriminatorType.STRING)
public class User {

    //Parameters

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Column(name = "type", insertable = false, updatable = false)
    public String type;

    @NotEmpty(message = "validations.name")
    public String name;

    @NotEmpty(message = "validations.state")
    public String state;

    @NotEmpty(message = "validations.address")
    public String address;

    @EmailValidation
    public String email;

    @PhoneNumberValidation
    public String phone;

    public double accountCredit;

    //Constructor

    public User() {}

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

    public void addCredit(double cred){ this.accountCredit+= cred; }

    public void subtractCredit(double cred){
        if (this.accountCredit == 0) {
            throw new NoEnoughCreditException();
        }
        else {
            this.accountCredit-= cred;
        }
    }

    public double getAccountCredit() { return this.accountCredit; }

    public List<Menu> getMenus(){
        return new ArrayList<>();
    }
}
