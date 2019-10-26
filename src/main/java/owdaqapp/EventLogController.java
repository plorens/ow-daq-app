package owdaqapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


