package app.model.Purchase;

import java.time.LocalDate;
import java.time.LocalTime;

public class PurchaseRequest {

    public String providerId;
    public String menuName;
    public String quantity;
    public LocalTime deliveryTime;
    public LocalDate deliveryDate;
    public String deliveryType;

    public PurchaseRequest(){}

    public PurchaseRequest(String providerId, String menuName, String quantity, LocalTime deliveryTime,
                           LocalDate deliveryDate, String deliveryType){

        this.providerId = providerId;
        this.menuName = menuName;
        this.quantity = quantity;
        this.deliveryTime = deliveryTime;
        this.deliveryDate = deliveryDate;
        this.deliveryType = deliveryType;
    }

}
