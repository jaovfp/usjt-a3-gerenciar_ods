package usjt.atividade.infra.Repository;

import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.infra.config.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdmRepository {

    public List<MyEventsRequest> findAllEventsPendingAprovation (int offset, int pageSize) {
        List<MyEventsRequest> result = new ArrayList<>();

        final String sql =
                "SELECT * " + "FROM tbl_event_requests er " +
                        "WHERE er.status = ? " +
                        "ORDER BY er.create_date DESC " +
                        "LIMIT ? OFFSET ?";


        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "PENDING");
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

    public List<MyEventsRequest> findEventsByUserId (String Id, int offset, int pageSize){
        List<MyEventsRequest> result = new ArrayList<>();

        final String sql =
                "SELECT * " + "FROM tbl_event_requests er " +
                        "WHERE er.user_id = ? " +
                        "ORDER BY er.create_date DESC " +
                        "LIMIT ? OFFSET ?";


        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, Id);
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
}



