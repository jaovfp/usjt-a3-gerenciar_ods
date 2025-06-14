package usjt.atividade.infra.Repository;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.domain.repository.EventRequestRepository;
import usjt.atividade.domain.valueObjects.Address;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.infra.config.MySQLConnection;

import java.sql.*;
import java.util.*;

import static java.util.Objects.isNull;
import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;

public class EventRequestRepositoryImpl implements EventRequestRepository {

    private static final String BASE_SELECT =
            "SELECT " +
                    "er.request_id, er.event_name, er.event_description, er.status, " +
                    "o.ods_name, o.ods_id, o.ods_description, " +
                    "er.create_date, er.event_date, " +
                    "er.postal_code, er.city, er.address_line, er.state " +
                    "FROM tbl_event_requests er " +
                    "JOIN tbl_ods_topics o ON er.ods_id = o.ods_id " +
                    "WHERE 1=1 ";

    @Override
    public List<EventRequest> findAllEventRequestsByFilter(int offset, int pageSize, EventRequestFilter filter) {
        StringBuilder sql = new StringBuilder(BASE_SELECT);
        List<Object> params = buildFilter(filter, sql);

        sql.append(" ORDER BY er.create_date DESC LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add(offset);

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = getStatementWithParams(conn, sql.toString(), params);
             ResultSet rs = stmt.executeQuery()) {

            List<EventRequest> result = new ArrayList<>();
            while (rs.next()) {
                result.add(mapResultSetToEventRequest(rs, filter.getUserId()));
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar eventos do usuário com filtros e paginação", e);
        }
    }

    @Override
    public int countEventRequestByFilter(EventRequestFilter filter) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tbl_event_requests er WHERE 1=1");
        List<Object> params = buildFilter(filter, sql);

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = getStatementWithParams(conn, sql.toString(), params);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar eventos com filtros", e);
        }
    }

    private List<Object> buildFilter(EventRequestFilter filter, StringBuilder sql) {
        List<Object> params = new ArrayList<>();

        if (!isStringNullOrEmpty(filter.getUserId())) {
            sql.append(" AND er.user_id = ?");
            params.add(filter.getUserId());
        }

        if (!isStringNullOrEmpty(filter.getEventName())) {
            sql.append(" AND LOWER(er.event_name) LIKE ?");
            params.add("%" + filter.getEventName().toLowerCase() + "%");
        }

        if (!isNull(filter.getStatus())) {
            sql.append(" AND er.status = ?");
            params.add(filter.getStatus().toString());
        }

        return params;
    }

    private PreparedStatement getStatementWithParams(Connection conn, String sql, List<Object> params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            stmt.setObject(i + 1, params.get(i));
        }
        return stmt;
    }

    private EventRequest mapResultSetToEventRequest(ResultSet rs, String userId) throws SQLException {
        EventRequest request = new EventRequest();
        request.setRequestId(UUID.fromString(rs.getString("request_id")));
        request.setEventName(rs.getString("event_name"));
        request.setEventDescription(rs.getString("event_description"));
        request.setStatus(EventRequestStatus.valueOf(rs.getString("status")));
        request.setOds(new ODS(
                UUID.fromString(rs.getString("ods_id")),
                rs.getString("ods_name"),
                rs.getString("ods_description")
        ));
        request.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        request.setEventDate(rs.getDate("event_date").toLocalDate());
        request.setUserId(UUID.fromString(userId));
        request.setAddress(new Address(
                rs.getString("address_line"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("postal_code")
        ));
        return request;
    }
}
