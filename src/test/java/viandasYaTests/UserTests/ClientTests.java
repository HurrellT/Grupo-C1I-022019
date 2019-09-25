package viandasYaTests.UserTests;

import static org.junit.Assert.*;
import org.junit.Test;
import viandasYaModel.Menu.DeliveryType;
import viandasYaModel.Purchase.Purchase;
import viandasYaModel.User.Client.Client;
import viandasYaModel.User.Client.ClientFactory;
import viandasYaModel.Exceptions.InvalidEmailException;
import viandasYaModel.Exceptions.InvalidPhoneNumberException;
import viandasYaModel.User.Provider.Provider;
import viandasYaModel.User.Provider.ProviderFactory;

public class ClientTests {

//    @Before
//    public void setUp() {
//
//    }

    @Test
    public void testClientConstructor_IsNamedTomasHurrellWithAddressUnq123InStateBernalWithEmailhurrelltomasFromGmailAndPhone12345() {
        Client client = ClientFactory.tomasHurrell();

        assertEquals("Tomas", client.name);
        assertEquals("Hurrell", client.lastname);
        assertEquals("Unq 123", client.address);
        assertEquals("Bernal", client.state);
        assertEquals("hurrelltomas@gmail.com", client.email);
        assertEquals("+5491198765432", client.phone);
    }

    @Test
    public void testChangeLastnameTo_TheClientTomasHurrellChangesHisNameToTomasJarrel() {
        Client client = ClientFactory.tomasHurrell();

        assertEquals("Tomas", client.name);
        assertEquals("Hurrell", client.lastname);

        client.changeLastnameTo("Jarrel");

        assertEquals("Jarrel", client.lastname);
    }

    /*@Test
    public void testSendMails(){

        Client client = ClientFactory.federicoMartinez();
        Provider provider = ProviderFactory.pepePizzas();
        Purchase purchase = new Purchase(provider, DeliveryType.DELIVERY);

        client.makePurchase(purchase);

    }*/


//    @After
//    public void cleanUp() {
//
//    }

}
