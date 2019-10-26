package owdaqapp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class RestAPI {

    @Autowired
    private OwSitesRepository owSitesRepository;

    @Autowired
    private OwReadingsRepository owReadingsRepository;

    @GetMapping("/API/currentReading")
    public SingleReadingData getCurrentReading(@RequestParam(name="siteid",   required = true) Long siteId)   {

        OwReading owr = owReadingsRepository.findCurrentOwReadingForSite(siteId);
        Optional<OwSite> owst  = owSitesRepository.findById(siteId);

        SingleReadingData srd= new SingleReadingData();

        srd.setTemperature(owr.getVALUE());
        srd.setTimestamp(owr.getTIMESTAMP());
        srd.setSiteName(owst.get().getDESCRIPTION());

        return srd;
    }


    @GetMapping("/API/currentReadings")
    public List<SingleReadingData> getCurrentReadings()  {

        List<OwSite> owSiteList= new ArrayList<>();
        owSiteList=owSitesRepository.findActiveSites();
        List<SingleReadingData> owReadingList= new ArrayList<>();

        for(OwSite owst : owSiteList) {

            OwReading owr = owReadingsRepository.findCurrentOwReadingForSite(owst.getID());
            /* omijamy puste sity */
            if (owr==null){
                continue;
            }
            SingleReadingData srd= new SingleReadingData();

            srd.setTemperature(owr.getVALUE());
            srd.setTimestamp(owr.getTIMESTAMP());
            srd.setSiteName(owst.getDESCRIPTION());

            owReadingList.add(srd);
        }
        return owReadingList;
    }


    @GetMapping("/API/siteList")
    public List<OwSite> getSiteList(){

        List<OwSite> owSiteList= new ArrayList<>();

        owSiteList=owSitesRepository.findActiveSites();

        return owSiteList;
    }


    @GetMapping("/API/readings")
    public List<OwReading> getReadings(@RequestParam(name="siteid",   required = true) Long siteId){

        List<OwReading> readings= new ArrayList<>();
        readings = owReadingsRepository.findReadings(siteId);

        return readings;
    }



    @GetMapping("/API/avgReadingsByDate")
    public List<ChartDataItem> getAvgReadingsByDate(@RequestParam(name="siteid",   required = true) Long siteId, @RequestParam(name="begin", required = true) String begin, @RequestParam(name="end", required = true) String end) throws ParseException {

        List<Object[]> tempData = owReadingsRepository.findAverageDataByDate(siteId, begin, end);
        List<ChartDataItem> readings = new ArrayList<>();

        for (Object[] object:tempData) {
            readings.add(new ChartDataItem(object[0], object[1], object[2]));
        }

        return readings;
    }


        @GetMapping("/API/avgReadings")
        public List<ChartDataItem> getAvgReadings(@RequestParam(name="siteid",   required = true) Long siteId) {

            List<Object[]> tempData = owReadingsRepository.findAverageData(siteId);

            List<ChartDataItem> readings = new ArrayList<>();

            for (Object[] object : tempData) {
                readings.add(new ChartDataItem(object[0], object[1], object[2]));
            }

            return readings;

        }
}
