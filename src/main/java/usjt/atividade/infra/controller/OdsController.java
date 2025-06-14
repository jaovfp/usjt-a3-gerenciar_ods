package usjt.atividade.infra.controller;

import usjt.atividade.app.Exceptions.ErrorException;
import usjt.atividade.app.ODS.OdsServiceImpl;
import usjt.atividade.common.Response;
import usjt.atividade.common.StatusCode;
import usjt.atividade.domain.entities.ODS;

import java.util.List;

import static usjt.atividade.common.MessageConstants.INTERNAL_ERROR;

public class OdsController {

    private final OdsServiceImpl odsService;

    public OdsController(){
        this.odsService = new OdsServiceImpl();
    }

    public Response<List<ODS>> getOdsTopics (){
        try{
            List<ODS> odsTopics = odsService.getOdsTopics();
            return Response.ok(odsTopics);
        }catch (ErrorException e){
            return Response.fail(e.getStatusCode(), e.getMessage());
        }catch (Exception e){
            System.out.println(e);
            return Response.fail(StatusCode.INTERNAL_ERROR, INTERNAL_ERROR);
        }
    }

}
