package viandasYaTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import viandasYaModel.Client;

public class ClientTest {

    @Before
    public void setUp() {

    }

    @Test
    public void testClientIsNamedTomasHurrell() {
        Client client = new Client("Tomas", "Hurrell");

        Assert.assertEquals(client.name, "Tomas");
        Assert.assertEquals(client.lastname, "Hurrell");
    }

//    @After
//    public void cleanUp() {
//
//    }

}
