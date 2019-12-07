package viandasYaTests.UserTests;

import static org.junit.Assert.*;

import app.model.User.Client.Client;
import app.model.User.Client.ClientFactory;
import app.model.User.Provider.Provider;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

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

    @Test
    public void testAClientCanBeConvertedToProvider() {
        Client client = ClientFactory.tomasHurrell();
        LocalTime officeHoursFrom = LocalTime.of(9,0);
        LocalTime officeHoursTo = LocalTime.of(18,0);
        DayOfWeek officeDaysFrom = DayOfWeek.MONDAY;
        DayOfWeek officeDaysTo = DayOfWeek.FRIDAY;
        Provider provider = client.convertToProvider(
                "logo",
                1,2,"desc",
                "website", officeHoursFrom,
                officeHoursTo, officeDaysFrom,
                officeDaysTo, new ArrayList<>(),false);

        assertEquals(Provider.class, provider.getClass());
        assertEquals("logo", provider.logo);
    }

//    @After
//    public void cleanUp() {
//
//    }

}
