package viandasYaModel.Purchase;

import viandasYaModel.Menu.Menu;
import viandasYaModel.User.Provider.Provider;

import java.util.ArrayList;
import java.util.List;

public class Purchase {

    public Provider provider;
    public List order;

    public Purchase(Provider p){
        this.provider = p;
        this.order = new ArrayList<Menu>();
    }

    public void addMenu(Menu m){
        if(this.provider.hasTheMenu(m)){
            this.order.add(m);
        } else{
            // invalid menu exception
        }
    }

    public void removeMenu(Menu m){

    }

}
