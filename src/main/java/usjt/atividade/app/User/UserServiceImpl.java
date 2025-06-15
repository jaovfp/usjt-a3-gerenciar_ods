package usjt.atividade.app.User;

import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.app.User.dto.requests.CreateUserRequest;
import usjt.atividade.app.User.dto.requests.UpdateUserRequest;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.domain.valueObjects.*;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.service.UserService;
import usjt.atividade.domain.repository.UserRepository;
import usjt.atividade.infra.Repository.UserRepositoryImpl;

import java.util.UUID;

import static usjt.atividade.app.User.UserValidator.*;
import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;
import static usjt.atividade.infra.security.PasswordHasher.hash;

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
        user.setPasswordHash(hash(new Password(request.getPassword()).getValue()));
        user.setType(UserType.NORMAL);
        user.setActive(true);
        return user;
    }

    public User update(UpdateUserRequest request) {
        validateUpdateUserRequestIsNull(request);

        User existingUser = getUserById(request.getUserId());

        validateEmailUniqueness(request.getEmail(), existingUser.getUserId());

        updateUserData(existingUser, request);

        userRepository.update(existingUser);
        return existingUser;
    }

    private User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(MessageConstants.USER_NOT_FOUND));
    }

    private void validateEmailUniqueness(String emailStr, UUID currentUserId) {
        Email email = new Email(emailStr);
        userRepository.findByEmail(email).ifPresent(userWithSameEmail -> {
            if (!userWithSameEmail.getUserId().equals(currentUserId)) {
                throw new UnprocessableEntityException(MessageConstants.ERROR_EMAIL_ALREADY_EXISTS);
            }
        });
    }

    private void updateUserData(User user, UpdateUserRequest request) {
        user.setFullname(request.getFullname());
        user.setEmail(new Email(request.getEmail()));
        user.setBirthDate(request.getBirthDate());
        user.setCpf(new CPF(request.getCpf()));
        user.setPhoneNumber(new PhoneNumber(request.getPhoneNumber()));

        Address address = new Address(
                request.getAddressLine(),
                request.getCity(),
                request.getState(),
                request.getPostalCode()
        );
        user.setAddress(address);
        user.setActive(request.getIsActive());
        user.setProfileComplete(true);

        if (!isStringNullOrEmpty(request.getProfilePhotoUrl())) {
            user.setProfilePhotoUrl(request.getProfilePhotoUrl());
        }
    }

}
