package owdaqapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import owdaqapp.repositories.OwEventLogRepository;

@org.springframework.stereotype.Controller
public class EventLogController {

    @Autowired
    private OwEventLogRepository owEventLogRepository;

    @GetMapping("/eventlogs")
    public String showEventLogs(Model model) {

        model.addAttribute("owEventLogs", this.owEventLogRepository.findOwEventLog());

        return "eventlogs";
    }
}


