package usjt.atividade.infra.Repository;

import usjt.atividade.app.Events.DTO.EventSubscribeFilter;
import usjt.atividade.domain.entities.Event;
import usjt.atividade.domain.entities.EventSubscribe;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.repository.EventSubscribeRepository;
import usjt.atividade.infra.config.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;

public class EventSubscribeRepositoryImpl implements EventSubscribeRepository {

    private static final String BASE_SELECT =
            "SELECT " +
                    "r.registration_id, r.registration_date, " +
                    "e.event_id, e.event_name, e.event_description, e.event_date, " +
                    "e.address_line, e.city, e.state, e.postal_code, " +
                    "o.ods_id, o.ods_name, o.ods_description, " +
                    "u.user_id, u.full_name " +
                    "cu.user_id AS creator_id, cu.full_name AS creator_name " +
                    "FROM tbl_event_registrations r " +
                    "JOIN tbl_events e ON r.event_id = e.event_id " +
                    "JOIN tbl_users u ON r.user_id = u.user_id " +
                    "JOIN tbl_ods_topics o ON e.ods_id = o.ods_id " +
                    "JOIN tbl_users cu ON e.created_by = cu.user_id " +
                    "WHERE 1=1 ";

    @Override
    public List<EventSubscribe> findAllEventSubscribesByFilter(int offset, int pageSize, EventSubscribeFilter filter) {
        StringBuilder sql = new StringBuilder(BASE_SELECT);
        List<Object> params = buildFilter(filter, sql);

        sql.append(" ORDER BY r.registration_date DESC LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add(offset);

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = getStatementWithParams(conn, sql.toString(), params);
             ResultSet rs = stmt.executeQuery()) {

            List<EventSubscribe> result = new ArrayList<>();
            while (rs.next()) {
                result.add(mapResultSetToEventSubscribe(rs));
            }
            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar inscrições em eventos com filtros", e);
        }
    }

    @Override
    public int countEventSubscribesByFilter(EventSubscribeFilter filter) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) " +
                "FROM tbl_event_registrations r " +
                "JOIN tbl_events e ON r.event_id = e.event_id " +
                "WHERE 1=1 ");
        List<Object> params = buildFilter(filter, sql);

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = getStatementWithParams(conn, sql.toString(), params);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar inscrições com filtros", e);
        }
    }

    private List<Object> buildFilter(EventSubscribeFilter filter, StringBuilder sql) {
        List<Object> params = new ArrayList<>();

        if (!isStringNullOrEmpty(filter.getUserId())) {
            sql.append(" AND r.user_id = ?");
            params.add(filter.getUserId());
        }

        if (!isStringNullOrEmpty(filter.getSubscribeId())) {
            sql.append(" AND r.registration_id = ?");
            params.add(filter.getSubscribeId());
        }

        if (!isStringNullOrEmpty(filter.getEventName())) {
            sql.append(" AND LOWER(e.event_name) LIKE ?");
            params.add("%" + filter.getEventName().toLowerCase() + "%");
        }

        if (!isStringNullOrEmpty(filter.getOdsId())) {
            sql.append(" AND e.ods_id = ?");
            params.add(filter.getOdsId());
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

    private EventSubscribe mapResultSetToEventSubscribe(ResultSet rs) throws SQLException {
        Event event = new Event();
        event.setEventId(rs.getString("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setEventDescription(rs.getString("event_description"));
        event.setEventDate(rs.getDate("event_date").toLocalDate());
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
        event.setCreatedBy(creator);

        User user = new User();
        user.setUserId(UUID.fromString(rs.getString("user_id")));
        user.setFullName(rs.getString("full_name"));

        EventSubscribe subscribe = new EventSubscribe();
        subscribe.setSubscribeId(rs.getString("registration_id"));
        subscribe.setRegistrationDate(rs.getTimestamp("registration_date").toLocalDateTime());
        subscribe.setEvent(event);
        subscribe.setUser(user);

        return subscribe;
    }

}
