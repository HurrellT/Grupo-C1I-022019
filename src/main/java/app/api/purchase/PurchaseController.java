package app.api.purchase;

import app.api.user.UserService;
import app.model.Menu.DeliveryType;
import app.model.Purchase.Purchase;
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

    //Constructor
    public PurchaseController(PurchaseService purchaseService, UserService userService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
    }

    // CREATING -- POST REQUESTS

    @PostMapping("/purchase")
    public void addPurchase(@Valid @RequestBody Purchase purchase) { purchaseService.addPurchase(purchase); }

    @PostMapping("/makePurchase")
    public void makePurchase(@Valid @RequestBody String menuName, @Valid @RequestBody Provider provider) {
        //TODO this method should receive a list of menus, quantity for each one, a client and a delivery type
        User client = this.userService.getAllClients().get(0);
        Purchase order = new Purchase(provider, DeliveryType.DELIVERY);
        order.addMenu(menuName, 1);
        client.makePurchase(order);
    }

    // GETTING -- GET REQUESTS

    @GetMapping("/purchases/{provider}")
    public List<Purchase> getAllProviderPurchases(@PathVariable("provider") String id){
        long providerId = Long.parseLong(id);
        return purchaseService.getAllPurchases()
                .stream()
                .filter(purchase -> (purchase.provider.id) == providerId)
                .collect(Collectors.toList()); }

    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases() {
        return purchaseService.getAllPurchases();
    }
}
