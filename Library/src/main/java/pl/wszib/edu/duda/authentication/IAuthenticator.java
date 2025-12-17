package pl.wszib.edu.duda.authentication;

//definiuje autoryzacje użytkownika
public interface IAuthenticator {
    boolean authenticate(String userName, String password);
}
