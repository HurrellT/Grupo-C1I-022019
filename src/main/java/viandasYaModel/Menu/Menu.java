package viandasYaModel.Menu;

import java.time.LocalDate;
import java.time.LocalTime;

public class Menu {

    //Parameters

    public String name;
    public String description;
    public MenuCategory category;
    public int deliveryPrice;
    public LocalDate effectiveDateFrom;
    public LocalDate effectiveDateTo;
    public DayNight dayNight;
    public LocalTime effectiveDeliveryHoursFrom;
    public LocalTime effectiveDeliveryHoursTo;
    public DeliveryType deliveryType;
    public LocalTime averageDeliveryTime;
    public int price;
    public int maximumAllowedSells;
//+ minimumAmount: Int
//+ minimumAmountPrice: Float
//+ minimumAmount2: Int
//+ minimumAmount2Price: Float
    public Score score;


    //Constructor

    public Menu(String menuName, String description, MenuCategory category,
                int deliveryPrice, LocalDate effectiveDateFrom, LocalDate effectiveDateTo,
                DayNight dayNight, LocalTime effectiveDeliveryHoursFrom, LocalTime effectiveDeliveryHoursTo,
                DeliveryType deliveryType, LocalTime averageDeliveryTime, int price,
                int maximumAllowedSellsAmount) {

        this.name = menuName;
        this.description = description;
        this.category = category;
        this.deliveryPrice = deliveryPrice;
        this.effectiveDateFrom = effectiveDateFrom;
        this.effectiveDateTo = effectiveDateTo;
        this.dayNight = dayNight;
        this.effectiveDeliveryHoursFrom = effectiveDeliveryHoursFrom;
        this.effectiveDeliveryHoursTo = effectiveDeliveryHoursTo;
        this.deliveryType = deliveryType;
        this.averageDeliveryTime = averageDeliveryTime;
        this.price = price;
        this.maximumAllowedSells = maximumAllowedSellsAmount;
        this.score = new Score();
    }

    //Optional constructor

    public Menu(String menuName, String description, MenuCategory category,
                LocalDate effectiveDateFrom, LocalDate effectiveDateTo,
                DayNight dayNight, LocalTime effectiveDeliveryHoursFrom, LocalTime effectiveDeliveryHoursTo,
                DeliveryType deliveryType, LocalTime averageDeliveryTime, int price,
                int maximumAllowedSellsAmount) {

        this.name = menuName;
        this.description = description;
        this.category = category;
        this.deliveryPrice = 0;         //No delivery price defined
        this.effectiveDateFrom = effectiveDateFrom;
        this.effectiveDateTo = effectiveDateTo;
        this.dayNight = dayNight;
        this.effectiveDeliveryHoursFrom = effectiveDeliveryHoursFrom;
        this.effectiveDeliveryHoursTo = effectiveDeliveryHoursTo;
        this.deliveryType = deliveryType;
        this.averageDeliveryTime = averageDeliveryTime;
        this.price = price;
        this.maximumAllowedSells = maximumAllowedSellsAmount;
        this.score = new Score();
    }

    //Methods

    public void changeNameTo(String newMenuName) {
        this.name = newMenuName;
    }

}
