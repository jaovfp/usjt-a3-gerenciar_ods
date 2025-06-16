package usjt.atividade.domain.repository;

import usjt.atividade.app.Events.DTO.EventRequestFilter;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.valueObjects.Email;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    boolean existsByEmail(Email email);
    void save(User user);
    Optional<User> findByEmail(Email email);
    void update(User user);
    Optional<User> findById(String userId);
    List<User> findAllUsers(int offset, int pageSize);
    boolean deleteUserById(String userId);
}
