package owdaqapp.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class UserController {


    @GetMapping("/user")
    public String showAbout(Model model) {

        return "user";
    }

}

