package owdaqapp;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ChartDataItem {

    private Long SITE_ID;
    private Double VALUE;
    private Timestamp TIMESTAMP;



    //------------------------------------------
    public ChartDataItem(Long SITE_ID, Double VALUE, Timestamp TIMESTAMP) {}

    public ChartDataItem(Object objTemp, Object objDateTime, Object objSiteId) {

        BigDecimal bd= (BigDecimal) objTemp;
        this.VALUE = bd.doubleValue();


        String text = (String) objDateTime;
        SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date lFromDate1 = null;
        try {
                lFromDate1 = datetimeFormatter1.parse(text);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        assert lFromDate1 != null;
        this.TIMESTAMP = new Timestamp(lFromDate1.getTime());


        BigDecimal bd2= (BigDecimal) objSiteId;
        this.SITE_ID = bd2.longValue();
    }


    //------------------------------------------

    public Long getSITE_ID() {
        return SITE_ID;
    }

    public void setSITE_ID(Long SITE_ID) {
        this.SITE_ID = SITE_ID;
    }

    public Double getVALUE() {
        return VALUE;
    }

    public void setVALUE(Double VALUE) { this.VALUE = VALUE; }

    public Timestamp getTIMESTAMP() {
        return TIMESTAMP;
    }

    public void setTIMESTAMP(Timestamp TIMESTAMP) {
        this.TIMESTAMP = TIMESTAMP;
    }
}
