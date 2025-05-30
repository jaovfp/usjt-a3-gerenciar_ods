package usjt.atividade.app.User;

import usjt.atividade.config.MySQLConnection;
import usjt.atividade.domain.model.User.User;
import usjt.atividade.domain.model.User.UserType;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT 1 FROM tbl_users WHERE email = ? LIMIT 1";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao verificar e-mail", e);
        }
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO tbl_users (user_id, username, email, password_hash, type, is_active) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUserId().toString());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getEmail());
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
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM tbl_users WHERE email = ? AND is_active = true";
        return findUserByParam(sql, email);
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
        return new User(
                UUID.fromString(rs.getString("user_id")),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password_hash"),
                UserType.valueOf(rs.getString("type")),
                rs.getBoolean("is_active"),
                rs.getString("profile_photo_url"),
                rs.getTimestamp("create_date").toLocalDateTime(),
                rs.getTimestamp("change_date").toLocalDateTime()
        );
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE tbl_users " +
                    "SET username = ?, email = ?, password_hash = ?, type = ?, is_active = ?, profile_photo_url = ?, change_date = ? " +
                    "WHERE user_id = ?";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPasswordHash());
            stmt.setString(4, user.getType().name());
            stmt.setBoolean(5, user.isActive());
            stmt.setString(6, user.getProfilePhotoUrl());
            stmt.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setString(8, user.getUserId().toString());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }
}
