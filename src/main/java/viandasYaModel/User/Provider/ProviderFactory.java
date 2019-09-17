package viandasYaModel.User.Provider;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ProviderFactory {
    public static Provider pepePizzas() {

        LocalTime officeHoursFrom = LocalTime.of(9,0);
        LocalTime officeHoursTo = LocalTime.of(18,0);
        DayOfWeek officeDaysFrom = DayOfWeek.MONDAY;
        DayOfWeek officeDaysTo = DayOfWeek.FRIDAY;

        return new Provider(
                "Pepe Pizzas", "logoUrl",
                "Bernal", "Roque Saenz Peña 400",
                "Pizzas y empanadas Pepe", "www.pepepizzas.com.ar",
                "pepepizzas@gmail.com", "+5491184469357",
                officeHoursFrom, officeHoursTo, officeDaysFrom, officeDaysTo, true);
    }
}
