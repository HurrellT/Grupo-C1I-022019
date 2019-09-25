package viandasYaTests.PurchasesTests;

import org.junit.Test;
import viandasYaModel.Exceptions.*;
import viandasYaModel.Menu.DeliveryType;
import viandasYaModel.Menu.Menu;
import viandasYaModel.Menu.MenuFactory;
import viandasYaModel.Purchase.Purchase;
import viandasYaModel.User.Client.Client;
import viandasYaModel.User.Client.ClientFactory;
import viandasYaModel.User.Provider.Provider;
import viandasYaModel.User.Provider.ProviderFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static viandasYaModel.Menu.DeliveryType.*;

public class PurchasesTests {

    @Test
    public void test_deliveryType(){

        Provider pepePizas = ProviderFactory.pepePizzas();

        assertTrue(pepePizas.hasDelivery());

    }

    @Test
    public void testAddMenu_addAMenuToAPurchase() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu empanadas = MenuFactory.menuWithName("empanadas");
        Purchase order1 = new Purchase(pepePizas, DELIVERY);

        pepePizas.addMenu(empanadas);
        order1.addMenu("empanadas", 6);

        assertTrue(order1.containsMenu("empanadas"));
        assertFalse(order1.containsMenu("pizza"));
        assertEquals(1200, order1.getTotalAmount());
    }

    @Test
    public void testRemoveMenu_removeAMenuFromAPurchase() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu piza = MenuFactory.menuWithName("piza");
        Menu milanesa = MenuFactory.menuWithName("milanesa");
        Purchase order1 = new Purchase(pepePizas, DELIVERY);

        pepePizas.addMenu(piza);
        pepePizas.addMenu(milanesa);
        order1.addMenu("piza", 2);
        order1.addMenu("milanesa", 1);

        //El pedido tiene piza y milanesa.
        assertTrue(order1.containsMenu("piza"));
        assertTrue(order1.containsMenu("milanesa"));
        assertEquals(600, order1.getTotalAmount());

        order1.removeMenu("piza");

        //El pedido ya no tiene la piza y se resta su precio.
        assertFalse(order1.containsMenu("piza"));
        assertEquals(200, order1.getTotalAmount());

    }

    @Test(expected = NonexistentMenuException.class)
    public void testAddMenu_addANonExistentMenuToAPurchase() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu piza = MenuFactory.menuWithName("piza");
        Purchase order1 = new Purchase(pepePizas, PICK_UP);

        pepePizas.addMenu(piza);

        //Pido empanadas pero pepePizas solo tiene piza.
        order1.addMenu("empanada", 12);

    }

    @Test
    public void getTotalAmount_getTheSumOfTheAmounts() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu pizza = MenuFactory.pizzaMenu();
        Menu empanada = MenuFactory.empanadas();
        Menu hamburguesa = MenuFactory.burgerMenu();
        Purchase order1 = new Purchase(pepePizas, DELIVERY);

        //Seteo los precios de los menús
        pizza.setPrice(200);
        empanada.setPrice(45);
        hamburguesa.setPrice(150);

        //Agrego los menús a la disponibilidad del proveedor
        pepePizas.addMenu(pizza);
        pepePizas.addMenu(empanada);
        pepePizas.addMenu(hamburguesa);

        //Agrego menús a la orden
        order1.addMenu(pizza.getName(), 2);
        order1.addMenu(empanada.getName(), 6);
        order1.addMenu(hamburguesa.getName(), 3);

        assertEquals(1120, order1.getTotalAmount(), 0.0);
    }

    @Test(expected = NoEnoughCreditException.class)
    public void makePurchase_tryingToMakeAPurchaseWithoutEnoughCredit() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu piza = MenuFactory.pizzaMenu();
        Client fede = ClientFactory.federicoMartinez();
        Purchase order1 = new Purchase(pepePizas, DELIVERY);

        piza.setPrice(200);
        pepePizas.addMenu(piza);
        order1.addMenu(piza.getName(), 1);
        fede.addCredit(10);
        fede.makePurchase(order1);

    }

    @Test(expected = NoItemsInTheOrderException.class)
    public void makePurchase_tryingToMakeAPurchaseWithoutItems(){

        Provider pepePizas = ProviderFactory.pepePizzas();
        Client fede = ClientFactory.federicoMartinez();
        Purchase order1 = new Purchase(pepePizas, DELIVERY);

        fede.makePurchase(order1);

    }

}
