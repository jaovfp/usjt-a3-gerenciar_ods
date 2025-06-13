package usjt.atividade.common.utils;

import usjt.atividade.common.MessageConstants;
import usjt.atividade.domain.entities.ODS;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class ODSUtils {

    public static List<String> getArrayOdsTopics(List<ODS> odsList) {
        if (isNull(odsList) || odsList.isEmpty()) {
            return List.of(MessageConstants.ODS_TOPICS_NOT_FOUND);
        }
        return odsList.stream()
                .map(ODS::getOdsName)
                .collect(Collectors.toList());
    }
}
