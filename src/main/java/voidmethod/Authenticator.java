package voidmethod;

public class Authenticator {

    private AuthenticatorInterface authenticator;

    public Authenticator(AuthenticatorInterface authenticator) {
        this.authenticator = authenticator;
    }

    public void authenticate(String username, String password) throws Exception {
        this.authenticator.authenticateUser(username, password);
    }
}
