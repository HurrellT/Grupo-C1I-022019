package viandasYaModel.Menu;

public class MenuFactory {

    public static Menu pizzaMenu() {
        return new Menu("Pizza Menu");
    }

    public static Menu menuWithName(String menuName) {
        return new Menu(menuName);
    }

    public static Menu copying(Menu menu) {
        return new Menu(menu.name);
    }
}

