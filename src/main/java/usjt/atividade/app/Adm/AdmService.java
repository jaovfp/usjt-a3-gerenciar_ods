package usjt.atividade.app.Adm;

import usjt.atividade.domain.entities.User;
import usjt.atividade.domain.repository.EventRepository;
import usjt.atividade.domain.repository.UserRepository;


import java.util.List;


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

}
