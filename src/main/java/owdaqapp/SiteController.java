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
public class SiteController {

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


    @GetMapping("/sites")
    public String showSitesList( Model model ) {
        model.addAttribute("installedSites", this.owSitesRepository.findAllSites());

        return "sites";
    }

    @GetMapping("/sites/delete")
    public String deleteFromSitesList(@RequestParam(name="ID", required=true) Long id, Model model ) {
        owSitesRepository.deleteById( id );

        OwEventLog owel = new OwEventLog();
        owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
        owel.setTYPE("ADMIN");
        owel.setDESCRIPTION("Usunięto site o ID= " + id);
        this.owEventLogRepository.save(owel);

        model.addAttribute("installedSites", this.owSitesRepository.findAllSites());

        return "sites";
    }

    @PostMapping("/sites")
    public String addNewSite(@ModelAttribute OwSite siteToAdd, BindingResult result, ModelMap model ) {
        owSitesRepository.save( siteToAdd );

        OwEventLog owel = new OwEventLog();
        owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
        owel.setTYPE("ADMIN");
        owel.setDESCRIPTION("Dodano sensor o ID= " + siteToAdd.getID() + "i opisie" + siteToAdd.getDESCRIPTION());
        this.owEventLogRepository.save(owel);



        model.addAttribute("installedSites", this.owSitesRepository.findAllSites() );

        return "sites";
    }
}

