package basecode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class) // or Mockito.initMocks(AuthenticatorTest.class)
public class AuthenticatorTest {

    @Mock
    private AuthenticatorInterface authenticatorMock; // needs MockitoJUnitRunner

    @InjectMocks
    private AuthenticatorService authenticator; // inject @Mock dependencies

    @Test
    public void testAuthentication() {
        AuthenticatorInterface authenticatorMock;
        AuthenticatorService authenticator;
        authenticatorMock = Mockito.mock(AuthenticatorInterface.class);
        authenticator = new AuthenticatorService(authenticatorMock);

        String username = "Drezir";
        String password = "333";

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

    @Test
    public void testAuthenticationMockInjection() {
        String username = "Drezir";
        String password = "333";

        when(authenticatorMock.authenticateUser(username, password))
            .thenReturn(true);
        boolean actual = authenticator.authenticate(username, password);

        assertTrue(actual);
    }
}
