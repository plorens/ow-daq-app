package owdaqapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import owdaqapp.one_wire.OwEventLog;
import owdaqapp.one_wire.OwUsers;
import owdaqapp.repositories.*;

import java.sql.Timestamp;


@org.springframework.stereotype.Controller
public class UserController {

    @Autowired
    private OwUsersRepository owUsersRepository;
    @Autowired
    private OwAdaptersRepository owAdaptersRepository;
    @Autowired
    private OwReadingsRepository owReadingsRepository;
    @Autowired
    private OwSensorsRepository owSensorsRepository;
    @Autowired
    private OwSitesRepository owSitesRepository;
    @Autowired
    private OwEventLogRepository owEventLogRepository;


    @GetMapping("/users")
    public String showAbout(Model model) {
        model.addAttribute("allUsers", this.owUsersRepository.findAllUsers());

        return "users";
    }

    @PostMapping("/users")
    public String addNewUser(@ModelAttribute OwUsers userToAdd, BindingResult result, ModelMap model ) {
        owUsersRepository.save( userToAdd );

        OwEventLog owel = new OwEventLog();
        owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
        owel.setTYPE("ADMIN");
        owel.setDESCRIPTION("Dodano usera o ID= " + userToAdd.getID() + " i loginie " + userToAdd.getLOGIN());
        this.owEventLogRepository.save(owel);

        model.addAttribute("allUsers", this.owUsersRepository.findAllUsers() );

        return "users";
    }

    @GetMapping("/users/delete")
    public String deleteFromUserList(@RequestParam(name="ID", required=true) Long id, Model model ) {
        owUsersRepository.deleteById( id );

        OwEventLog owel = new OwEventLog();
        owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
        owel.setTYPE("ADMIN");
        owel.setDESCRIPTION("Usunięto usera o ID= " + id);
        this.owEventLogRepository.save(owel);

        model.addAttribute("allUsers", this.owUsersRepository.findAllUsers());

        return "users";
    }

}

