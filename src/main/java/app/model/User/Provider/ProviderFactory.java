package app.model.User.Provider;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class ProviderFactory {
    public static Provider pepePizzas() {

        LocalTime officeHoursFrom = LocalTime.of(9,0);
        LocalTime officeHoursTo = LocalTime.of(18,0);
        DayOfWeek officeDaysFrom = DayOfWeek.MONDAY;
        DayOfWeek officeDaysTo = DayOfWeek.FRIDAY;

        return new Provider(
                "Pepe Pizzas", "logoUrl",     -34.61053, -58.37175,
                "Bernal", "Roque Saenz Peña 400",
                "Pizzas y empanadas Pepe", "www.pepepizzas.com.ar",
                "pepepizzas@gmail.com", "+5491184469357",
                officeHoursFrom, officeHoursTo, officeDaysFrom, officeDaysTo, true);
    }

    public static Provider palermoSushi() {

        LocalTime officeHoursFrom = LocalTime.of(18,0);
        LocalTime officeHoursTo = LocalTime.of(23,0);
        DayOfWeek officeDaysFrom = DayOfWeek.MONDAY;
        DayOfWeek officeDaysTo = DayOfWeek.SUNDAY;

        return new Provider(
                "Palermo Sushi", "logoUrl",     -34.61053, -58.37175,
                "Palermo", "Scalabrini Ortiz 3200",
                "Sushi en Palermo", "www.palermosushi.com.ar",
                "palermosushi@gmail.com", "+5491184464357",
                officeHoursFrom, officeHoursTo, officeDaysFrom, officeDaysTo, true);
    }



}
