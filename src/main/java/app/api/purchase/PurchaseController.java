package app.api.purchase;

import app.api.menu.MenuService;
import app.api.user.UserService;
import app.model.Menu.DeliveryType;
import app.model.Menu.Menu;
import app.model.Purchase.Purchase;
import app.model.Purchase.PurchaseRequest;
import app.model.User.Client.Client;
import app.model.User.Provider.Provider;
import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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

    @PostMapping("/makePurchase")
    @Transactional
    public void makePurchase(@Valid @RequestBody List<PurchaseRequest> purchaseRequest) {

        HashMap<Long, Purchase> purchases = new HashMap<>();
        Collection<Purchase> orders = new ArrayList<>();
        Purchase order;
        Provider provider;
        long providerId;


        for (PurchaseRequest pr: purchaseRequest) {
            System.out.println("menuId: " + pr.menuName + " provId: " + pr.providerId +
                    " quantity: " + pr.quantity);
            /*providerId = Long.parseLong(pr.providerId);
            if (purchases.containsKey(providerId)){
                order = purchases.get(providerId);
                order.addMenu(pr.menuName, Integer.parseInt(pr.quantity));
                purchases.replace(providerId, order);
            }
            else{
                provider = this.userService.findProviderById(providerId);
                order = new Purchase(provider, DeliveryType.DELIVERY);
                order.addMenu(pr.menuName, Integer.parseInt(pr.quantity));
                purchases.put(providerId, order);
            }*/
        }

        //TODO replace this client with the logged client
        /*User client = this.userService.getAllClients().get(0);

        orders = purchases.values();
        for (Purchase p: orders){
            client.makePurchase(p);
        }*/
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
