package usjt.atividade.app.Authentication;

import org.mindrot.jbcrypt.BCrypt;
import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.app.User.UserRepositoryImpl;
import usjt.atividade.domain.model.User.User;

import static java.util.Objects.isNull;
import static usjt.atividade.app.Authentication.AuthValidator.validateAuthenticate;
import static usjt.atividade.common.MessageConstants.GENERIC_AUTHENTICATE_ERROR;
import static usjt.atividade.common.MessageConstants.INVALID_AUTHENTICATE;

public class AuthServiceImpl implements AuthService {

    private final UserRepositoryImpl userRepository;

    public AuthServiceImpl(){
        this.userRepository = new UserRepositoryImpl();
    }

    public User authenticate(AuthenticateRequest request){

        validateAuthenticate(request);
        User user = userRepository.findByEmail(request.getEmail());
        if (isNull(user)) {
            throw new UnprocessableEntityException(GENERIC_AUTHENTICATE_ERROR + INVALID_AUTHENTICATE);
        }

        boolean passwordMatches = BCrypt.checkpw(request.getPassword(), user.getPasswordHash());

        if (!passwordMatches) {
            throw new UnprocessableEntityException(GENERIC_AUTHENTICATE_ERROR + INVALID_AUTHENTICATE);
        }
        request.setPassword(null);

        return user;

    }

}
