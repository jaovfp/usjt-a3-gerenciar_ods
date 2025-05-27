package usjt.atividade.app.Authentication;

import org.mindrot.jbcrypt.BCrypt;
import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.app.Email.EmailService;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.app.User.UserRepositoryImpl;
import usjt.atividade.domain.model.PasswordRecovery;
import usjt.atividade.domain.model.User.User;

import java.time.LocalDateTime;
import java.util.UUID;

import static usjt.atividade.app.Authentication.AuthValidator.validateAuthenticate;
import static usjt.atividade.app.Authentication.AuthValidator.validatePasswordRecovery;
import static usjt.atividade.common.MessageConstants.EMAIL_NOT_FOUND;
import static usjt.atividade.common.MessageConstants.INVALID_AUTHENTICATE;
import static usjt.atividade.common.utils.CodeGenerator.generateNumericCode;

public class AuthServiceImpl implements AuthService {

    private final UserRepositoryImpl userRepository;
    private final PasswordRecoveryRepositoryImpl passowordRecoveryRepository;
    private static final int NUMBERS_DIGITS_PIN_CODE = 6;
    private final EmailService emailService;

    public AuthServiceImpl(){
        this.userRepository = new UserRepositoryImpl();
        this.passowordRecoveryRepository = new PasswordRecoveryRepositoryImpl();
        this.emailService = new EmailService();
    }

    public User authenticate(AuthenticateRequest request){

        validateAuthenticate(request);
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UnprocessableEntityException(INVALID_AUTHENTICATE));

        boolean passwordMatches = BCrypt.checkpw(request.getPassword(), user.getPasswordHash());

        if (!passwordMatches) {
            throw new UnprocessableEntityException(INVALID_AUTHENTICATE);
        }
        request.setPassword(null);

        return user;
    }

    public void requestPasswordRecovery(String email){
        validatePasswordRecovery(email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(EMAIL_NOT_FOUND));

        String pinCode = generateNumericCode(NUMBERS_DIGITS_PIN_CODE);

        PasswordRecovery recovery = new PasswordRecovery(
                UUID.randomUUID(),
                user.getUserId(),
                pinCode,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(2),
                false
        );

        passowordRecoveryRepository.save(recovery);
        emailService.sendRecoveryEmail(email,pinCode);
    }
}
