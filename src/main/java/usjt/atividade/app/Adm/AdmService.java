package usjt.atividade.app.Adm;

import usjt.atividade.app.Events.DTO.MyEventsRequest;
import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.repository.EventRepository;
import usjt.atividade.domain.repository.UserRepository;


import java.util.List;
import java.util.Optional;


public class AdmService  {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;


    public AdmService(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public List<User> findAllUser(){
        List<User> listUser =  userRepository.findAllUsers(0, 10);
        return listUser;
    }

    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }

    public boolean deleteUserById(String userId) {
        return userRepository.deleteUserById(userId);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public List<MyEventsRequest> findAllEvents(){
        List<MyEventsRequest> listEvent =  eventRepository.findAllEvents(0, 10);
        return listEvent;
    }

    public List<MyEventsRequest> findAllEventsByStatus(int offset, int pageSize, String status) {
        return eventRepository.findAllEventsByStatus(offset, pageSize, status);
    }

    public List<MyEventsRequest> findEventsByUserName(String name, int offset, int pageSize) {
        return userRepository.findEventsByUserName(name, offset, pageSize);
    }

}
