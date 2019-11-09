package app.model.Menu;

import app.model.Exceptions.MenuMinimumAmountInfringement;
import app.model.Exceptions.MenuPriceInfringement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MenuFactory {

    //Parameters

    private static LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
    private static LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
    private static LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
    private static LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
    private static LocalTime averageDeliveryTime = LocalTime.of(13,0);

    //Static Constructors

    //PIZZA MENU

    public static Menu pizzaMenu() throws MenuMinimumAmountInfringement, MenuPriceInfringement {

        return new Menu(
                "Pizza Menu", "Menu de pizzas", MenuCategory.PIZZA,
                10,effectiveDateFrom,effectiveDateTo,
                DayNight.DAY,effectiveDeliveryHoursFrom,effectiveDeliveryHoursTo,
                DeliveryType.DELIVERY, averageDeliveryTime, 200,
                100,10,190,
                30,180);
    }

    //HAMBURGUER MENU

    public static Menu burgerMenu() throws MenuMinimumAmountInfringement, MenuPriceInfringement {

        return new Menu(
                "Burguer Menu", "Menu de hamburguesas", MenuCategory.HAMBURGER,
                effectiveDateFrom,effectiveDateTo, DayNight.DAY,
                effectiveDeliveryHoursFrom,effectiveDeliveryHoursTo,
                DeliveryType.DELIVERY, averageDeliveryTime, 200,
                100,10,190);
    }

    //SUSHI MENU

    public static Menu sushiMenu() throws MenuMinimumAmountInfringement, MenuPriceInfringement {

        return new Menu(
                "Sushi Menu", "Menu de sushi", MenuCategory.SUSHI,
                effectiveDateFrom,effectiveDateTo, DayNight.DAY,
                effectiveDeliveryHoursFrom,effectiveDeliveryHoursTo,
                DeliveryType.DELIVERY, averageDeliveryTime, 500,
                100,10,190);
    }

    //PIZZA MENU WITH CUSTOM NAME

    public static Menu pizzaMenuWithName(String menuName) throws MenuMinimumAmountInfringement, MenuPriceInfringement {

        return new Menu(
                menuName, "Menu de pizzas", MenuCategory.PIZZA,
                10, effectiveDateFrom, effectiveDateTo,
                DayNight.DAY, effectiveDeliveryHoursFrom, effectiveDeliveryHoursTo,
                DeliveryType.DELIVERY, averageDeliveryTime, 200,
                100,10,190,
                30,180);
    }

    //NEW MENU COPYING ANOTHER MENU

    public static Menu copying(Menu menu) throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        return new Menu(menu.name, menu.description, menu.category,
                        menu.deliveryPrice, menu.effectiveDateFrom, menu.effectiveDateTo,
                        menu.dayNight, menu.effectiveDeliveryHoursFrom, menu.effectiveDeliveryHoursTo,
                        menu.deliveryType, menu.averageDeliveryTime, menu.price,
                        menu.maximumAllowedSells,menu.minimumAmount,menu.minimumAmountPrice,
                        menu.minimumAmount2,menu.minimumAmount2Price);
    }

    //TEST MENU

    public static Menu testMenu() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
        LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
        LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
        LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
        LocalTime averageDeliveryTime = LocalTime.of(13,0);

        return new Menu("Test Menu", "Menu de prueba", MenuCategory.SUSHI,
                10,effectiveDateFrom, effectiveDateTo, DayNight.NIGHT,
                effectiveDeliveryHoursFrom, effectiveDeliveryHoursTo, DeliveryType.DELIVERY,
                averageDeliveryTime, 100, 10, 1, 90,
                1,10);
    }

    //EMPANADAS MENU

    public static Menu empanadas() throws MenuMinimumAmountInfringement, MenuPriceInfringement {


        return new Menu(
                "Empanadas", "Menu de empanadas",MenuCategory.EMPANADAS,
                10,effectiveDateFrom,effectiveDateTo,
                DayNight.DAY,effectiveDeliveryHoursFrom,effectiveDeliveryHoursTo,
                DeliveryType.DELIVERY, averageDeliveryTime,
                100, 100, 1,
                90,10,10);
    }

    //MENU WITH NAME

    public static Menu menuWithName(String menuName) throws MenuMinimumAmountInfringement, MenuPriceInfringement {

        return new Menu(
                menuName, "Descripcion", MenuCategory.PIZZA,
                10, effectiveDateFrom, effectiveDateTo,
                DayNight.DAY, effectiveDeliveryHoursFrom, effectiveDeliveryHoursTo,
                DeliveryType.DELIVERY, averageDeliveryTime, 200,
                100, 1, 90);
    }

}