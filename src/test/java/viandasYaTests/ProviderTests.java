package viandasYaTests;

import static org.junit.Assert.*;

import org.junit.Test;
import viandasYaModel.User.Provider.Provider;
import viandasYaModel.User.Provider.ProviderFactory;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
//        assertEquals(location gmaps, provider.location);
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

}
