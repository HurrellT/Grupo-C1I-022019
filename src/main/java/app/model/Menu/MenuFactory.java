package app.model.Menu;

import app.model.Exceptions.MenuMinimumAmountInfringement;
import app.model.Exceptions.MenuPriceInfringement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MenuFactory {

    private static LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
    private static LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
    private static LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
    private static LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
    private static LocalTime averageDeliveryTime = LocalTime.of(13,0);

    public static Menu pizzaMenu() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        List<MenuCategory> menuCategories = new ArrayList<MenuCategory>();
        menuCategories.add(MenuCategory.PIZZA);

        return new Menu(
                "Pizza Menu", "Menu de pizzas", menuCategories,
                10,effectiveDateFrom,effectiveDateTo,
                DayNight.DAY,effectiveDeliveryHoursFrom,effectiveDeliveryHoursTo,
                DeliveryType.MOTORCYCLE, averageDeliveryTime, 200,
                100,10,190,
                30,180);
    }

    public static Menu burgerMenu() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        List<MenuCategory> menuCategories = new ArrayList<MenuCategory>();
        menuCategories.add(MenuCategory.HAMBURGER);

        return new Menu(
                "Burguer Menu", "Menu de hamburguesas", menuCategories,
                effectiveDateFrom,effectiveDateTo, DayNight.DAY,
                effectiveDeliveryHoursFrom,effectiveDeliveryHoursTo,
                DeliveryType.MOTORCYCLE, averageDeliveryTime, 200,
                100,10,190);
    }


    public static Menu pizzaMenuWithName(String menuName) throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        List<MenuCategory> menuCategories = new ArrayList<MenuCategory>();
        menuCategories.add(MenuCategory.PIZZA);

        return new Menu(
                menuName, "Menu de pizzas", menuCategories,
                10, effectiveDateFrom, effectiveDateTo,
                DayNight.DAY, effectiveDeliveryHoursFrom, effectiveDeliveryHoursTo,
                DeliveryType.MOTORCYCLE, averageDeliveryTime, 200,
                100,10,190,
                30,180);
    }

    public static Menu copying(Menu menu) throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        return new Menu
                (menu.name, menu.description, menu.categories,
                        menu.deliveryPrice, menu.effectiveDateFrom, menu.effectiveDateTo,
                        menu.dayNight, menu.effectiveDeliveryHoursFrom, menu.effectiveDeliveryHoursTo,
                        menu.deliveryType, menu.averageDeliveryTime, menu.price,
                        menu.maximumAllowedSells,menu.minimumAmount,menu.minimumAmountPrice,
                        menu.minimumAmount2,menu.minimumAmount2Price);
    }

    public static Menu testMenu() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
        LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
        LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
        LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
        LocalTime averageDeliveryTime = LocalTime.of(13,0);

        List<MenuCategory> menuCategories = new ArrayList<MenuCategory>();

        menuCategories.add(MenuCategory.PIZZA);
        menuCategories.add(MenuCategory.SUSHI);

        return new Menu("Test Menu", "Menu de prueba", menuCategories,
                10,effectiveDateFrom, effectiveDateTo, DayNight.NIGHT,
                effectiveDeliveryHoursFrom, effectiveDeliveryHoursTo, DeliveryType.MOTORCYCLE,
                averageDeliveryTime, 100, 10, 1, 90,
                1,10);
    }

}