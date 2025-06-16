package usjt.atividade.infra.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasher {

    public static String hash(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // 12 significa que o algoritmo vai rodar 2¹² (4096) rounds de processamento para gerar o hash.
    }

    public static boolean verify(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
