package viandasYaTests.PurchasesTests;

import org.junit.Test;
import viandasYaModel.Exceptions.MenuAmountConstraintException;
import viandasYaModel.Exceptions.NonexistentMenuException;
import viandasYaModel.Menu.Menu;
import viandasYaModel.Menu.MenuFactory;
import viandasYaModel.Purchase.Purchase;
import viandasYaModel.User.Provider.Provider;
import viandasYaModel.User.Provider.ProviderFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PurchasesTests {

    @Test
    public void testAddMenu_addAMenuToAPurchase() throws MenuAmountConstraintException {

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu empanadas = MenuFactory.menuWithName("empanadas");
        Purchase order1 = new Purchase(pepePizas);

        pepePizas.addMenu(empanadas);
        order1.addMenu("empanadas", 6);

        assertTrue(order1.containsMenu("empanadas"));
        assertFalse(order1.containsMenu("pizza"));
    }

    @Test
    public void testRemoveMenu_removeAMenuFromAPurchase() throws MenuAmountConstraintException {

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu piza = MenuFactory.menuWithName("piza");
        Menu milanesa = MenuFactory.menuWithName("milanesa");
        Purchase order1 = new Purchase(pepePizas);

        pepePizas.addMenu(piza);
        pepePizas.addMenu(milanesa);
        order1.addMenu("piza", 2);
        order1.addMenu("milanesa", 1);

        //El pedido tiene piza y milanesa.
        assertTrue(order1.containsMenu("piza"));
        assertTrue(order1.containsMenu("milanesa"));

        order1.removeMenu("piza");

        //El pedido ya no tiene la piza.
        assertFalse(order1.containsMenu("piza"));

    }

    @Test(expected = NonexistentMenuException.class)
    public void testAddMenu_addANonExistentMenuToAPurchase() throws MenuAmountConstraintException {

        Provider pepePizas = ProviderFactory.pepePizzas();
        Menu piza = MenuFactory.menuWithName("piza");
        Purchase order1 = new Purchase(pepePizas);

        pepePizas.addMenu(piza);

        //Pido empanadas pero pepePizas solo tiene piza.
        order1.addMenu("empanada", 12);

    }

}
