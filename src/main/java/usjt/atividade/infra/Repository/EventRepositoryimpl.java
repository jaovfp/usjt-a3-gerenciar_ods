package usjt.atividade.infra.Repository;

import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.domain.repository.EventRepository;
import usjt.atividade.infra.config.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventRepositoryimpl  implements EventRepository {

    public List<MyEventsRequest> findAllEventRequestsByUserId(String userId, int offset, int pageSize) {
        List<MyEventsRequest> result = new ArrayList<>();

        final String sql =
                "SELECT er.request_id, er.event_name, er.event_description, er.status, o.ods_name, er.create_date " +
                        "FROM tbl_event_requests er " +
                        "JOIN tbl_ods_topics o ON er.ods_id = o.ods_id " +
                        "WHERE er.user_id = ? " +
                        "ORDER BY er.create_date DESC " +
                        "LIMIT ? OFFSET ?";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);
            stmt.setInt(2, pageSize);
            stmt.setInt(3, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MyEventsRequest request = new MyEventsRequest();
                    request.setRequestId(rs.getString("request_id"));
                    request.setEventName(rs.getString("event_name"));
                    request.setEventDescription(rs.getString("event_description"));
                    request.setStatus(rs.getString("status"));
                    request.setOdsName(rs.getString("ods_name"));
                    request.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());

                    result.add(request);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao buscar eventos do usuário com paginação", e);
        }
        return result;
    }

    public int countByUserId(UUID userId) {
        String sql = "SELECT COUNT(*) FROM tbl_event_requests WHERE user_id = ?";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setObject(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao contar eventos", e);
        }

        return 0;
    }

    public List<MyEventsRequest> findAllEventsByStatus (int offset, int pageSize, String status) {
        List<MyEventsRequest> result = new ArrayList<>();

        final String sql =
                "SELECT * " + "FROM tbl_event_requests er " +
                        "WHERE er.status = ? " +
                        "ORDER BY er.create_date DESC " +
                        "LIMIT ? OFFSET ?";


        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, pageSize );
            stmt.setInt(3, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MyEventsRequest request = new MyEventsRequest();
                    request.setRequestId(rs.getString("request_id"));
                    request.setEventName(rs.getString("event_name"));
                    request.setEventDescription(rs.getString("event_description"));
                    request.setStatus(rs.getString("status"));
                    request.setOdsName(rs.getString("ods_name"));
                    request.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());

                    result.add(request);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<MyEventsRequest> findAllEvents (int offset, int pageSize) {
        List<MyEventsRequest> result = new ArrayList<>();

        final String sql =
                "SELECT * " + "FROM tbl_event_requests er " +
                        "ORDER BY er.create_date DESC " +
                        "LIMIT ? OFFSET ?";


        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pageSize );
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    MyEventsRequest request = new MyEventsRequest();
                    request.setRequestId(rs.getString("request_id"));
                    request.setEventName(rs.getString("event_name"));
                    request.setEventDescription(rs.getString("event_description"));
                    request.setStatus(rs.getString("status"));
                    request.setOdsName(rs.getString("ods_name"));
                    request.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());

                    result.add(request);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        return result;
    }
}
