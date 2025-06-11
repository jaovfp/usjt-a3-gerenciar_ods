package usjt.atividade.infra.controller;

import usjt.atividade.app.Adm.AdmService;
import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;

import static usjt.atividade.common.MessageConstants.INTERNAL_ERROR;

public class AdmController {

    private AdmService admService;

    public Response<Void> getAllUser (){
        try{
            admService.findAllUser();
            return Response.created(MessageConstants.USER_CREATED_SUCCESS);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }
}
