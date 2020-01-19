package owdaqapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import owdaqapp.one_wire.OwEventLog;
import owdaqapp.repositories.*;

import java.sql.Timestamp;

/**
 *
 */
@Component
public class OwScheduler {

    private static final Logger log = LoggerFactory.getLogger(OwScheduler.class);

    @Autowired
    private OwAdaptersRepository owAdaptersRepository;

    @Autowired
    private OwReadingsRepository owReadingsRepository;

    @Autowired
    private OwSensorsRepository owSensorsRepository;

    @Autowired
    private OwSitesRepository owSitesRepository;

    @Autowired
    private OwDaqProcessor owDaqProcessor;

    @Autowired
    private OwEventLogRepository owEventLogRepository;


    /**
     *  This function is used for the first import of data (immediately after starting the application)
     */
    @Scheduled(fixedDelay = Long.MAX_VALUE, initialDelay = 50)
    public void firstImportData() {

        OwEventLog owel = new OwEventLog();
        owel.setTIMESTAMP(new Timestamp(System.currentTimeMillis()));
        owel.setTYPE("INFO");
        owel.setDESCRIPTION("Uruchomiono aplikację");
        this.owEventLogRepository.save(owel);

        importData();
    }

    /*
    @Value("${ScheduledTasks.pathToFiles}")
    private String pathToFiles;
    */

    /**
     * This function is used for the planned start of data import
     * The schedule and path to the file from which the data is downloaded can be set in application.properties
     */
    @Scheduled(cron = "${ScheduledTasks.cronExpressionUpdateDatabaseTime}")
    public void importData() {

        log.info("before measurement");
        owDaqProcessor.doMeasurement();
        log.info("after measurement");

    }
}
