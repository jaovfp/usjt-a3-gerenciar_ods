package usjt.atividade.app.ODS;

import usjt.atividade.app.Exceptions.NotFoundException;
import usjt.atividade.common.MessageConstants;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.infra.Repository.OdsRepositoryImpl;

import java.util.List;

public class OdsServiceImpl {

    private final OdsRepositoryImpl odsRepository;

    public OdsServiceImpl(){
        this.odsRepository = new OdsRepositoryImpl();
    }

    public List<ODS> getOdsTopics(){
        List<ODS> odsList = odsRepository.getAllOds();
        if (odsList.isEmpty()) {
            throw new NotFoundException(MessageConstants.ODS_TOPICS_NOT_FOUND);
        }
        return odsList;
    }

}
