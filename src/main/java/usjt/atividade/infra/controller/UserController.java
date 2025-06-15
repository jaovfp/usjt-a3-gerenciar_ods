package usjt.atividade.infra.controller;

import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.app.User.UserServiceImpl;
import usjt.atividade.app.User.dto.requests.CreateUserRequest;
import usjt.atividade.app.User.dto.requests.UpdateUserRequest;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.User;

import static usjt.atividade.common.MessageConstants.INTERNAL_ERROR;

public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController() {
        this.userServiceImpl = new UserServiceImpl();
    }

    public Response<Void> createUser(CreateUserRequest request){
        try{
            userServiceImpl.create(request);
            return Response.created(MessageConstants.USER_CREATED_SUCCESS);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<User> updateUser(UpdateUserRequest request){
        try{
            User newUser = userServiceImpl.update(request);
            return Response.ok(MessageConstants.USER_UPDATED_SUCCESS, newUser);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }
}
