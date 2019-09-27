package viandasYaTests.UserTests;

import static org.junit.Assert.*;

import app.model.Exceptions.NonexistentMenuException;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;
import app.model.Exceptions.MenuAmountConstraintException;
import app.model.Exceptions.MenuMinimumAmountInfringement;
import app.model.Exceptions.MenuPriceInfringement;
import app.model.Menu.Menu;
import app.model.Menu.MenuFactory;
import app.model.User.Provider.Provider;
import app.model.User.Provider.ProviderFactory;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

@RunWith(MockitoJUnitRunner.class)
public class ProviderTests {

    @Test
    public void testProviderConstructor_ProviderNamedPepePizzasInBernalRoqueSaenzPeña400() {
        Provider provider = ProviderFactory.pepePizzas();

        LocalTime officeHoursFrom = LocalTime.of(9,0);
        LocalTime officeHoursTo = LocalTime.of(18,0);
        DayOfWeek officeDaysFrom = DayOfWeek.MONDAY;
        DayOfWeek officeDaysTo = DayOfWeek.FRIDAY;

        assertEquals("Pepe Pizzas",provider.name);
        assertEquals("logoUrl", provider.logo);
        assertEquals("Bernal", provider.state);
        assertEquals("Roque Saenz Peña 400", provider.address);
        assertEquals(-34.61053, provider.latitude, 0);
        assertEquals(-58.37175, provider.longitude, 0);
        assertEquals("Pizzas y empanadas Pepe", provider.description);
        assertEquals("www.pepepizzas.com.ar",provider.website);
        assertEquals("pepepizzas@gmail.com", provider.email);
        assertEquals("+5491184469357", provider.phone);
        assertEquals(officeHoursFrom, provider.officeHoursFrom);
        assertEquals(officeHoursTo, provider.officeHoursTo);
        assertEquals(officeDaysFrom, provider.officeDaysFrom);
        assertEquals(officeDaysTo, provider.officeDaysTo);
        assertEquals(new ArrayList(), provider.deliveryStates);
        assertEquals(new ArrayList(), provider.menus);
    }

    @Test
    public void addCredit_AProviderAddsCreditToHisAccount(){
        Provider pepePizzas = ProviderFactory.pepePizzas();

        pepePizzas.addCredit(152.10);

        assertEquals(pepePizzas.getAccountCredit(), 152.10, 0);
    }

    @Test
    public void testAddMenu_AProviderAddsAMenuWithCategoryPizza() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {
        Provider provider = ProviderFactory.pepePizzas();
        Menu pizzaMenu = MenuFactory.pizzaMenu();

        provider.addMenu(pizzaMenu);

        assertTrue(provider.menus.contains(pizzaMenu));
    }

    @Test(expected = MenuAmountConstraintException.class)
    public void testAddMenu_AProviderCannotAddANewMenuBecauseHeIsManaging20Menus() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {
        Provider providerMock = mock(Provider.class);

        when(providerMock.menusAmount()).thenReturn(20);
        Menu pizzaMenu = MenuFactory.pizzaMenu();
        doThrow(new MenuAmountConstraintException()).when(providerMock).addMenu(pizzaMenu);

        providerMock.addMenu(pizzaMenu);
    }

    @Test
    public void testUpdateMenu_AProviderUpdatesAMenuNameFromMenu1ToPizzaMenu() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {
        Provider provider = ProviderFactory.pepePizzas();
        Menu initialMenu = MenuFactory.pizzaMenuWithName("Menu 1");

        provider.addMenu(initialMenu);
        assertEquals("Menu 1", initialMenu.name);

        Menu updatedMenu = MenuFactory.copying(initialMenu);
        updatedMenu.changeNameTo("Pizza Menu");

        provider.updateMenu(initialMenu, updatedMenu);
        assertEquals(updatedMenu, provider.menus.get(0));
    }

    @Test
    public void testDeleteMenu_AProviderDeletesAMenu() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {
        Provider provider = ProviderFactory.pepePizzas();
        Menu menu = MenuFactory.pizzaMenuWithName("Menu 1");
        provider.addMenu(menu);

        assertEquals(1,provider.menus.size());

        provider.deleteMenu(menu);

        assertEquals(0,provider.menus.size());
    }

    @Test
    public void testGetMenu_CheckIfTheProviderHasAManu() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {
        Provider pepePizzas = ProviderFactory.pepePizzas();
        Menu menu = MenuFactory.menuWithName("12 Empanadas");
        pepePizzas.addMenu(menu);

        assertEquals(pepePizzas.getMenu("12 Empanadas").getName(), menu.getName());
    }

    @Test(expected = NonexistentMenuException.class)
    public void testGetMenu_TheProviderDoesntHaveTheMenu() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {
        Provider pepePizzas = ProviderFactory.pepePizzas();
        Menu menu1 = MenuFactory.menuWithName("12 Empanadas");

        pepePizzas.addMenu(menu1);
        pepePizzas.getMenu("1 Piza");

    }

}
