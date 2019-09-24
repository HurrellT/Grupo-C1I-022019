package viandasYaModel.Menu;

public class MenuItem {

    //Parameters
    public Menu menu;
    public Integer quantity;

    public MenuItem(Menu m, Integer q){
        this.menu = m;
        this.quantity = q;
    }

    //Methods
    public String getMenuName(){
        return this.menu.getName();
    }
    public Integer getQuantity() { return this.quantity; }
}
