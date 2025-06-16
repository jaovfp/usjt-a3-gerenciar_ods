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

    public List<User> findAllUser(int offset, int pageSize){
        List<User> listUser =  userRepository.findAllUsers(offset, pageSize);
        if (listUser.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return listUser;
    }

    public Optional<User> findUserById(String userId) {
        Optional<User> findUser =  userRepository.findById(userId);
        if (findUser.isEmpty()) {
            throw new RuntimeException("No user found");
        }
        return findUser;
    }

    public boolean deleteUserById(String userId) {
        return userRepository.deleteUserById(userId);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public List<MyEventsRequest> findAllEvents(){
        List<MyEventsRequest> listEvent =  eventRepository.findAllEvents(0, 10);
        if (listEvent.isEmpty()) {
            throw new RuntimeException("No events found");
        }
        return listEvent;
    }

    public List<MyEventsRequest> findAllEventsByStatus(int offset, int pageSize, String status) {
        List<MyEventsRequest> listEventByStatus =  eventRepository.findAllEventsByStatus(offset, pageSize, status);
        if (listEventByStatus.isEmpty()) {
            throw new RuntimeException("No events found");
        }
        return listEventByStatus;
    }

    public List<MyEventsRequest> findEventsByUserName(String name, int offset, int pageSize) {
        List<MyEventsRequest> listEventByName =   userRepository.findEventsByUserName(name, offset, pageSize);
        if (listEventByName.isEmpty()) {
            throw new RuntimeException("No events found");
        }
        return listEventByName;
    }

}
