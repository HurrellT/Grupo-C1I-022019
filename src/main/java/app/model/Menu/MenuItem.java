package app.model.Menu;

import app.model.Exceptions.ScoreRateOutOfBoundsException;

import javax.persistence.*;

@Entity
@Table(name = "menuItem")
public class MenuItem {

    //Parameters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    @OneToOne
    public Menu menu;
    public Integer quantity;

    //Constructor
    public MenuItem(){}

    public MenuItem(Menu m, Integer q){
        this.menu = m;
        this.quantity = q;
    }

    //Methods
    public String getMenuName(){
        return this.menu.getName();
    }
    public Integer getQuantity() { return this.quantity; }
    public void setMenuScore(int score){
        menu.addScore(score);
    }
}
