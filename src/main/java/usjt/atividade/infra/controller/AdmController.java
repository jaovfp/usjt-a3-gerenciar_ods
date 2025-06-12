package usjt.atividade.infra.controller;

import usjt.atividade.app.Adm.AdmService;
import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.User;

import java.util.List;
import java.util.Optional;

import static usjt.atividade.common.MessageConstants.INTERNAL_ERROR;

public class AdmController {

    private AdmService admService;

    public Response<List<User>> getAllUser (){
        try{
            return Response.created(MessageConstants.USER_CREATED_SUCCESS, admService.findAllUser());
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> getUserById(String userId){
        try{
            admService.findUserById(userId);
            return Response.created(MessageConstants.USER_CREATED_SUCCESS);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> deleteUserById(String userId){
        try{
            admService.deleteUserById(userId);
            return Response.created(MessageConstants.USER_DELETED_SUCCESS);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> updateUser(String userId, String fullname, String email, String password){
        try{
            Optional<User> Ouser = admService.findUserById(userId);
            User user = Ouser.get();
            admService.updateUser(user);
            return Response.created(MessageConstants.USER_UPDATED_SUCCESS);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> getAllEvents (){
        try{
            admService.findAllEvents();
            return Response.created("Eventos Listados com sucesso");
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> getAllEventsByStatus(int offset, int pageSize, String status) {
        try {
            admService.findAllEventsByStatus(offset, pageSize, status);
            return Response.created("Eventos listados por status com sucesso");
        } catch (ErrorException e) {
            return Response.fail(e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> getEventsByUserName(String name, int offset, int pageSize) {
        try {
            admService.findEventsByUserName(name, offset, pageSize);
            return Response.created("Eventos listados por nomes com sucesso");
        } catch (ErrorException e) {
            return Response.fail(e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

}
