package usjt.atividade.app.User;

import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.app.User.dto.requests.CreateUserRequest;
import usjt.atividade.domain.valueObjects.Email;
import usjt.atividade.domain.valueObjects.Password;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.service.UserService;
import usjt.atividade.domain.valueObjects.UserType;
import usjt.atividade.domain.repository.UserRepository;
import usjt.atividade.infra.Repository.UserRepositoryImpl;

import java.util.UUID;

import static usjt.atividade.app.User.UserValidator.validateCreateRequestIsNull;
import static usjt.atividade.app.User.UserValidator.validateUserExists;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(){
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public void create (CreateUserRequest request){
        validateCreateRequestIsNull(request);
        Email email = new Email(request.getEmail());
        boolean userExists = userRepository.existsByEmail(email);
        validateUserExists(userExists);
        User newUser = createNormalUser(request);
        userRepository.save(newUser);
    }

    private User createNormalUser(CreateUserRequest request){
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setFullname(request.getFullname());
        user.setEmail(new Email(request.getEmail()));
        user.setPasswordHash(new Password(request.getPassword()).getValue());
        user.setType(UserType.NORMAL);
        user.setActive(true);
        return user;
    }
}
