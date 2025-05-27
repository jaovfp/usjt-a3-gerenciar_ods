package usjt.atividade.app.Authentication;

import usjt.atividade.config.MySQLConnection;
import usjt.atividade.domain.model.PasswordRecovery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static usjt.atividade.common.utils.DateTimeUtils.toSqlTimestamp;

public class PasswordRecoveryRepositoryImpl implements PassowordRecoveryRepository {

    @Override
    public void save(PasswordRecovery passwordRecovery){
        String sql = "INSERT INTO tbl_password_recovery (recover_id, user_id, pin, created_at, expires_at, is_used) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = MySQLConnection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, passwordRecovery.getRecoveryId().toString());
            stmt.setString(2, passwordRecovery.getUserId().toString());
            stmt.setString(3, passwordRecovery.getPin());
            stmt.setTimestamp(4, toSqlTimestamp(passwordRecovery.getCreatedAt()));
            stmt.setTimestamp(5, toSqlTimestamp(passwordRecovery.getExpiresAt()));
            stmt.setBoolean(6, passwordRecovery.isUsed());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar recuperação de senha", e);
        }
    }

}
