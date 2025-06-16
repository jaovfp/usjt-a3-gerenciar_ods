package usjt.atividade.infra.Repository;

import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.domain.repository.UserRepository;
import usjt.atividade.domain.valueObjects.*;
import usjt.atividade.infra.config.MySQLConnection;
import usjt.atividade.domain.entities.User;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean existsByEmail(Email email) {
        String sql = "SELECT 1 FROM tbl_users WHERE email = ? LIMIT 1";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email.getValue());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao verificar e-mail", e);
        }
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO tbl_users (user_id, fullname, email, password_hash, type, is_active) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUserId().toString());
            stmt.setString(2, user.getFullname());
            stmt.setString(3, user.getEmail().getValue());
            stmt.setString(4, user.getPasswordHash());
            stmt.setString(5, user.getType().getDescription());
            stmt.setBoolean(6, user.isActive());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao criar usuário", e);
        }
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        String sql = "SELECT * FROM tbl_users WHERE email = ? AND is_active = true";
        return findUserByParam(sql, email.getValue());
    }

    @Override
    public Optional<User> findById(String userId) {
        String sql = "SELECT * FROM tbl_users WHERE user_id = ? AND is_active = true";
        return findUserByParam(sql, userId);
    }

    private Optional<User> findUserByParam(String sql, String param) {
        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, param);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToUser(rs));
            }

            return Optional.empty();

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao consultar usuário", e);
        }
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        LocalDate birthDate = Optional.ofNullable(rs.getDate("birth_date"))
                .map(Date::toLocalDate)
                .orElse(null);

        return new User(
                UUID.fromString(rs.getString("user_id")),
                rs.getString("fullname"),
                new Email(rs.getString("email")),
                rs.getString("password_hash"),
                UserType.valueOf(rs.getString("type")),
                rs.getBoolean("is_active"),
                birthDate,
                rs.getString("cpf"),
                rs.getString("phone_number"),
                rs.getString("address_line"),
                rs.getString("city"),
                rs.getString("state"),
                rs.getString("postal_code"),
                rs.getString("profile_photo_url"),
                rs.getTimestamp("create_date").toLocalDateTime(),
                rs.getTimestamp("change_date").toLocalDateTime()
        );
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE tbl_users SET " +
                "fullname = ?, email = ?, password_hash = ?, birth_date = ?, cpf = ?, phone_number = ?, " +
                "address_line = ?, city = ?, state = ?, postal_code = ?, type = ?, is_active = ?, " +
                "profile_photo_url = ?, change_date = ? " +
                "WHERE user_id = ?";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFullname());
            stmt.setString(2, user.getEmail().getValue());
            stmt.setString(3, user.getPasswordHash());
            stmt.setDate(4, user.getBirthDate() != null ? Date.valueOf(user.getBirthDate()) : null);
            stmt.setString(5, user.getCpfValue());
            stmt.setString(6, user.getPhoneNumberValue());
            stmt.setString(7, user.getAddressLine());
            stmt.setString(8, user.getCity());
            stmt.setString(9, user.getState());
            stmt.setString(10, user.getPostalCode());
            stmt.setString(11, user.getType().name());
            stmt.setBoolean(12, user.isActive());
            stmt.setString(13, user.getProfilePhotoUrl());
            stmt.setTimestamp(14, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(15, user.getUserId().toString());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }

    public List<MyEventsRequest> findEventsByUserName (String name, int offset, int pageSize){
        List<MyEventsRequest> result = new ArrayList<>();

        final String sql =
                "SELECT * " + "FROM tbl_event_requests er " +
                        "WHERE er.fullname = ? " +
                        "ORDER BY er.create_date DESC " +
                        "LIMIT ? OFFSET ?";


        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
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

    public List<User> findAllUsers(int offset, int pageSize) {
        List<User> result = new ArrayList<>();

        final String sql =
                "SELECT fullname, email, birth_date, cpf, phone_number, address_line, city, state, postal_code " +
                        "FROM tbl_users " +
                        "ORDER BY create_date DESC " +
                        "LIMIT ? OFFSET ?";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    User user = new User();
                    user.setFullname(rs.getString("fullname"));
                    user.setEmail(new Email(rs.getString("email")));
                    Date birthDate = rs.getDate("birth_date");
                    user.setBirthDate(birthDate != null ? birthDate.toLocalDate() : null);
                    user.setCpf(rs.getString("cpf"));
                    user.setPhoneNumber(rs.getString("phone_number"));
                    user.setAddress(
                            rs.getString("address_line"),
                            rs.getString("city"),
                            rs.getString("state"),
                            rs.getString("postal_code")
                    );
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return result;
    }

    public boolean deleteUserById(String userId) {
        final String sql = "DELETE FROM tbl_users WHERE user_id = ?";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
            return false;
        }
    }

}
