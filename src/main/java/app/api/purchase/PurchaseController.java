package app.api.purchase;

import app.api.menu.MenuService;
import app.api.user.UserService;
import app.aspects.Logger;
import app.model.Exceptions.NoEnoughCreditException;
import app.model.Exceptions.ScoreRateOutOfBoundsException;
import app.model.Purchase.Purchase;
import app.model.Purchase.PurchaseRequest;
import app.model.Purchase.PurchaseScore;
import app.model.User.Client.Client;
import app.model.User.Provider.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
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

    @Logger
    @PostMapping("/purchase")
    public void addPurchase(@Valid @RequestBody Purchase purchase) { purchaseService.addPurchase(purchase); }

    @Logger
    @PostMapping("/setScore")
    public void setScore(@Valid @RequestBody PurchaseScore purchaseScore){
        purchaseService.setScore(purchaseScore.id, purchaseScore.score);
    }

    @Logger
    @PostMapping("/makePurchase/{id}")
    @Transactional
    public void makePurchase(@Valid @RequestBody List<PurchaseRequest> purchaseRequests,
                             @PathVariable("id") String id) {

        HashMap<Long, Purchase> purchases = new HashMap<>();
        Collection<Purchase> orders;
        Purchase order;
        Provider provider;
        long providerId;
        long clientId;
        double totalPurchaseAmmount = 0;
        Client client;

        //Get client
        clientId = Long.parseLong(id);
        client = userService.findClientById(clientId);

        for (PurchaseRequest pr: purchaseRequests) {
            providerId = Long.parseLong(pr.providerId);
            if (purchases.containsKey(providerId)){
                order = purchases.get(providerId);
                order.addMenu(pr.menuName, Integer.parseInt(pr.quantity));
                purchases.put(providerId, order);
            }
            else{
                provider = this.userService.findProviderById(providerId);
                order = new Purchase(provider, pr.deliveryType, pr.deliveryDate, pr.deliveryTime);
                order.addMenu(pr.menuName, Integer.parseInt(pr.quantity));
                purchases.put(providerId, order);
            }
        }

        orders = purchases.values();
        for (Purchase p: orders){
            totalPurchaseAmmount += p.getTotalAmount();
        }
        if (totalPurchaseAmmount > client.getAccountCredit()){
            throw new NoEnoughCreditException();
        }
        else{
            for (Purchase p: orders){
                client.makePurchase(p);
            }
        }
    }

    @Logger
    @GetMapping("/ppurchases/{provider}")
    public List<Purchase> getAllProviderPurchases(@PathVariable("provider") String id){
        long providerId = Long.parseLong(id);
        return userService.getAllPurchases()
                .stream()
                .filter(purchase -> (purchase.provider.id) == providerId)
                .collect(Collectors.toList());
    }

    @Logger
    @GetMapping("/cpurchases/{client}")
    public List<Purchase> getAllClientPurchases(@PathVariable("client") String id){
        long clientId = Long.parseLong(id);
        return userService.findClientById(clientId).getPurchases();
    }

    @Logger
    @GetMapping("/pendingScoredPurchases/{client}")
    public int getPendingScoredPurchases(@PathVariable("client") String id){
        int pendingScoredPurchases = 0;
        List<Purchase> purchases = this.getAllClientPurchases(id);
        for(Purchase p: purchases){
            if(p.score == 0){
                pendingScoredPurchases += 1;
            }
        }

        return pendingScoredPurchases;
    }

    @Logger
    @GetMapping("/purchases")
    public List<Purchase> getAllPurchases() {
        return userService.getAllPurchases();
    }
}
