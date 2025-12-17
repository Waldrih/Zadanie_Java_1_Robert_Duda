package pl.wszib.edu.duda.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component //rejestracja klasy jako bean Springa
@RequiredArgsConstructor
public class Authenticator implements IAuthenticator{
    private final IUserRepository userRepository;

    @Override
    public boolean authenticate(String userName, String password) {
        return false;
    }
}
