package app.model.User.Provider;


import app.model.Exceptions.MenuAmountConstraintException;
import app.model.Exceptions.NonexistentMenuException;
import app.model.Menu.Menu;
import app.model.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
//@Table(name = "provider")
@DiscriminatorValue("provider")
public class Provider extends User {

    // Parameters

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
    @OneToMany
    public List<Menu> menus;
    private boolean delivery;

    //Constructor

    public Provider() {super();}

    public Provider(String name, String logo, double latitude, double longitude,
                    String state, String address, String description, String website,
                    String email, String phone, LocalTime officeHoursFrom,
                    LocalTime officeHoursTo, DayOfWeek officeDaysFrom,
                    DayOfWeek officeDaysTo, boolean delivery) {
        super(name, state, address, email, phone);

        this.logo = logo;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.website = website;
        this.officeHoursFrom = officeHoursFrom;
        this.officeHoursTo = officeHoursTo;
        this.officeDaysFrom = officeDaysFrom;
        this.officeDaysTo = officeDaysTo;
        this.menus = new ArrayList<>();
        this.delivery = delivery;
    }

    //Methods

    public void addMenu(Menu menu) throws MenuAmountConstraintException {
        if (menusAmount() < 20) {
            menu.setProviderId(this.id);
            this.menus.add(menu); }
        else{
            throw new MenuAmountConstraintException(); }
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

    public List<Menu> getMenus(){
        return this.menus;
    }

}