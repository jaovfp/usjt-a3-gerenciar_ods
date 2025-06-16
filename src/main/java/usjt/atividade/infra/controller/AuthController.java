package usjt.atividade.infra.controller;

import usjt.atividade.app.Authentication.AuthServiceImpl;
import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.app.Authentication.dto.ResetPasswordRequest;
import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.PasswordRecovery;
import usjt.atividade.domain.entities.User;

import static usjt.atividade.common.MessageConstants.*;

public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController() {
        this.authService = new AuthServiceImpl();
    }

    public Response<User> authenticate(AuthenticateRequest request){
        try{
            User user = authService.authenticate(request);
            return Response.ok(user);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<PasswordRecovery> requestPasswordReset(String email){
        try{
            PasswordRecovery passwordRecovery = authService.requestPasswordRecovery(email);
            return Response.created(RESET_PASSWORD_REQUEST_SUCCESS + email, passwordRecovery);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> resetPassword(ResetPasswordRequest request){
        try{
            authService.resetPassword(request);
            return Response.created(SUCCESS_UPDATE_PASSWORD);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

}
