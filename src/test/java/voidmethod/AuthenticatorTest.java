package voidmethod;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.doThrow;

public class AuthenticatorTest {

    @Test(expected = Exception.class)
    public void authenticateUser() throws Exception {
        AuthenticatorInterface authenticatorMock = Mockito.mock(AuthenticatorInterface.class);
        Authenticator authenticator = new Authenticator(authenticatorMock);

        String username = "Drezir";
        String password = "333";

        doThrow(new Exception())
            .when(authenticatorMock)
            .authenticateUser(username, password);

        authenticator.authenticate(username, password);

    }
}
