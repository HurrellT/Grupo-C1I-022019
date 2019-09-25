package app.model.User.Provider;


import app.model.Exceptions.MenuAmountConstraintException;
import app.model.Exceptions.NonexistentMenuException;
import app.model.Menu.Menu;
import app.model.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Provider extends User {

    // Parameters

    private final UUID id;
    public String logo;
    //Location Params
    public double latitude;
    public double longitude;
    public String description;
    public String website;
    public LocalTime officeHoursFrom;
    public LocalTime officeHoursTo;
    public DayOfWeek officeDaysFrom;
    public DayOfWeek officeDaysTo;
    public List deliveryStates;
    public List<Menu> menus;
    private boolean delivery;

    //Constructor

    public Provider(String name, String logo, double latitude, double longitude,
                    String state, String address, String description, String website,
                    String email, String phone, LocalTime officeHoursFrom,
                    LocalTime officeHoursTo, DayOfWeek officeDaysFrom,
                    DayOfWeek officeDaysTo, boolean delivery) {
        super(name, state, address, email, phone);

        this.id = UUID.randomUUID();
        this.logo = logo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.website = website;
        this.officeHoursFrom = officeHoursFrom;
        this.officeHoursTo = officeHoursTo;
        this.officeDaysFrom = officeDaysFrom;
        this.officeDaysTo = officeDaysTo;
        this.deliveryStates = new ArrayList();
        this.menus = new ArrayList<>();
        this.delivery = delivery;
    }

    public Provider(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("lat") double latitude,
            @JsonProperty("long") double longitude) {
        super(name, "Bernal","Calle falsa", "hurrelltomas@gmail.com", "+5491157784955");

        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    //Methods

    public void addMenu(Menu menu) throws MenuAmountConstraintException {
        if (menusAmount() < 20)
            this.menus.add(menu);
        else {
            throw new MenuAmountConstraintException();
        }
    }

    public Integer menusAmount() {
        return menus.size();
    }

    public void updateMenu(Menu oldMenu, Menu updatedMenu) {
        menus.remove(oldMenu);
        menus.add(updatedMenu);
    }

    public void deleteMenu(Menu menu) {
        menus.remove(menu);
    }

    public Menu getMenu(String menuName) throws NonexistentMenuException {
        for(Menu m : this.menus) {
            if (m.getName().equals(menuName)){
                return m;
            }
        }
        throw new NonexistentMenuException();
    }

    public boolean hasDelivery(){return delivery;}

}