package pl.wszib.edu.duda.authentication;

public interface IAuthenticator {
    boolean authenticate(String userName, String password);
}
