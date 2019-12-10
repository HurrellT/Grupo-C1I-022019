package app.model.Purchase;

import app.model.DataFormatter.DataFormatter;
import app.model.Email.Email;
import app.model.Email.Sender;
import app.model.Exceptions.NonexistentMenuException;
import app.model.Menu.MenuItem;
import app.model.User.Provider.Provider;

import javax.persistence.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @Transient
    public Provider provider;
    @OneToMany(cascade = CascadeType.ALL)
    public List<MenuItem> order;
    public String deliveryType;
    public LocalDate deliveryDate;
    public LocalDate orderDate;
    public LocalTime deliveryTime;
    public double totalAmount;
    public int score;
    public String showScore;

    //Constructor
    public Purchase(){}

    public Purchase(Provider p, String deliveryType){
        this.provider = p;
        this.order = new ArrayList<>();
        this.deliveryType = deliveryType;
        this.deliveryDate = LocalDate.now();
        this.deliveryTime = LocalTime.now();
        this.totalAmount = 0;
        this.showScore = "";
    }

    public Purchase(Provider p, String deliveryType, LocalDate deliveryDate, LocalTime deliveryTime){
        this.provider = p;
        this.order = new ArrayList<>();
        this.deliveryType = deliveryType;
        this.deliveryDate = deliveryDate;
        this.orderDate = LocalDate.now();
        this.deliveryTime = deliveryTime;
        this.totalAmount = 0;
        this.showScore = "";
    }

    public void addMenu(String menuName, Integer quantity) throws NonexistentMenuException {
        this.order.add(new MenuItem(this.provider.getMenu(menuName), quantity));
        this.totalAmount += (this.provider.getMenu(menuName).getPrice() * quantity);
    }

    public void removeMenu(String menuName){
        this.totalAmount -= (this.provider.getMenu(menuName).getPrice() * this.getMenuQuantity(menuName));
        this.order.removeIf(menuItem -> (menuItem.getMenuName().equals(menuName)));
    }

    public boolean containsMenu(String menuName){
        boolean result = false;
        for (MenuItem item: order) {
            result = item.getMenuName().equals(menuName);
            if (result) break;
        }
        return result;
    }

    public double getTotalAmount(){
        return this.totalAmount;
    }

    private int getMenuQuantity(String menuName){
        int quantity = 0;
        for (MenuItem item: order) {
            if(item.getMenuName() == menuName){
                quantity = item.getQuantity();
                break;
            }
        }
        return quantity;
    }

    public int menusQuantity(){
        return order.size();
    }

    public void makePayment(){
        this.provider.addCredit(this.totalAmount);
    }

    public List<MenuItem> getOrder(){ return this.order; }

    public void setScore(int score){
        this.score = score;
        for (MenuItem mi: this.order){
            mi.setMenuScore(score);
        }
    }

    public int getScore(){
        return this.score;
    }

    public void sendMails(String clientAddress, String clientMessage, DataFormatter formatter){

        //Controller controller = new Controller();
        Sender clientSender;
        Sender providerSender;
        Email clientMail = new Email();
        Email providerMail = new Email();
        ResourceBundle messages = formatter.getResourceBundle();

        //Build email for client
        clientMail.setReceiver(clientAddress);
        clientMail.setSubject(messages.getString("clientSubject"));
        clientMail.setMessage(clientMessage);
        clientMail.setFileName("ViandasYa.png");
        clientMail.setFilePath("ViandasYa.png");

        //Build email for provider
        providerMail.setReceiver(this.provider.getEmail());
        providerMail.setSubject(messages.getString("providerSubject"));
        providerMail.setMessage(this.getEmailProviderMessage(formatter));
        providerMail.setFileName("ViandasYa.png");
        providerMail.setFilePath("ViandasYa.png");

        clientSender = new Sender("Client send", clientMail);
        providerSender = new Sender("Provider send", providerMail);
        clientSender.start();
        providerSender.start();

    }

    private String getEmailProviderMessage(DataFormatter formatter){

        String message;
        ResourceBundle messages = formatter.getResourceBundle();
        NumberFormat currencyFormatter = formatter.getCurrencyFormatter();
        int menuQty = this.menusQuantity();
        int iterator = 0;

        message = messages.getString("providerHeader") + "\n\n";

        if (menuQty > 1){
            message += messages.getString("menus") + " ";
        }
        else{
            message += messages.getString("menu") + " ";
        }
        for (MenuItem item: this.getOrder()) {
            iterator += 1;
            message += item.getMenuName();
            if (iterator == menuQty){
                message += ".\n";
            }
            else{
                message += ", ";
            }
        }
        message += messages.getString("acqCredit") + " " + currencyFormatter.format(this.getTotalAmount()) + "\n";
        message += messages.getString("totCredit") + " " + currencyFormatter.format(this.provider.getAccountCredit())
                + "\n\n";
        message += messages.getString("providerFooter");

        return message;

    }

}
