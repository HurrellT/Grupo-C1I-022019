package viandasYaTests.UserTests;

import static org.junit.Assert.*;

import app.model.User.Client.Client;
import app.model.User.Client.ClientFactory;
import org.junit.Test;

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


//    @After
//    public void cleanUp() {
//
//    }

}
