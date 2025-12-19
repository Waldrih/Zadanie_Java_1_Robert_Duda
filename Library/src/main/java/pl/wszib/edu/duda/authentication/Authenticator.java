package pl.wszib.edu.duda.authentication;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.wszib.edu.duda.database.IUserRepository;
import pl.wszib.edu.duda.model.User;

@Component //rejestracja klasy jako bean Springa
@RequiredArgsConstructor
public class Authenticator implements IAuthenticator{
    private final IUserRepository userRepository;

    @Override
    public boolean authenticate(String userName, String password) {
        User user = this.userRepository.findUserByLogin(userName);
        if(user != null && user.getPassword().equals(DigestUtils.md5Hex(password))) {
            return true;
        }
        return false;
    }
}
