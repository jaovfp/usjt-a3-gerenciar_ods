package usjt.atividade.infra.Repository;

import usjt.atividade.app.Events.DTO.MyEventRequestFilter;
import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.infra.config.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;

public class EventRepository {

    public List<MyEventsRequest> findAllEventRequestsByUserIdAndFilter(
            String userId,
            int offset,
            int pageSize,
            MyEventRequestFilter filter) {

        List<MyEventsRequest> result = new ArrayList<>();

        StringBuilder sql = new StringBuilder(
                "SELECT er.request_id, er.event_name, er.event_description, er.status, o.ods_name, er.create_date " +
                        "FROM tbl_event_requests er " +
                        "JOIN tbl_ods_topics o ON er.ods_id = o.ods_id " +
                        "WHERE er.user_id = ? "
        );

        List<Object> parameters = new ArrayList<>();
        parameters.add(userId);

        if (!isStringNullOrEmpty(filter.getEventName())) {
            sql.append(" AND LOWER(er.event_name) LIKE ? ");
            parameters.add("%" + filter.getEventName().toLowerCase() + "%");
        }

        if (!isNull(filter.getStatus())) {
            sql.append(" AND er.status = ? ");
            parameters.add(filter.getStatus().toString());
        }

        sql.append(" ORDER BY er.create_date DESC ");
        sql.append(" LIMIT ? OFFSET ? ");

        parameters.add(pageSize);
        parameters.add(offset);

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }

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
            throw new RuntimeException("Erro ao buscar eventos do usuário com filtros e paginação", e);
        }
        return result;
    }

    public int countByUserIdAndFilter(String userId, MyEventRequestFilter filter) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tbl_event_requests WHERE user_id = ?");
        List<Object> parameters = new ArrayList<>();
        parameters.add(userId);

        if (!isStringNullOrEmpty(filter.getEventName())) {
            sql.append(" AND LOWER(event_name) LIKE ?");
            parameters.add("%" + filter.getEventName().toLowerCase() + "%");
        }

        if (!isNull(filter.getStatus())) {
            sql.append(" AND status = ?");
            parameters.add(filter.getStatus().toString());
        }
        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao contar eventos com filtros", e);
        }
        return 0;
    }

}
