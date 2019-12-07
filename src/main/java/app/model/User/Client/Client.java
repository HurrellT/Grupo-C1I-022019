package app.model.User.Client;


import app.model.DataFormatter.DataFormatter;
import app.model.Exceptions.InvalidPhoneNumberException;
import app.model.Exceptions.NoEnoughCreditException;
import app.model.Exceptions.NoItemsInTheOrderException;
import app.model.Menu.MenuItem;
import app.model.Purchase.Purchase;
import app.model.User.Provider.Provider;
import app.model.User.User;

import javax.persistence.*;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Function;

@Entity
//@Table(name = "client")
@DiscriminatorValue("client")
public class Client extends User {

    //Parameters

    public String lastname;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Purchase> purchases;

    //Constructor

    public Client() {super();}

    public Client(String name, String lastname,
                  String address, String state,
                  String email, String phoneNumber) throws InvalidPhoneNumberException {
        super(name,state,address,email,phoneNumber);

        this.lastname = lastname;
        this.purchases = new ArrayList<>();
    }

    //Methods

    public void changeLastnameTo(String newLastname) {
        this.lastname = newLastname;
    }

    public List<Purchase> getPurchases(){
        return this.purchases;
    }

    public void setPurchases(List<Purchase> purchases) {this.purchases = purchases;}

    public void makePurchase(Purchase p) throws NoEnoughCreditException, NoItemsInTheOrderException {

        double totalAmount = p.getTotalAmount();
        DataFormatter f = new DataFormatter("US");

        if (p.menusQuantity() > 0){

            if(totalAmount <= this.getAccountCredit()){
                p.makePayment();
                this.subtractCredit(totalAmount);
                this.purchases.add(p);
                p.sendMails(this.email, this.getEmailMessage(p, f), f);
            }
            else{
                throw new NoEnoughCreditException();
            }

        }
        else{
            throw new NoItemsInTheOrderException();
        }
    }

    private String getEmailMessage(Purchase p, DataFormatter formatter){

        String message;
        ResourceBundle messages = formatter.getResourceBundle();
        NumberFormat currencyFormatter = formatter.getCurrencyFormatter();
        int menuQty = p.menusQuantity();
        int iterator = 0;

        message = messages.getString("clientHeader") + "\n\n";

        if (menuQty > 1){
            message += messages.getString("menus") + " ";
        }
        else{
            message += messages.getString("menu") + " ";
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
        message += messages.getString("cost") + " " + currencyFormatter.format(p.getTotalAmount()) + "\n";
        message += messages.getString("remCredit") + " " + currencyFormatter.format(this.getAccountCredit()) + "\n\n";
        message += messages.getString("clientFooter");

        return message;

    }


    public Provider convertToProvider(String logo, double latitude,
                                      double longitude, String description,
                                      String website, LocalTime officeHoursFrom,
                                      LocalTime officeHoursTo, DayOfWeek officeDaysFrom,
                                      DayOfWeek officeDaysTo, List menus,
                                      boolean delivery) {
        Provider provider = new Provider(this.name, logo, latitude, longitude,
                this.state, this.address, description, website,
                this.email, this.phone, officeHoursFrom, officeHoursTo,
                officeDaysFrom, officeDaysTo, delivery);
        provider.id = this.id;
        return provider;
    }
}
