package app;

import app.api.menu.MenuRepository;
import app.api.user.UserRepository;
import app.model.Exceptions.MenuMinimumAmountInfringement;
import app.model.Exceptions.MenuPriceInfringement;
import app.model.Menu.Menu;
import app.model.Menu.MenuFactory;
import app.model.User.Client.ClientFactory;
import app.model.User.Provider.ProviderFactory;
import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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

    @EventListener
    public void appReady(ApplicationReadyEvent event) throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        //Users to Preload
        User tomasHurrell = ClientFactory.tomasHurrell();
        User federicoMartinez = ClientFactory.federicoMartinez();
        User provider = ProviderFactory.pepePizzas();

        Menu pizza = MenuFactory.pizzaMenu();
        Menu burger = MenuFactory.burgerMenu();

        userRepository.save(tomasHurrell);
        userRepository.save(federicoMartinez);
        userRepository.save(provider);

        menuRepository.save(pizza);
        menuRepository.save(burger);
    }
}
