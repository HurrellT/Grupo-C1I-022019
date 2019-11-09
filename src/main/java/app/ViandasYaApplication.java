package app;

import app.api.menu.MenuRepository;
import app.api.purchase.PurchaseRepository;
import app.api.user.UserRepository;
import app.api.user.UserService;
import app.model.Exceptions.MenuAmountConstraintException;
import app.model.Exceptions.MenuMinimumAmountInfringement;
import app.model.Exceptions.MenuPriceInfringement;
import app.model.Menu.Menu;
import app.model.Menu.MenuFactory;
import app.model.Purchase.Purchase;
import app.model.User.Client.ClientFactory;
import app.model.User.Provider.Provider;
import app.model.User.Provider.ProviderFactory;
import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static app.model.Menu.DeliveryType.DELIVERY;

@SpringBootApplication
public class ViandasYaApplication {


    public static void main(String[] args) {
        SpringApplication.run(ViandasYaApplication.class, args);
    }
}

@Component
class DBPreloader {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MenuRepository menuRepository;
//    @Autowired
//    private PurchaseRepository purchaseRepository;
    @Autowired
    private UserService userService;

    @EventListener
    public void appReady(ApplicationReadyEvent event) throws MenuMinimumAmountInfringement, MenuPriceInfringement, MenuAmountConstraintException {
        //Users to Preload
        User tomasHurrell = ClientFactory.tomasHurrell();
        User federicoMartinez = ClientFactory.federicoMartinez();
        Provider pepePizzas = ProviderFactory.pepePizzas();
        Provider palermoSushi = ProviderFactory.palermoSushi();

        //Menus to Preload
        Menu pizza = MenuFactory.pizzaMenu();
        Menu burger = MenuFactory.burgerMenu();
        Menu sushi = MenuFactory.sushiMenu();

        menuRepository.save(burger);
        menuRepository.save(pizza);
        menuRepository.save(sushi);

        federicoMartinez.addCredit(500);

        userRepository.save(federicoMartinez);
        userRepository.save(tomasHurrell);
        userRepository.save(pepePizzas);
        userRepository.save(palermoSushi);

        ((Provider) pepePizzas).addMenu(pizza);
        ((Provider) palermoSushi).addMenu(sushi);
        ((Provider) palermoSushi).addMenu(burger);

        userService.updateProvider(pepePizzas.id, pepePizzas);
        userService.updateProvider(palermoSushi.id, palermoSushi);

        /*//Purchases to preload
        Purchase order1 = new Purchase(pepePizzas, DELIVERY);
        Purchase order2 = new Purchase(palermoSushi, DELIVERY);

        menuRepository.save(pizza);
        menuRepository.save(burger);
        menuRepository.save(sushi);

        ((Provider) pepePizzas).addMenu(pizza);
        ((Provider) palermoSushi).addMenu(sushi);

        purchaseRepository.save(order1);
        purchaseRepository.save(order2);

        /*order1.addMenu("Pizza Menu", 1);
        order2.addMenu("Sushi Menu", 1);

        purchaseRepository.(order1);
        purchaseRepository.save(order2);*/

    }
}
