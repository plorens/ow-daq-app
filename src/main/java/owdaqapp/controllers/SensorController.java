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
import owdaqapp.one_wire.OwSensor;
import owdaqapp.repositories.*;

import java.sql.Timestamp;

@org.springframework.stereotype.Controller
public class SensorController {

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


    @GetMapping("/sensors")
    public String showSensorsList( Model model ) {
        // List<OwSensor> listOfAllSensors = this.owSensorsRepository.findAllSensors();
        model.addAttribute("installedSensors", this.owSensorsRepository.findAllSensors());
        model.addAttribute("listOfAllAdapters", this.owAdaptersRepository.findAllAdapters());
        model.addAttribute("listOfAllSites", this.owSitesRepository.findAllSites());
        return "sensors";
    }

    @GetMapping("/sensors/delete")
    public String deleteFromSensorsList(@RequestParam(name="ID", required=true) Long id, Model model ) {
        owSensorsRepository.deleteById( id );


        OwEventLog owel = new OwEventLog();
        owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
        owel.setTYPE("ADMIN");
        owel.setDESCRIPTION("Usunięto sensor o ID= " + id);
        this.owEventLogRepository.save(owel);


        model.addAttribute("installedSensors", owSensorsRepository.findAllSensors() );
        model.addAttribute("listOfAllAdapters", this.owAdaptersRepository.findAllAdapters());
        model.addAttribute("listOfAllSites", this.owSitesRepository.findAllSites());
        return "sensors";
    }

    @PostMapping("/sensors")
    public String addNewSensor(@ModelAttribute OwSensor sensorToAdd, BindingResult result, ModelMap model ) {
        owSensorsRepository.save( sensorToAdd );

        OwEventLog owel = new OwEventLog();
        owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
        owel.setTYPE("ADMIN");
        owel.setDESCRIPTION("Dodano sensor o adresie= " + sensorToAdd.getADDRESS());
        this.owEventLogRepository.save(owel);


        model.addAttribute("installedSensors", owSensorsRepository.findAllSensors() );
        model.addAttribute("listOfAllAdapters", this.owAdaptersRepository.findAllAdapters());
        model.addAttribute("listOfAllSites", this.owSitesRepository.findAllSites());
        return "sensors";
    }

}

