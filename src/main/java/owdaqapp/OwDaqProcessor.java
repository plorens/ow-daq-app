package owdaqapp;

import java.sql.Timestamp;
import java.util.List;

import com.dalsemi.onewire.OneWireException;
import com.dalsemi.onewire.container.TemperatureContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dalsemi.onewire.OneWireAccessProvider;
import com.dalsemi.onewire.adapter.DSPortAdapter;
import com.dalsemi.onewire.container.OneWireContainer;

@Component
public class OwDaqProcessor {

    private static final Logger log = LoggerFactory.getLogger(OwDaqProcessor.class);

    @Autowired
    private OwReadingsRepository owReadingsRepository;

    @Autowired
    private OwAdaptersRepository owAdaptersRepository;

    @Autowired
    private OwSensorsRepository owSensorsRepository;

    @Autowired
    private OwSitesRepository owSitesRepository;

    @Autowired
    private OwEventLogRepository owEventLogRepository;




    private void processTemp(DSPortAdapter adapter, OwSensor owsensor) throws OneWireException {

        OneWireContainer owc = adapter.getDeviceContainer(owsensor.getADDRESS());

        if (!(owc instanceof TemperatureContainer)) {
            log.info("Sensor is not a temperature container");
            return;
        }
        System.out.println("= " + owc.getAddressAsString());

        TemperatureContainer tc = (TemperatureContainer) owc;

        boolean selectable = tc.hasSelectableTemperatureResolution();
        double[] resolution = null;

        System.out.println("= This device is a " + owc.getName());

        try{
            byte[] state = tc.readDevice();

            if (selectable) {

                try {
                    resolution = tc.getTemperatureResolutions();
                    for (int i = 0; i < resolution.length; i++) {
                        System.out.println("= Available resolution " + i + "        : " + resolution[i]);
                    }
                    System.out.println("= Setting temperature resolution to " + resolution[resolution.length - 1] + "...");
                    tc.setTemperatureResolution(resolution[resolution.length - 1], state);

                } catch (Exception e) {
                    log.info(
                            "= Could not get available resolutions : "
                                    + e.toString());
                }

                try {
                    tc.doTemperatureConvert(state);
                } catch (Exception e) {
                    log.info("= Could not complete temperature conversion...");
                }

                state = tc.readDevice();

                double temp = tc.getTemperature(state);

                System.out.println("= Reported temperature: " + temp);


                 if (temp>=85)
                 {
                     log.info("Sprzętowy błąd pomiaru: temp= " +temp);
                     OwEventLog owel = new OwEventLog();
                     owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
                     owel.setTYPE("ERROR");
                     owel.setDESCRIPTION("Sprzętowy błąd pomiaru dla czujnika o adresie:  " + owc.getAddressAsString()+ "\n temperature="+temp+"℃");
                     this.owEventLogRepository.save(owel);
                     return;
                 }


                OwReading owr = new OwReading();
                owr.setSITE_ID(owsensor.getSITE_ID());
                owr.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
                owr.setVALUE(temp);
                this.owReadingsRepository.save(owr);

            }
        }catch(Exception e){
            log.info("Problem z czujnikiem");
            OwEventLog owel = new OwEventLog();
            owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
            owel.setTYPE("ERROR");
            owel.setDESCRIPTION("Problem z czujnikiem o adresie: " + owc.getAddressAsString() + "\n (wiecej info w logach)");
            this.owEventLogRepository.save(owel);
            log.info("Exception: " + e.toString());
        }
    }







    void doMeasurement(){

        OneWireContainer owd;

        List<OwAdapter> activeAdapters= owAdaptersRepository.findActiveAdapters();

        for(OwAdapter owa: activeAdapters){
            try {
                DSPortAdapter adapter = OneWireAccessProvider.getAdapter(owa.getNAME(), owa.getPORT());

                // get exclusive use of adapter
                adapter.beginExclusive(true);

                // clear any previous search restrictions
                adapter.setSearchAllDevices();
                adapter.targetAllFamilies();
                adapter.setSpeed(adapter.SPEED_REGULAR);


                log.info("adapter ready");
                List<OwSensor> activeSensors= owSensorsRepository.findActiveSensorsForAdapter(owa.getID());

                for(OwSensor owsensor:activeSensors)
                {
                    this.processTemp(adapter, owsensor);
                }

                // end exclusive use of adapter
                adapter.endExclusive();

                // free port used by adapter
                adapter.freePort();
            }
            catch(Exception e)
            {
                OwEventLog owel = new OwEventLog();
                owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
                owel.setTYPE("ERROR");
                owel.setDESCRIPTION("Problem z adapterem o nazwie: " + owa.getNAME() +" i porcie "+ owa.getPORT() + " (wiecej info w logach)");
                this.owEventLogRepository.save(owel);

                System.out.println(e);
            }
        }

        //lista adapterów, otwieranie, zamykanie

    }

}
