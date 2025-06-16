package usjt.atividade.domain.repository;

import usjt.atividade.domain.entities.ODS;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OdsRepository {

    List<ODS> getAllOds();
    Optional<ODS> findById(UUID id);

}
