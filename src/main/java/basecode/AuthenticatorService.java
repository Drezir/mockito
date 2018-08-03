package basecode;

public class AuthenticatorService {

    private AuthenticatorInterface authenticator;

    public AuthenticatorService(AuthenticatorInterface authenticator) {
        this.authenticator = authenticator;
    }

    public boolean authenticate(String username, String password) {
        authenticator.foo();
        return authenticator.authenticateUser(username, password);
    }
}
