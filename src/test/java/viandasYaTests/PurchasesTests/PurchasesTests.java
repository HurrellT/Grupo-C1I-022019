package viandasYaTests.PurchasesTests;

import app.model.Exceptions.*;
import app.model.Menu.Menu;
import app.model.Menu.MenuFactory;
import app.model.Purchase.Purchase;
import app.model.User.Client.Client;
import app.model.User.Client.ClientFactory;
import app.model.User.Provider.Provider;
import app.model.User.Provider.ProviderFactory;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        Purchase order1 = new Purchase(pepePizas, "Delivery");

        pepePizas.addMenu(empanadas);
        order1.addMenu("empanadas", 6);

        assertTrue(order1.containsMenu("empanadas"));
        assertFalse(order1.containsMenu("pizza"));
        assertEquals(1200, order1.getTotalAmount(), 0.0);
    }

    @Test
    public void testRemoveMenu_removeAMenuFromAPurchase() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu piza = MenuFactory.menuWithName("piza");
        Menu milanesa = MenuFactory.menuWithName("milanesa");
        Purchase order1 = new Purchase(pepePizas, "Delivery");

        pepePizas.addMenu(piza);
        pepePizas.addMenu(milanesa);
        order1.addMenu("piza", 2);
        order1.addMenu("milanesa", 1);

        //El pedido tiene piza y milanesa.
        assertTrue(order1.containsMenu("piza"));
        assertTrue(order1.containsMenu("milanesa"));
        assertEquals(600, order1.getTotalAmount(), 0.0);

        order1.removeMenu("piza");

        //El pedido ya no tiene la piza y se resta su precio.
        assertFalse(order1.containsMenu("piza"));
        assertEquals(200, order1.getTotalAmount(), 0.0);

    }

    @Test(expected = NonexistentMenuException.class)
    public void testAddMenu_addANonExistentMenuToAPurchase() throws MenuAmountConstraintException, MenuMinimumAmountInfringement, MenuPriceInfringement {

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu piza = MenuFactory.menuWithName("piza");
        Purchase order1 = new Purchase(pepePizas, "Pick up");

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
        Purchase order1 = new Purchase(pepePizas, "Delivery");

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
        Purchase order1 = new Purchase(pepePizas, "Delivery");

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
        Purchase order1 = new Purchase(pepePizas, "Delivery", LocalDate.ofYearDay(2019, 300), LocalTime.MIDNIGHT);

        fede.makePurchase(order1);

    }

    @Test
    public void makePayment_APurchaseMakesAPayment()throws MenuMinimumAmountInfringement,
            MenuPriceInfringement,
            MenuAmountConstraintException{

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu piza = MenuFactory.pizzaMenu();
        Purchase order = new Purchase(pepePizas, "Delivery");

        piza.setPrice(200);
        pepePizas.addMenu(piza);
        order.addMenu("Pizza Menu", 1);

        assertEquals(0, pepePizas.getAccountCredit(), 0);

        order.makePayment();

        assertEquals(200, pepePizas.getAccountCredit(), 0);

    }

    @Test
    public void makePurchase_makeASuccessfulPurchase() throws MenuMinimumAmountInfringement,
                                                              MenuPriceInfringement,
                                                              MenuAmountConstraintException{

        Provider pepePizas = ProviderFactory.pepePizzas();
        Client juan = ClientFactory.juan();
        Menu piza = MenuFactory.pizzaMenu();
        Menu hamburguesa = MenuFactory.burgerMenu();
        Purchase order = new Purchase(pepePizas, "Delivery");

        juan.addCredit(400);
        piza.setPrice(200);
        hamburguesa.setPrice(100);
        pepePizas.addMenu(piza);
        pepePizas.addMenu(hamburguesa);
        order.addMenu("Pizza Menu", 1);
        order.addMenu("Burguer Menu", 1);

        assertEquals(0, pepePizas.getAccountCredit(), 0);

        juan.makePurchase(order);

        assertEquals(100, juan.getAccountCredit(), 0);
        assertEquals(300, pepePizas.getAccountCredit(), 0);

    }

}
