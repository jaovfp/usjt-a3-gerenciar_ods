package usjt.atividade.infra.Repository;

import usjt.atividade.domain.entities.Event;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.repository.EventRepository;
import usjt.atividade.domain.valueObjects.Email;
import usjt.atividade.app.Events.DTO.EventFilter;
import usjt.atividade.infra.config.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;

public class EventRepositoryImpl implements EventRepository {


    private static final String BASE_SELECT =
            "SELECT " +
                    "e.event_id, e.event_name, e.event_description, e.event_date, " +
                    "e.address_line, e.city, e.state, e.postal_code, e.create_date, " +
                    "o.ods_id, o.ods_name, o.ods_description, " +
                    "u.user_id AS creator_id, u.full_name AS creator_name, u.email AS creator_email " +
                    "FROM tbl_events e " +
                    "JOIN tbl_ods_topics o ON e.ods_id = o.ods_id " +
                    "JOIN tbl_users u ON e.created_by = u.user_id " +
                    "WHERE 1=1 ";

    @Override
    public List<Event> findAllEventsByFilter(int offset, int pageSize, EventFilter filter) {
        StringBuilder sql = new StringBuilder(BASE_SELECT);
        List<Object> params = buildFilter(filter, sql);

        sql.append(" ORDER BY e.create_date DESC LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add(offset);

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = getStatementWithParams(conn, sql.toString(), params);
             ResultSet rs = stmt.executeQuery()) {

            List<Event> result = new ArrayList<>();
            while (rs.next()) {
                result.add(mapResultSetToEvent(rs));
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar eventos com filtros", e);
        }
    }

    @Override
    public int countEventsByFilter(EventFilter filter) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tbl_events e WHERE 1=1 ");
        List<Object> params = buildFilter(filter, sql);

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = getStatementWithParams(conn, sql.toString(), params);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar eventos com filtros", e);
        }
    }

    private List<Object> buildFilter(EventFilter filter, StringBuilder sql) {
        List<Object> params = new ArrayList<>();

        if (!isStringNullOrEmpty(filter.getEventName())) {
            sql.append(" AND LOWER(e.event_name) LIKE ?");
            params.add("%" + filter.getEventName().toLowerCase() + "%");
        }

        if (!isStringNullOrEmpty(filter.getOdsId())) {
            sql.append(" AND e.ods_id = ?");
            params.add(filter.getOdsId());
        }

        if (!isStringNullOrEmpty(filter.getCreatorId())) {
            sql.append(" AND e.created_by = ?");
            params.add(filter.getCreatorId());
        }

        if (Boolean.TRUE.equals(filter.getBeforeToday())) {
            sql.append(" AND e.create_date < CURRENT_DATE");
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

    private Event mapResultSetToEvent(ResultSet rs) throws SQLException {
        Event event = new Event();
        event.setEventId(rs.getString("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setEventDescription(rs.getString("event_description"));
        event.setEventDate(rs.getDate("event_date").toLocalDate());
        event.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());

        event.setAddressLine(rs.getString("address_line"));
        event.setCity(rs.getString("city"));
        event.setState(rs.getString("state"));
        event.setPostalCode(rs.getString("postal_code"));

        ODS ods = new ODS(
                UUID.fromString(rs.getString("ods_id")),
                rs.getString("ods_name"),
                rs.getString("ods_description")
        );
        event.setOds(ods);

        User creator = new User();
        creator.setUserId(UUID.fromString(rs.getString("creator_id")));
        creator.setFullName(rs.getString("creator_name"));
        creator.setEmail(new Email(rs.getString("creator_email")));
        event.setCreatedBy(creator);

        return event;
    }

}
