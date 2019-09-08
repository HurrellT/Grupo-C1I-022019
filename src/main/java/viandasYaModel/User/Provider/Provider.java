package viandasYaModel.User.Provider;

import viandasYaModel.User.User;

import javax.xml.datatype.Duration;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Provider extends User {

    // Parameters

    public String logo;
    //    public Location location;
    public String description;
    public String website;
    public LocalTime officeHoursFrom;
    public LocalTime officeHoursTo;
    public DayOfWeek officeDaysFrom;
    public DayOfWeek officeDaysTo;
    public List deliveryStates;
    public List menus;

    //Constructor

    public Provider(String name, String logo, String state,
                    String address, String description, String website,
                    String email, String phone, LocalTime officeHoursFrom,
                    LocalTime officeHoursTo, DayOfWeek officeDaysFrom,
                    DayOfWeek officeDaysTo) {
        super(name, state, address, email, phone);

        this.logo = logo;
        this.description = description;
        this.website = website;
        this.officeHoursFrom = officeHoursFrom;
        this.officeHoursTo = officeHoursTo;
        this.officeDaysFrom = officeDaysFrom;
        this.officeDaysTo = officeDaysTo;
        this.deliveryStates = new ArrayList();
        this.menus = new ArrayList();
    }
}