package app.api.purchase;

import app.api.menu.MenuService;
import app.api.user.UserService;
import app.model.Menu.DeliveryType;
import app.model.Menu.Menu;
import app.model.Purchase.Purchase;
import app.model.User.Client.Client;
import app.model.User.Provider.Provider;
import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@EnableAutoConfiguration
public class PurchaseController {

    @Autowired
    private final PurchaseService purchaseService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final MenuService menuService;

    //Constructor
    public PurchaseController(PurchaseService purchaseService, UserService userService, MenuService menuService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
        this.menuService = menuService;
    }

    // CREATING -- POST REQUESTS

    @PostMapping("/purchase")
    public void addPurchase(@Valid @RequestBody Purchase purchase) { purchaseService.addPurchase(purchase); }

    // GETTING -- GET REQUESTS
    @GetMapping("/makePurchase/{menuName}")
    public void makePurchase(@PathVariable("menuName") String name) {
        //TODO this method should receive a list of menus, quantity for each one, a client and a delivery type
        //https://www.baeldung.com/spring-request-param (see how to pass a list)

        Menu menu = menuService.findMenuNamed(name);
        Provider provider = userService.findProviderById(menu.getProviderId());
        User client = this.userService.getAllClients().get(0);
        Purchase order = new Purchase(provider, DeliveryType.DELIVERY);
        order.addMenu(menu.name, 1);

        client.makePurchase(order);
        userService.updateClient(client.id, (Client) client);
    }

    @GetMapping("/purchases/{provider}")
    public List<Purchase> getAllProviderPurchases(@PathVariable("provider") String id){
        long providerId = Long.parseLong(id);
        return userService.getAllPurchases()
                .stream()
                .filter(purchase -> (purchase.provider.id) == providerId)
                .collect(Collectors.toList()); }

    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases() {
        return userService.getAllPurchases();
    }
}
