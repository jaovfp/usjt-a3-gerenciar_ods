package usjt.atividade.app.Authentication;

import usjt.atividade.app.Authentication.dto.AuthenticateRequest;
import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.model.User.User;

import static usjt.atividade.common.MessageConstants.INTERNAL_ERROR;
import static usjt.atividade.common.MessageConstants.RESET_PASSWORD_REQUEST_SUCCESS;

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

    public Response<Void> requestPasswordReset(String email){
        try{
            authService.requestPasswordRecovery(email);
            return Response.created(RESET_PASSWORD_REQUEST_SUCCESS + email);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

}
