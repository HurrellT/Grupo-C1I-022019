package viandasYaModel.Purchase;

import viandasYaModel.Email.Controller;
import viandasYaModel.Email.Email;
import viandasYaModel.Exceptions.NonexistentMenuException;
import viandasYaModel.Menu.DeliveryType;
import viandasYaModel.Menu.MenuItem;
import viandasYaModel.User.Provider.Provider;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Purchase {

    public Provider provider;
    public List<MenuItem> order;
    public DeliveryType deliveryType;
    public LocalDate deliveryDate;
    public LocalTime deliveryTime;

    public Purchase(Provider p, DeliveryType deliveryType){
        this.provider = p;
        this.order = new ArrayList<>();
        this.deliveryType = deliveryType;
        this.deliveryDate = LocalDate.now();
        this.deliveryTime = LocalTime.now();
    }

    public Purchase(Provider p, DeliveryType deliveryType, LocalDate deliveryDate, LocalTime deliveryTime){
        this.provider = p;
        this.order = new ArrayList<>();
        this.deliveryType = deliveryType;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
    }

    public void addMenu(String menuName, Integer quantity) throws NonexistentMenuException {
        this.order.add(new MenuItem(this.provider.getMenu(menuName), quantity));
    }

    public void removeMenu(String menuName){
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

    public void sendMails(String clientAddress){

        Controller controller = new Controller();
        Email clientMail = new Email();
        Email providerMail = new Email();

        //Build email for client
        clientMail.setReceiver(clientAddress);
        clientMail.setSubject("Compra a través de viandas ya");
        clientMail.setMessage("Su compra ha sido realizada con éxito");

        //Build email for provider
        providerMail.setReceiver(this.provider.getEmail());
        providerMail.setSubject("Venta a través de viandas ya");
        providerMail.setMessage("La venta ha sido realizada con éxito");

        //TODO agregar más información en los mails:
        // Cliente: menú comprado, costo, credito restante
        // Proveedor: menú vendido, credito adquirido

        controller.sendMail(clientMail);

    }

}
