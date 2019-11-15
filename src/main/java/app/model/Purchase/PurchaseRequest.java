package app.model.Purchase;

public class PurchaseRequest {

    public String menuName;
    public String providerId;
    public String quantity;

    public PurchaseRequest (){}

    public PurchaseRequest (String menuName, String providerId, String quantity){

        this.menuName = menuName;
        this.providerId = providerId;
        this.quantity = quantity;
    }

}
