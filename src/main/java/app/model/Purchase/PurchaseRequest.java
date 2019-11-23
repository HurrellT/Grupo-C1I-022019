package app.model.Purchase;

public class PurchaseRequest {

    public String providerId;
    public String menuName;
    public String quantity;

    public PurchaseRequest(){}

    public PurchaseRequest(String providerId, String menuName, String quantity){

        this.providerId = providerId;
        this.menuName = menuName;
        this.quantity = quantity;
    }

}
