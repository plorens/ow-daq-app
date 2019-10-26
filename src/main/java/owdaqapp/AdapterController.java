package owdaqapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;


@org.springframework.stereotype.Controller
public class AdapterController {

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


    @GetMapping("/adapters")
    public String showAdaptersList( Model model ) {

        model.addAttribute("installedAdapters", this.owAdaptersRepository.findAllAdapters());

        return "adapters";
    }

    @GetMapping("/adapters/delete")
    public String deleteFromAdapterList(@RequestParam(name="ID", required=true) Long id, Model model ) {
        owAdaptersRepository.deleteById( id );

        OwEventLog owel = new OwEventLog();
        owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
        owel.setTYPE("ADMIN");
        owel.setDESCRIPTION("Usunięto adapter o ID= " + id);
        this.owEventLogRepository.save(owel);

        model.addAttribute("installedAdapters", this.owAdaptersRepository.findAllAdapters());


        return "adapters";
    }

    @PostMapping("/adapters")
    public String addNewAdapter(@ModelAttribute OwAdapter adapterToAdd, BindingResult result, ModelMap model ) {
        owAdaptersRepository.save( adapterToAdd );

        OwEventLog owel = new OwEventLog();
        owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
        owel.setTYPE("ADMIN");
        owel.setDESCRIPTION("Dodano sensor o ID: " + adapterToAdd.getID() + ", nazwie: " + adapterToAdd.getNAME() + " i porcie: " + adapterToAdd.getPORT());

        this.owEventLogRepository.save(owel);

        model.addAttribute("installedAdapters", this.owAdaptersRepository.findAllAdapters() );

        return "adapters";
    }

}

