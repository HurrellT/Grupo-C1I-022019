package viandasYaModel.User.Client;

import viandasYaModel.Exceptions.InvalidPhoneNumberException;
import viandasYaModel.Exceptions.NoEnoughCreditException;
import viandasYaModel.Exceptions.NoItemsInTheOrderException;
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

    public void makePurchase(Purchase p) throws NoEnoughCreditException, NoItemsInTheOrderException {

        int totalAmount = p.getTotalAmount();

        if (p.menusQuantity() > 0){

            if(totalAmount <= this.getAccountCredit()){
                p.makePayment();
                this.subtractCredit(totalAmount);
                p.sendMails(this.email);
            }
            else{
                throw new NoEnoughCreditException();
            }

        }
        else{
            throw new NoItemsInTheOrderException();
        }

    }

}
