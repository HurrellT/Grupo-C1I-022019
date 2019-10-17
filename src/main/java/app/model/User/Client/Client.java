package app.model.User.Client;


import app.model.Exceptions.InvalidPhoneNumberException;
import app.model.Exceptions.NoEnoughCreditException;
import app.model.Exceptions.NoItemsInTheOrderException;
import app.model.Menu.MenuItem;
import app.model.Purchase.Purchase;
import app.model.User.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@DiscriminatorValue("client")
public class Client extends User {

    //Parameters

    public String lastname;

    //Constructor

    public Client() {super();}

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

        double totalAmount = p.getTotalAmount();

        if (p.menusQuantity() > 0){

            if(totalAmount <= this.getAccountCredit()){
                p.makePayment();
                this.subtractCredit(totalAmount);
                p.sendMails(this.email, this.getEmailMessage(p));
            }
            else{
                throw new NoEnoughCreditException();
            }

        }
        else{
            throw new NoItemsInTheOrderException();
        }

    }

    private String getEmailMessage(Purchase p){

        String message;
        int menuQty = p.menusQuantity();
        int iterator = 0;

        message = "Su compra ha sido realizada con éxito. \n";

        if (menuQty > 1){
            message += "Menús: ";
        }
        else{
            message += "Menú: ";
        }
        for (MenuItem item: p.getOrder()) {
            iterator += 1;
            message += item.getMenuName();
            if (iterator == menuQty){
                message += ".\n";
            }
            else{
                message += ", ";
            }
        }
        message += "Costo: " + p.getTotalAmount() + "\n";
        message += "Crédito restante: " + this.getAccountCredit();

        return message;

    }

}
