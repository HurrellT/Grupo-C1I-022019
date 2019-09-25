package viandasYaTests.EmailTests;

import app.model.Email.Controller;
import app.model.Email.Email;
import app.model.Exceptions.InvalidEmailException;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EmailTests {

    @Test
    public void testEmailConstructor_emailForhurrelltomasAtGmailDotComSayingHello() {
        Email email = new Email();
        email.composeEmailWith("Hello", "hurrelltomas@gmail.com","Hello");

        assertEquals("viandasya.c1i@gmail.com", email.getUser());
        assertEquals("ktmlnpbqckpkbtcd", email.getPass());
        assertEquals("Hello", email.getSubject());
        assertEquals("hurrelltomas@gmail.com", email.getReceiver());
        assertEquals("Hello", email.getMessage());
    }

    @Test
    public void testSendEmail_sendingAnEmailTohurrelltomasAtGmailDotComSayingHello() {
        Email email = new Email();
        email.composeEmailWith("Hello", "hurrelltomas@gmail.com","Hello");

        Controller emailController = mock(Controller.class);

        emailController.sendMail(email);

        verify(emailController, times(1)).sendMail(email);
    }

    @Test(expected = InvalidEmailException.class)
    public void testComposeEmail_CannotComposeEmailBecauseOfAnInvalidEmail() {
        Email email = new Email();
        email.composeEmailWith("Hello", "hurrellgmail.com","Hello");
    }
}
