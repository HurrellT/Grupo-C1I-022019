package viandasYaModel.User.Client;

import viandasYaModel.Exceptions.InvalidPhoneNumberException;
import viandasYaModel.Purchase.Purchase;
import viandasYaModel.User.User;

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

    public void makePurchase(Purchase p){

        //Validar que la compra se realice correctamente
        p.sendMails(this.email);

    }


}
