package viandasYaTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import viandasYaModel.Client.Client;
import viandasYaModel.Client.ClientFactory;

public class ClientTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testClientConstructor_IsNamedTomasHurrellWithAddressUnq123InStateBernalWithEmailhurrelltomasFromGmailAndPhone12345() {
        Client client = ClientFactory.tomasHurrell();

        Assert.assertEquals(client.name, "Tomas");
        Assert.assertEquals(client.lastname, "Hurrell");
        Assert.assertEquals(client.address, "Unq 123");
        Assert.assertEquals(client.state, "Bernal");
        Assert.assertEquals(client.email, "hurrelltomas@gmail.com");
        Assert.assertEquals(client.phone, 12345);
    }


    @Test
    public void testChangeNameTo_TheClientTomasHurrellChangesHisNameToPepitoHurrell() {
        Client client = ClientFactory.tomasHurrell();

        Assert.assertEquals(client.name, "Tomas");
        Assert.assertEquals(client.lastname, "Hurrell");

        client.changeNameTo("Pepito");

        Assert.assertEquals(client.name, "Pepito");
    }

//    @After
//    public void cleanUp() {
//
//    }

}
