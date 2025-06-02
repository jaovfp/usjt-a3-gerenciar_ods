package usjt.atividade.app.User;

import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.app.User.dto.requests.CreateUserRequest;
import usjt.atividade.common.utils.PasswordHasher;
import usjt.atividade.domain.model.User.User;
import usjt.atividade.domain.model.User.UserType;

import java.util.UUID;

import static usjt.atividade.app.User.UserValidator.validateCreate;
import static usjt.atividade.common.MessageConstants.ERROR_EMAIL_ALREADY_EXISTS;

public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;

    public UserServiceImpl(){
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public void create (CreateUserRequest request){
        validateCreate(request);
        validateUserExists(request);
        User newUser = createNormalUser(request);
        userRepository.save(newUser);
    }

    private void validateUserExists(CreateUserRequest request){
        String email = request.getEmail();
        if (userRepository.existsByEmail(email)){
            throw new UnprocessableEntityException(ERROR_EMAIL_ALREADY_EXISTS);
        }
    }

    private User createNormalUser(CreateUserRequest request){
        User user = new User(
                UUID.randomUUID(),
                request.getFullname(),
                request.getEmail(),
                PasswordHasher.hash(request.getPassword()),
                UserType.NORMAL,
                true,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        request.setPassword(null);
        return user;
    }


}
