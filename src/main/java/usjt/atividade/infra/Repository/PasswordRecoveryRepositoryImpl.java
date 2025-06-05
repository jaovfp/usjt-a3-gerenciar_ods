package usjt.atividade.infra.Repository;

import usjt.atividade.domain.repository.PassowordRecoveryRepository;
import usjt.atividade.domain.valueObjects.RecoveryPin;
import usjt.atividade.infra.config.MySQLConnection;
import usjt.atividade.domain.entities.PasswordRecovery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static usjt.atividade.common.utils.DateTimeUtils.toSqlTimestamp;

public class PasswordRecoveryRepositoryImpl implements PassowordRecoveryRepository {

    @Override
    public void save(PasswordRecovery passwordRecovery){
        String sql = "INSERT INTO tbl_password_recovery (recovery_id, user_id, pin, created_at, expires_at, is_used) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, passwordRecovery.getRecoveryId().toString());
            stmt.setString(2, passwordRecovery.getUserId().toString());
            stmt.setString(3, passwordRecovery.getPin().getValue());
            stmt.setTimestamp(4, toSqlTimestamp(passwordRecovery.getCreatedAt()));
            stmt.setTimestamp(5, toSqlTimestamp(passwordRecovery.getExpiresAt()));
            stmt.setBoolean(6, passwordRecovery.isUsed());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao criar recuperação de senha", e);
        }
    }

    @Override
    public Optional<PasswordRecovery> findValidById(UUID recoveryId) {
        String sql = "SELECT recovery_id, user_id, pin, created_at, expires_at, is_used " +
                "FROM tbl_password_recovery " +
                "WHERE recovery_id = ? " +
                "AND expires_at > CONVERT_TZ(NOW(), '+00:00', '-03:00') " +
                "AND is_used = FALSE";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, recoveryId.toString());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                PasswordRecovery recovery = new PasswordRecovery(
                        UUID.fromString(rs.getString("recovery_id")),
                        UUID.fromString(rs.getString("user_id")),
                        new RecoveryPin(rs.getString("pin")),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("expires_at").toLocalDateTime(),
                        rs.getBoolean("is_used")
                );
                return Optional.of(recovery);
            }

            return Optional.empty();
        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao buscar recuperação de senha por ID", e);
        }
    }

    @Override
    public void invalidatePin(UUID recoveryId) {
        String sql = "UPDATE tbl_password_recovery SET is_used = true WHERE recovery_id = ?";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, recoveryId.toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            throw new RuntimeException("Erro ao invalidar o PIN de recuperação", e);
        }
    }
}
