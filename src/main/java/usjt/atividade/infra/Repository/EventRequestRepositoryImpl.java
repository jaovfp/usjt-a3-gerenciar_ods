package usjt.atividade.infra.Repository;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.EventRequest;
import usjt.atividade.domain.entities.ODS;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.repository.EventRequestRepository;
import usjt.atividade.domain.valueObjects.Address;
import usjt.atividade.domain.valueObjects.Email;
import usjt.atividade.domain.valueObjects.EventRequestStatus;
import usjt.atividade.domain.valueObjects.UserType;
import usjt.atividade.infra.config.MySQLConnection;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static java.util.Objects.isNull;
import static usjt.atividade.common.utils.ValidatorUtils.isStringNullOrEmpty;

public class EventRequestRepositoryImpl implements EventRequestRepository {

    private static final String BASE_SELECT =
            "SELECT " +
                    "er.request_id, er.event_name, er.event_description, er.status, " +
                    "o.ods_name, o.ods_id, o.ods_description, " +
                    "er.create_date, er.event_date, " +
                    "er.postal_code, er.city, er.address_line, er.state, " +
                    "u.user_id AS creator_id, u.fullname AS creator_name, u.email AS creator_email, " +
                    "u.cpf AS creator_cpf, u.phone_number AS creator_phone, " +
                    "u.type AS creator_type, u.is_active AS creator_active " +
                    "FROM tbl_event_requests er " +
                    "JOIN tbl_ods_topics o ON er.ods_id = o.ods_id " +
                    "JOIN tbl_users u ON er.user_id = u.user_id " +
                    "WHERE 1=1 ";

    private static final String INSERT_EVENT_REQUEST =
            "INSERT INTO tbl_event_requests " +
                    "(request_id, event_name, event_description, status, ods_id, user_id, create_date, event_date, " +
                    "postal_code, city, address_line, state) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String EXISTS_PENDING_OR_APPROVED_BY_USER_AND_DATE =
            "SELECT 1 FROM tbl_event_requests WHERE user_id = ? AND event_date = ? AND status IN ('PENDING', 'APPROVED') LIMIT 1";

    private static final String BASE_COUNT =
            "SELECT COUNT(*) " +
                    "FROM tbl_event_requests er " +
                    "JOIN tbl_ods_topics o ON er.ods_id = o.ods_id " +
                    "JOIN tbl_users u ON er.user_id = u.user_id " +
                    "WHERE 1=1";

    private static final String UPDATE_EVENT_REQUEST_STATUS =
            "UPDATE tbl_event_requests SET status = ? WHERE request_id = ?";

    private static final String UPDATE_EVENT_REQUEST =
            "UPDATE tbl_event_requests SET " +
                    "event_name = ?, " +
                    "event_description = ?, " +
                    "status = ?, " +
                    "event_date = ?, " +
                    "postal_code = ?, " +
                    "city = ?, " +
                    "address_line = ?, " +
                    "state = ? " +
                    "WHERE request_id = ?";

    @Override
    public void update(EventRequest request) {
        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_EVENT_REQUEST)) {
            setCommonParams(stmt, request, false);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar solicitação de evento: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar solicitação de evento", e);
        }
    }

    @Override
    public void save(EventRequest request) {
        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(INSERT_EVENT_REQUEST)) {
            stmt.setString(1, request.getRequestId().toString());
            setCommonParams(stmt, request, true);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao salvar solicitação de evento: " + e.getMessage());
            throw new RuntimeException("Erro ao salvar solicitação de evento", e);
        }
    }

    @Override
    public boolean existsPendingOrApprovedByUserIdAndEventDate(UUID userId, LocalDate eventDate) {
        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(EXISTS_PENDING_OR_APPROVED_BY_USER_AND_DATE)) {

            stmt.setString(1, userId.toString());
            stmt.setDate(2, Date.valueOf(eventDate));

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar evento pendente ou aprovado para o usuário na data", e);
        }
    }

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
            throw new RuntimeException("Erro ao buscar solicitações de eventos com filtros e paginação", e);
        }
    }

    @Override
    public int countEventRequestByFilter(EventRequestFilter filter) {
        StringBuilder sql = new StringBuilder(BASE_COUNT);
        List<Object> params = buildFilter(filter, sql);

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = getStatementWithParams(conn, sql.toString(), params);
             ResultSet rs = stmt.executeQuery()) {

            return rs.next() ? rs.getInt(1) : 0;

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException("Erro ao contar eventos com filtros", e);
        }
    }

    private List<Object> buildFilter(EventRequestFilter filter, StringBuilder sql) {
        List<Object> params = new ArrayList<>();

        if (!isStringNullOrEmpty(filter.getRequestId())) {
            sql.append(" AND er.request_id = ?");
            params.add(filter.getRequestId());
        }

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

        if (!isStringNullOrEmpty(filter.getCreatorEmail())) {
            sql.append(" AND LOWER(u.email) LIKE ?");
            params.add("%" + filter.getCreatorEmail().toLowerCase() + "%");
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

        User creator = new User();
        creator.setUserId(UUID.fromString(rs.getString("creator_id")));
        creator.setFullname(rs.getString("creator_name"));
        creator.setEmail(new Email(rs.getString("creator_email")));
        creator.setCpf(rs.getString("creator_cpf"));
        creator.setPhoneNumber(rs.getString("creator_phone"));
        creator.setType(UserType.valueOf(rs.getString("creator_type")));
        creator.setActive(rs.getBoolean("creator_active"));

        request.setRequestedBy(creator);

        request.setAddress(new Address(
                rs.getString("address_line"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("postal_code")
        ));

        return request;
    }

    private void setCommonParams(PreparedStatement stmt, EventRequest request, boolean includeAllFields) throws SQLException {
        stmt.setString(1, request.getEventName());
        stmt.setString(2, request.getEventDescription());
        stmt.setString(3, request.getStatus().name());

        if (includeAllFields) {
            stmt.setString(4, request.getOds().getOdsId().toString());
            stmt.setString(5, request.getRequestedBy().getUserId().toString());
            stmt.setTimestamp(6, Timestamp.valueOf(request.getCreateDate()));
            stmt.setDate(7, Date.valueOf(request.getEventDate()));
            stmt.setString(8, request.getAddress().getPostalCode());
            stmt.setString(9, request.getAddress().getCity());
            stmt.setString(10, request.getAddress().getAddressLine());
            stmt.setString(11, request.getAddress().getState());
            stmt.setString(12, request.getRequestId().toString());
        } else {
            stmt.setDate(4, Date.valueOf(request.getEventDate()));
            stmt.setString(5, request.getAddress().getPostalCode());
            stmt.setString(6, request.getAddress().getCity());
            stmt.setString(7, request.getAddress().getAddressLine());
            stmt.setString(8, request.getAddress().getState());
            stmt.setString(9, request.getRequestId().toString());
        }
    }

    @Override
    public Optional<EventRequest> findById(String requestId) {
        EventRequestFilter filter = new EventRequestFilter();
        filter.setRequestId(requestId);

        List<EventRequest> result = findAllEventRequestsByFilter(0, 1, filter);
        return result.isEmpty() ? Optional.empty() : Optional.of(result.get(0));
    }

}
