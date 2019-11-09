package app.model.Purchase;

import app.model.User.Provider.Provider;

public class PurchaseRequest {
    public String menuName;
    public Provider provider;

    public PurchaseRequest(String menuName, Provider provider) {
        this.menuName = menuName;
        this.provider = provider;
    }
}
