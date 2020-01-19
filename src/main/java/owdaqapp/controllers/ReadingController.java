package owdaqapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import owdaqapp.one_wire.OwAdapter;
import owdaqapp.one_wire.OwReading;
import owdaqapp.repositories.OwAdaptersRepository;
import owdaqapp.repositories.OwReadingsRepository;
import owdaqapp.repositories.OwSensorsRepository;
import owdaqapp.repositories.OwSitesRepository;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class ReadingController {

    @Autowired
    private OwAdaptersRepository owAdaptersRepository;
    @Autowired
    private OwReadingsRepository owReadingsRepository;
    @Autowired
    private OwSensorsRepository owSensorsRepository;
    @Autowired
    private OwSitesRepository owSitesRepository;


    @GetMapping("/readings")
    public String showReadings(Model model,  @RequestParam(name="id", required=false) Long siteID) {


        model.addAttribute("listOfAllSites", this.owSitesRepository.findAllSites());

        List<OwAdapter> listOfActiveAdapters = this.owAdaptersRepository.findActiveAdapters();
        model.addAttribute("listOfActiveAdapters", listOfActiveAdapters);

        model.addAttribute("siteID", siteID);


        model.addAttribute("siteDescription", this.owSitesRepository.findDescriptionById(siteID));
/*
        List<OwReading> listOfValueForSite = this.owReadingsRepository.findOwReadingForSite(siteID);
        model.addAttribute("listOfValueForSite", listOfValueForSite);
*/


        List<OwReading> listOfLastValuesForSite = new ArrayList<>() ;
        if (siteID!=null) {
            listOfLastValuesForSite=this.owReadingsRepository.findLastOwReadingForSite(siteID);
        }
        model.addAttribute("listOfValueForSite", listOfLastValuesForSite);
        return "readings";
    }
}

