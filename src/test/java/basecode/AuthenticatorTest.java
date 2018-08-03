package basecode;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class AuthenticatorTest {

    @Test
    public void testAuthentication() {
        AuthenticatorInterface authenticatorMock;
        AuthenticatorService authenticator;
        String username = "Drezir";
        String password = "333";

        authenticatorMock = Mockito.mock(AuthenticatorInterface.class);
        authenticator = new AuthenticatorService(authenticatorMock);

        when(authenticatorMock.authenticateUser(username, password)).thenReturn(false);

        // verification type 1
        boolean actual = authenticator.authenticate(username, password);
        assertFalse(actual);
        // verification type 2
        verify(authenticatorMock, times(1)).authenticateUser(username, password);
        verify(authenticatorMock, atLeastOnce()).authenticateUser(username, password);
        verify(authenticatorMock, atLeast(1)).authenticateUser(username, password);
        verify(authenticatorMock, atMost(1)).authenticateUser(username, password);
        //verify(authenticatorMock, never()).authenticateUser(username, password); // fail

        // check method call order
        InOrder inOrder = inOrder(authenticatorMock);
        inOrder.verify(authenticatorMock).foo();
        inOrder.verify(authenticatorMock).authenticateUser(username, password);

        // check timeout
        //verify(authenticatorMock, timeout(1000).times(1)).authenticateUser(username,password);
    }

    @Test
    public void testEmptyCredentials() {
        AuthenticatorInterface authenticatorMock = mock(AuthenticatorInterface.class);
        AuthenticatorService authenticatorService = new AuthenticatorService(authenticatorMock);

        when(authenticatorMock.authenticateUser("",""))
                .thenThrow(new EmptyCredentialsException());

        authenticatorService.authenticate("", "");
    }
}
