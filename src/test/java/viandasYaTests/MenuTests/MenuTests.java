package viandasYaTests.MenuTests;

import org.junit.Test;
import viandasYaModel.Exceptions.MenuMinimumAmountInfringement;
import viandasYaModel.Exceptions.MenuPriceInfringement;
import viandasYaModel.Menu.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.Assert.*;

public class MenuTests {

    @Test
    public void testMenuConstructor_AMenuCalledMenuPizzaWithCategoryPizzaEtc() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        Menu menu = MenuFactory.pizzaMenu();

        LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
        LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
        LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
        LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
        LocalTime averageDeliveryTime = LocalTime.of(13,0);

        assertEquals("Pizza Menu", menu.name);
        assertEquals("Menu de pizzas", menu.description);
        assertEquals(MenuCategory.PIZZA, menu.category);
        assertEquals(10,menu.deliveryPrice);
        assertEquals(effectiveDateFrom, menu.effectiveDateFrom);
        assertEquals(effectiveDateTo, menu.effectiveDateTo);
        assertEquals(DayNight.DAY, menu.dayNight);
        assertEquals(effectiveDeliveryHoursFrom, menu.effectiveDeliveryHoursFrom);
        assertEquals(effectiveDeliveryHoursTo, menu.effectiveDeliveryHoursTo);
        assertEquals(DeliveryType.MOTORCYCLE, menu.deliveryType);
        assertEquals(averageDeliveryTime, menu.averageDeliveryTime);
        assertEquals(200, menu.price, 0);
        assertEquals(10, menu.minimumAmount);
        assertEquals(190, menu.minimumAmountPrice,0);
        assertEquals(30, menu.minimumAmount2);
        assertEquals(180, menu.minimumAmount2Price, 0);
        assertEquals(100, menu.maximumAllowedSells);
    }

    @Test
    public void testMenuOptionalConstructor_AMenuCalledMenuBurguerWithCategoryBurgerEtc() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        Menu menu = MenuFactory.burgerMenu();

        LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
        LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
        LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
        LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
        LocalTime averageDeliveryTime = LocalTime.of(13,0);

        assertEquals("Burguer Menu", menu.name);
        assertEquals("Menu de hamburguesas", menu.description);
        assertEquals(MenuCategory.HAMBURGER, menu.category);
        assertEquals(0,menu.deliveryPrice); //TODO: Use optionals instead?
        assertEquals(effectiveDateFrom, menu.effectiveDateFrom);
        assertEquals(effectiveDateTo, menu.effectiveDateTo);
        assertEquals(DayNight.DAY, menu.dayNight);
        assertEquals(effectiveDeliveryHoursFrom, menu.effectiveDeliveryHoursFrom);
        assertEquals(effectiveDeliveryHoursTo, menu.effectiveDeliveryHoursTo);
        assertEquals(DeliveryType.MOTORCYCLE, menu.deliveryType);
        assertEquals(averageDeliveryTime, menu.averageDeliveryTime);
        assertEquals(200, menu.price, 0);
        assertEquals(10, menu.minimumAmount);
        assertEquals(190, menu.minimumAmountPrice, 0);
        assertEquals(0, menu.minimumAmount2); //TODO: Use optionals instead?
        assertEquals(0, menu.minimumAmount2Price, 0); //TODO: Use optionals instead?
        assertEquals(100, menu.maximumAllowedSells);
    }

    @Test
    public void testMenuOptionalConstructor_ATestMenuWithDeliveryPrice() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
        LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
        LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
        LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
        LocalTime averageDeliveryTime = LocalTime.of(13,0);

        Menu menu = new Menu("Test Menu", "Menu de prueba", MenuCategory.PIZZA,
                10,effectiveDateFrom, effectiveDateTo, DayNight.NIGHT,
                effectiveDeliveryHoursFrom, effectiveDeliveryHoursTo, DeliveryType.MOTORCYCLE,
                averageDeliveryTime, 100, 10, 1, 90);

        assertEquals("Test Menu", menu.name);
        assertEquals("Menu de prueba", menu.description);
        assertEquals(MenuCategory.PIZZA, menu.category);
        assertEquals(10,menu.deliveryPrice); //TODO: Use optionals instead?
        assertEquals(effectiveDateFrom, menu.effectiveDateFrom);
        assertEquals(effectiveDateTo, menu.effectiveDateTo);
        assertEquals(DayNight.NIGHT, menu.dayNight);
        assertEquals(effectiveDeliveryHoursFrom, menu.effectiveDeliveryHoursFrom);
        assertEquals(effectiveDeliveryHoursTo, menu.effectiveDeliveryHoursTo);
        assertEquals(DeliveryType.MOTORCYCLE, menu.deliveryType);
        assertEquals(averageDeliveryTime, menu.averageDeliveryTime);
        assertEquals(100, menu.price, 0);
        assertEquals(1, menu.minimumAmount);
        assertEquals(90, menu.minimumAmountPrice, 0);
        assertEquals(0, menu.minimumAmount2); //TODO: Use optionals instead?
        assertEquals(0, menu.minimumAmount2Price, 0); //TODO: Use optionals instead?
        assertEquals(10, menu.maximumAllowedSells);
    }

    @Test(expected = MenuMinimumAmountInfringement.class)
    public void testMenuConstructor_AMenuCannotBeBuiltBecauseOfMinimumAmountLimitsIntersecting() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
        LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
        LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
        LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
        LocalTime averageDeliveryTime = LocalTime.of(13,0);

        Menu menu = new Menu("Test Menu", "Menu de prueba", MenuCategory.PIZZA,
                10,effectiveDateFrom, effectiveDateTo, DayNight.NIGHT,
                effectiveDeliveryHoursFrom, effectiveDeliveryHoursTo, DeliveryType.MOTORCYCLE,
                averageDeliveryTime, 100, 10, 1, 90,
                1,10);
    }

    @Test(expected = MenuPriceInfringement.class)
    public void testMenuConstructor_AMenuCannotBeBuiltBecauseOfMinimumPricesHigherThanTheMainPrice() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
        LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
        LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
        LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
        LocalTime averageDeliveryTime = LocalTime.of(13,0);

        Menu menu = new Menu("Test Menu", "Menu de prueba", MenuCategory.PIZZA,
                10,effectiveDateFrom, effectiveDateTo, DayNight.NIGHT,
                effectiveDeliveryHoursFrom, effectiveDeliveryHoursTo, DeliveryType.MOTORCYCLE,
                averageDeliveryTime, 100, 10, 1, 90,
                10,100);
    }
}
