package viandasYaTests.MenuTests;

import org.junit.Test;
import viandasYaModel.Menu.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.Assert.*;

public class MenuTests {

    @Test
    public void testMenuConstructor_AMenuCalledMenuPizzaWithCategoryPizzaEtc() {
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
        assertEquals(200, menu.price);
//        assertEquals(11, menu.minimumAmount);     //TODO: WAITING TO GET AN ANSWER ABOUT MINIMUM RANGES
//        assertEquals(190, menu.minimumAmountPrice);
//        assertEquals(31, menu.minimumAmount2);
//        assertEquals(180, menu.minimumAmount2Price);
        assertEquals(100, menu.maximumAllowedSells);
    }

    @Test
    public void testMenuOptionalConstructor_AMenuCalledMenuBurguerWithCategoryBurgerEtc() {
        Menu menu = MenuFactory.burgerMenu();

        LocalDate effectiveDateFrom = LocalDate.of(2018, 1, 1);
        LocalDate effectiveDateTo = LocalDate.of(2020, 1, 1);
        LocalTime effectiveDeliveryHoursFrom = LocalTime.of(9,0);
        LocalTime effectiveDeliveryHoursTo = LocalTime.of(18,0);
        LocalTime averageDeliveryTime = LocalTime.of(13,0);

        assertEquals("Burguer Menu", menu.name);
        assertEquals("Menu de hamburguesas", menu.description);
        assertEquals(MenuCategory.HAMBURGER, menu.category);
        assertEquals(0,menu.deliveryPrice);
        assertEquals(effectiveDateFrom, menu.effectiveDateFrom);
        assertEquals(effectiveDateTo, menu.effectiveDateTo);
        assertEquals(DayNight.DAY, menu.dayNight);
        assertEquals(effectiveDeliveryHoursFrom, menu.effectiveDeliveryHoursFrom);
        assertEquals(effectiveDeliveryHoursTo, menu.effectiveDeliveryHoursTo);
        assertEquals(DeliveryType.MOTORCYCLE, menu.deliveryType);
        assertEquals(averageDeliveryTime, menu.averageDeliveryTime);
        assertEquals(200, menu.price);
//        assertEquals(11, menu.minimumAmount);     //TODO: WAITING TO GET AN ANSWER ABOUT MINIMUM RANGES
//        assertEquals(190, menu.minimumAmountPrice);
//        assertEquals(31, menu.minimumAmount2);
//        assertEquals(180, menu.minimumAmount2Price);
        assertEquals(100, menu.maximumAllowedSells);
    }

//    @Test
//    public void testMenuConstructor_AMenuCannotBeBuiltBecauseOfMinimumAmountLimits() {
//        Menu menu = MenuFactory.menuWithMinimumAmounts();
//        Menu anotherMenu = MenuFactory.menuWithMinimumAmounts()
//    }
}
