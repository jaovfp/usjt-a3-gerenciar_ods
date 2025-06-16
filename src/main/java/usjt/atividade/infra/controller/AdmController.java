package usjt.atividade.infra.controller;

import usjt.atividade.app.Adm.AdmService;
import usjt.atividade.app.Events.DTO.EventFilter;
import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.repository.EventRepository;
import usjt.atividade.domain.repository.UserRepository;
import usjt.atividade.infra.Repository.EventRepositoryImpl;
import usjt.atividade.infra.Repository.UserRepositoryImpl;

import java.util.List;
import java.util.Optional;

import static usjt.atividade.common.MessageConstants.INTERNAL_ERROR;

public class AdmController  {

    private AdmService admService;
    private UserRepository userRepository;
    private EventRepository eventRepository;

    public AdmController() {
        this.userRepository = new UserRepositoryImpl();
        this.eventRepository = new EventRepositoryImpl();
        this.admService = new AdmService(userRepository, eventRepository);
    }

    public Response<List<User>> getAllUser (int offset, int pageSize){
        try{
            List<User> user = admService.findAllUser(offset, pageSize);
            return Response.created(MessageConstants.USER_CREATED_SUCCESS, user);
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

    public Response<Void> getAllEvents (int offset, int pageSize, EventFilter filter){
        try{
            admService.findAllEvents(offset, pageSize, filter);
            return Response.created("Eventos Listados com sucesso");
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> getAllEventsByStatus(int offset, int pageSize, EventFilter filter) {
        try {
            admService.findAllEventsByStatus(offset, pageSize, filter);
            return Response.created("Eventos listados por status com sucesso");
        } catch (ErrorException e) {
            return Response.fail(e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

    public Response<Void> getEventsByUserName(String name, int offset, int pageSize, EventFilter filter) {
        try {
            admService.findEventsByUserName(name, offset, pageSize, filter);
            return Response.created("Eventos listados por nomes com sucesso");
        } catch (ErrorException e) {
            return Response.fail(e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

}
