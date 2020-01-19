package owdaqapp.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class TestController {


    @GetMapping("/test")
    public String showAbout(Model model) {

        return "test";
    }

}

