package app.api.purchase;

import app.model.Purchase.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseController {

    @Autowired
    private final PurchaseService purchaseService;

    //Constructor
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // CREATING -- POST REQUESTS

    @PostMapping("/purchase")
    public void addPurchase(@Valid @RequestBody Purchase purchase) { purchaseService.addPurchase(purchase); }

    @GetMapping("/purchases")
    public List<Purchase> getAllProviderPurchases(@PathVariable("id") String id){
        long providerId = Long.parseLong(id);
        return purchaseService.getAllPurchases()
                .stream()
                .filter(purchase -> (purchase.provider.id) == providerId)
                .collect(Collectors.toList()); }

}
