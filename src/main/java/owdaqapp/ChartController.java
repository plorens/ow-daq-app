package owdaqapp;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class ChartController {


    @GetMapping("/chart")
    public String showAbout(Model model) {

        return "chart";
    }

}

