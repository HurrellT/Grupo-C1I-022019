package viandasYaModel.Menu;

import java.time.LocalDate;
import java.time.LocalTime;

public class MenuFactory {

    private static LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
    private static LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
    private static LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
    private static LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
    private static LocalTime averageDeliveryTime = LocalTime.of(13,0);

    public static Menu pizzaMenu() {

        return new Menu(
                "Pizza Menu", "Menu de pizzas",MenuCategory.PIZZA,
                10,effectiveDateFrom,effectiveDateTo,
                DayNight.DAY,effectiveDeliveryHoursFrom,effectiveDeliveryHoursTo,
                DeliveryType.DELIVERY, averageDeliveryTime, 200,
                100);
    }

    public static Menu menuWithName(String menuName) {
        return new Menu(
                menuName, "Menu de pizzas", MenuCategory.PIZZA,
                10, effectiveDateFrom, effectiveDateTo,
                DayNight.DAY, effectiveDeliveryHoursFrom, effectiveDeliveryHoursTo,
                DeliveryType.DELIVERY, averageDeliveryTime, 200,
                100);
    }

    public static Menu copying(Menu menu) {
        return new Menu
                (menu.name, "Menu de pizzas", MenuCategory.PIZZA,
                        10, effectiveDateFrom, effectiveDateTo,
                        DayNight.DAY, effectiveDeliveryHoursFrom, effectiveDeliveryHoursTo,
                        DeliveryType.DELIVERY, averageDeliveryTime, 200,
                        100);
    }

    public static Menu empanadas() {

        return new Menu(
                "Empanadas", "Menu de empanadas",MenuCategory.EMPANADAS,
                10,effectiveDateFrom,effectiveDateTo,
                DayNight.DAY,effectiveDeliveryHoursFrom,effectiveDeliveryHoursTo,
                DeliveryType.DELIVERY, averageDeliveryTime, 100,
                100);
    }

    public static Menu hamburger() {

        return new Menu(
                "Hamburguesa", "Menu de hamburguesa",MenuCategory.HAMBURGER,
                10,effectiveDateFrom,effectiveDateTo,
                DayNight.DAY,effectiveDeliveryHoursFrom,effectiveDeliveryHoursTo,
                DeliveryType.DELIVERY, averageDeliveryTime, 100,
                100);
    }

}