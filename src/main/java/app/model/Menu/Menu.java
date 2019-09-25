package app.model.Menu;

import app.model.Exceptions.MenuMinimumAmountInfringement;
import app.model.Exceptions.MenuPriceInfringement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Menu {

    //Parameters

    public String name;
    public String description;
    public List<MenuCategory> categories;
    public int deliveryPrice;
    public LocalDate effectiveDateFrom;
    public LocalDate effectiveDateTo;
    public DayNight dayNight;
    public LocalTime effectiveDeliveryHoursFrom;
    public LocalTime effectiveDeliveryHoursTo;
    public DeliveryType deliveryType;
    public LocalTime averageDeliveryTime;
    public float price;
    public int maximumAllowedSells;
    public int minimumAmount;
    public float minimumAmountPrice;
    public int minimumAmount2;
    public float minimumAmount2Price;
    public Score score;


    //Constructor

    public Menu(String menuName, String description, List<MenuCategory> categories,
                int deliveryPrice, LocalDate effectiveDateFrom, LocalDate effectiveDateTo,
                DayNight dayNight, LocalTime effectiveDeliveryHoursFrom, LocalTime effectiveDeliveryHoursTo,
                DeliveryType deliveryType, LocalTime averageDeliveryTime, float price,
                int maximumAllowedSellsAmount, int minimumAmount, float minimumAmountPrice,
                int minimumAmount2, float minimumAmount2Price) throws MenuMinimumAmountInfringement, MenuPriceInfringement {

        this.name = menuName;
        this.description = description;
        this.categories = categories;
        this.deliveryPrice = deliveryPrice;
        this.effectiveDateFrom = effectiveDateFrom;
        this.effectiveDateTo = effectiveDateTo;
        this.dayNight = dayNight;
        this.effectiveDeliveryHoursFrom = effectiveDeliveryHoursFrom;
        this.effectiveDeliveryHoursTo = effectiveDeliveryHoursTo;
        this.deliveryType = deliveryType;
        this.averageDeliveryTime = averageDeliveryTime;
        this.maximumAllowedSells = maximumAllowedSellsAmount;
        this.score = new Score();

        setMinimumAmounts(minimumAmount, minimumAmount2);
        setPrices(price, minimumAmountPrice, minimumAmount2Price);
    }

    //Optional constructor

    public Menu(String menuName, String description, List<MenuCategory> categories,
                LocalDate effectiveDateFrom, LocalDate effectiveDateTo,
                DayNight dayNight, LocalTime effectiveDeliveryHoursFrom, LocalTime effectiveDeliveryHoursTo,
                DeliveryType deliveryType, LocalTime averageDeliveryTime, float price,
                int maximumAllowedSellsAmount, int minimumAmount, float minimumAmountPrice) throws MenuMinimumAmountInfringement, MenuPriceInfringement {

        this.name = menuName;
        this.description = description;
        this.categories = categories;
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
        this.minimumAmount = minimumAmount;
        this.minimumAmountPrice = minimumAmountPrice;
        this.score = new Score();

        setMinimumAmounts(minimumAmount, 0);
        setPrices(price, minimumAmountPrice, 0);
    }

    public Menu(String menuName, String description, List<MenuCategory> categories,
                int deliveryPrice, LocalDate effectiveDateFrom, LocalDate effectiveDateTo,
                DayNight dayNight, LocalTime effectiveDeliveryHoursFrom, LocalTime effectiveDeliveryHoursTo,
                DeliveryType deliveryType, LocalTime averageDeliveryTime, float price,
                int maximumAllowedSellsAmount, int minimumAmount, int minimumAmountPrice) throws MenuMinimumAmountInfringement, MenuPriceInfringement {

        this.name = menuName;
        this.description = description;
        this.categories = categories;
        this.deliveryPrice = deliveryPrice;
        this.effectiveDateFrom = effectiveDateFrom;
        this.effectiveDateTo = effectiveDateTo;
        this.dayNight = dayNight;
        this.effectiveDeliveryHoursFrom = effectiveDeliveryHoursFrom;
        this.effectiveDeliveryHoursTo = effectiveDeliveryHoursTo;
        this.deliveryType = deliveryType;
        this.averageDeliveryTime = averageDeliveryTime;
        this.maximumAllowedSells = maximumAllowedSellsAmount;
        this.score = new Score();

        setMinimumAmounts(minimumAmount, 0);
        setPrices(price, minimumAmountPrice, 0);
    }


    //Methods
    public void changeNameTo(String newMenuName) {
        this.name = newMenuName;
    }

    private void setMinimumAmounts(int minimumAmount, int minimumAmount2) throws MenuMinimumAmountInfringement {

        if (minimumAmount >= 1 && ((minimumAmount2 > minimumAmount) || (minimumAmount2 == 0))) {
            this.minimumAmount = minimumAmount;
            this.minimumAmount2 = minimumAmount2;
        }
        else {
            throw new MenuMinimumAmountInfringement();
        }
    }

    private void setPrices(float price, float minimumAmountPrice, float minimumAmountPrice2) throws MenuPriceInfringement {

        if (minimumAmountPrice2 < minimumAmountPrice && minimumAmountPrice < price) {
            this.price = price;
            this.minimumAmountPrice = minimumAmountPrice;
            this.minimumAmount2Price = minimumAmountPrice2;
        }
        else {
            throw new MenuPriceInfringement();
        }
    }

    public String getName() {return this.name;}

    public double getPrice() {
        return this.price;
    }

    public void setPrice(int price){
        this.price = price;
    }
}
