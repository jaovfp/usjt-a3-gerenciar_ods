package usjt.atividade.app.Authentication;

import org.mindrot.jbcrypt.BCrypt;
import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.app.Authentication.dto.ResetPasswordRequest;
import usjt.atividade.domain.valueObjects.Email;
import usjt.atividade.domain.valueObjects.Password;
import usjt.atividade.domain.valueObjects.RecoveryPin;
import usjt.atividade.infra.Email.EmailService;
import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.app.Exceptions.UnprocessableEntityException;
import usjt.atividade.domain.service.AuthService;
import usjt.atividade.infra.Repository.PasswordRecoveryRepositoryImpl;
import usjt.atividade.infra.Repository.UserRepositoryImpl;
import usjt.atividade.domain.entities.PasswordRecovery;
import usjt.atividade.domain.entities.User;

import java.time.LocalDateTime;
import java.util.UUID;

import static usjt.atividade.app.Authentication.AuthRequestsValidator.*;
import static usjt.atividade.common.MessageConstants.*;
import static usjt.atividade.common.utils.CodeGenerator.generateNumericCode;
import static usjt.atividade.infra.security.PasswordHasher.hash;

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
        validateAuthenticateRequest(request);
        Email email = new Email(request.getEmail());
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnprocessableEntityException(INVALID_AUTHENTICATE));

        boolean passwordMatches = BCrypt.checkpw(request.getPassword(), user.getPasswordHash());

        if (!passwordMatches) {
            throw new UnprocessableEntityException(INVALID_AUTHENTICATE);
        }
        request.setPassword(null);

        return user;
    }

    public PasswordRecovery requestPasswordRecovery(String email){
        validatePasswordRecoveryRequest(email);
        Email objEmail = new Email(email);
        User user = userRepository.findByEmail(objEmail)
                .orElseThrow(() -> new NotFoundException(EMAIL_NOT_FOUND));

        String pinCode = generateNumericCode(NUMBERS_DIGITS_PIN_CODE);
        RecoveryPin recoveryPin = new RecoveryPin(pinCode);
        PasswordRecovery recovery = createPasswordRecovery(user,recoveryPin);
        emailService.sendRecoveryEmail(email, pinCode);
        return recovery;
    }

    public void resetPassword(ResetPasswordRequest request){
        validateResetPasswordRequest(request);

        PasswordRecovery recovery = passowordRecoveryRepository.findValidById(request.getRecoveryId())
                .filter(r -> r.getPin().getValue().equals(request.getPinCode()))
                .orElseThrow(() -> new UnprocessableEntityException(PIN_CODE_ERROR));

        User user = userRepository.findById(recovery.getUserId().toString())
                .orElseThrow(() -> new RuntimeException("Usuário vinculado ao id da recuperação de senha não existe."));

        updatePassword(request.getPassword(), user);
        passowordRecoveryRepository.invalidatePin(recovery.getRecoveryId());
    }

    private void updatePassword(String newPassword, User user){
        Password password = new Password(newPassword);
        user.setPasswordHash(hash(password.getValue()));
        password = null;
        userRepository.update(user);
    }

    private PasswordRecovery createPasswordRecovery(User user,RecoveryPin recoveryPin){
        PasswordRecovery recovery = new PasswordRecovery(
                UUID.randomUUID(),
                user.getUserId(),
                recoveryPin,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(3),
                false
        );
        passowordRecoveryRepository.save(recovery);
        return recovery;
    }
}
