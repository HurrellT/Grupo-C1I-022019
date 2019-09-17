package viandasYaModel.Purchase;

import viandasYaModel.Exceptions.NonexistentMenuException;
import viandasYaModel.Menu.MenuItem;
import viandasYaModel.User.Provider.Provider;

import java.util.ArrayList;
import java.util.List;

public class Purchase {

    public Provider provider;
    public List<MenuItem> order;

    public Purchase(Provider p){
        this.provider = p;
        this.order = new ArrayList<>();
    }

    public void addMenu(String menuName, Integer quantity) throws NonexistentMenuException {
        this.order.add(new MenuItem(this.provider.getMenu(menuName), quantity));
    }

    public void removeMenu(String menuName){
        this.order.removeIf(menuItem -> (menuItem.getMenuName().equals(menuName)));
    }

    public boolean containsMenu(String menuName){
        boolean result = false;
        for (MenuItem item: order) {
            result = item.getMenuName().equals(menuName);
            if (result) break;
        }
        return result;
    }

}
