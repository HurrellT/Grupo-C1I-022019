package app.api.purchase;

import app.model.Purchase.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    //Parameters
    @Autowired
    private final PurchaseRepository purchaseRepository;

    //Constructor
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    //Methods
    public Purchase addPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Purchase findPurchaseById(long id) {
        return purchaseRepository.findById(id).get();
    }

    public List<Purchase> getAllPurchases() {
        return (List<Purchase>) purchaseRepository.findAll();
    }

}
