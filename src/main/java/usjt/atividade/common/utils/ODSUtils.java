package usjt.atividade.common.utils;

import usjt.atividade.common.MessageConstants;
import usjt.atividade.domain.entities.ODS;

import javax.swing.*;
import java.util.List;
import java.util.UUID;
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

    public static UUID getOdsIdByName(List<ODS> odsList, String name) {
        if (odsList == null || name == null) {
            return null;
        }

        return odsList.stream()
                .filter(ods -> name.equalsIgnoreCase(ods.getOdsName()))
                .map(ODS::getOdsId)
                .findFirst()
                .orElse(null);
    }

    public static String getSelectedOdsOrNull(JComboBox<String> comboBox) {
        String selected = (String) comboBox.getSelectedItem();
        return "Selecione uma ODS".equals(selected) ? null : selected;
    }
}
