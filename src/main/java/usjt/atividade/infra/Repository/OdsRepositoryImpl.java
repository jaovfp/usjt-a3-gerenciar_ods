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
import java.util.Optional;
import java.util.UUID;

public class OdsRepositoryImpl implements OdsRepository {


    private static final String SELECT_ALL_ODS = "SELECT ods_id, ods_name, ods_description FROM tbl_ods_topics";
    private static final String SELECT_BY_ID = "SELECT ods_id, ods_name, ods_description FROM tbl_ods_topics WHERE ods_id = ?";

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

    @Override
    public Optional<ODS> findById(UUID id) {
        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setString(1, id.toString());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UUID odsId = UUID.fromString(rs.getString("ods_id"));
                    String name = rs.getString("ods_name");
                    String description = rs.getString("ods_description");

                    return Optional.of(new ODS(odsId, name, description));
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar ODS por ID: " + e.getMessage());
        }

        return Optional.empty();
    }

}
