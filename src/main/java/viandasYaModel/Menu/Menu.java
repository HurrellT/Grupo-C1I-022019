package viandasYaModel.Menu;

public class Menu {

    //Parameters

    public String name;

    //Constructor

    public Menu(String menuName) {
        this.name = menuName;
    }

    //Methods

    public void changeNameTo(String newMenuName) {
        this.name = newMenuName;
    }




//+ name: String
//+ description: String
//+ category: MenuCategory
//+ deliveryPrice: Int
//+ effectiveDate: DateInterval o vars separadas
//+ dayNight: DayNight
//+ effectiveDeliveryHours: Time
//+ deliveryType: DeliveryType
//+ averageDeliveryTime: Time
//+ price: Float
//+ minimumAmount: Int
//+ minimumAmountPrice: Float
//+ minimumAmount2: Int
//+ minimumAmount2Price: Float
//+ maximumSellsAmount: Int
}
