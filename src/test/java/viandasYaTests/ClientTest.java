package viandasYaTests;

import static org.junit.Assert.*;
import org.junit.Test;
import viandasYaModel.Client.Client;
import viandasYaModel.Client.ClientFactory;
import viandasYaModel.Exceptions.InvalidPhoneNumberException;

public class ClientTest {

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
    public void testChangeNameTo_TheClientTomasHurrellChangesHisNameToPepitoHurrell() {
        Client client = ClientFactory.tomasHurrell();

        assertEquals("Tomas", client.name);
        assertEquals("Hurrell", client.lastname);

        client.changeNameTo("Pepito");

        assertEquals("Pepito", client.name);
    }

    @Test
    public void testUpdatePhoneNumberTo_TomasHurrellChangesHisPhoneNumberTo5491198765432To5491112345678() {
        Client client = ClientFactory.tomasHurrell();

        assertEquals("+5491198765432", client.phone);

        client.updatePhoneNumberTo("+5491112345678");

        assertEquals("+5491112345678", client.phone);
    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void testUpdatePhoneNumberTo_TomasHurrellCannotChangeHisPhoneNumberTo123BecauseItsAnInvalidNumber() {
        Client client = ClientFactory.tomasHurrell();

        assertEquals("+5491198765432", client.phone);

        client.updatePhoneNumberTo("123");
    }

//    @After
//    public void cleanUp() {
//
//    }

}
