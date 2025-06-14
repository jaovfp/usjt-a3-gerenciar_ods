package usjt.atividade.infra.Repository;

import usjt.atividade.domain.entities.ODS;
import usjt.atividade.domain.repository.OdsRepository;
import usjt.atividade.infra.config.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OdsRepositoryImpl implements OdsRepository {


    private static final String SELECT_ALL_ODS = "SELECT ods_id, ods_name, ods_description FROM tbl_ods_topics";

    @Override
    public List<ODS> getAllOds() {
        List<ODS> odsList = new ArrayList<>();

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_ODS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("ods_id"));
                String name = rs.getString("ods_name");
                String description = rs.getString("ods_description");

                odsList.add(new ODS(id, name, description));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return odsList;
    }

}
