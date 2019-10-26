package owdaqapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface OwReadingsRepository extends JpaRepository<OwReading, Long> {


    @Query("select owrd from OwReading as owrd where owrd.SITE_ID= ?1 ORDER BY TIMESTAMP DESC ")
    List<OwReading> findOwReadingForSite(Long siteID);

    @Query(value = "select ID, SITE_ID, VALUE, TIMESTAMP from READINGS where SITE_ID= ?1 ORDER BY TIMESTAMP DESC fetch next 1000 rows only", nativeQuery = true)
    List<OwReading> findLastOwReadingForSite(Long siteID);


    @Query(value = "select * from READINGS where SITE_ID= ?1 ORDER BY TIMESTAMP DESC fetch next 1 rows only", nativeQuery = true)
    OwReading findCurrentOwReadingForSite(Long siteID);


    @Query(value = "SELECT ID, SITE_ID, VALUE, TIMESTAMP FROM READINGS where SITE_ID= ?1 and timestamp>='2019-09-18 00:00' and timestamp<='2019-09-20 09:00' ORDER BY ID ASC", nativeQuery = true)
    List<OwReading> findReadings(Long siteID);


    @Query(value = "select avg(value), TO_CHAR(timestamp, 'YYYY-MM-DD HH24:MI'), site_id from READINGS where timestamp>='2019-09-20 00:00' and timestamp<='2019-09-23 09:10' and SITE_ID=?1 group by TO_CHAR(timestamp, 'YYYY-MM-DD HH24:MI'), site_id order by TO_CHAR(timestamp, 'YYYY-MM-DD HH24:MI')", nativeQuery = true)
    List<Object[]> findAverageData(Long siteID);

    //@Query(value = "select TO_DATE( ?2, 'yyyy-MM-dd HH24:MI') as qq , TO_DATE( ?3, 'yyyy-MM-dd HH24:MI') as ww,  ?1 as aa from DUAL", nativeQuery = true)
    @Query(value = "select avg(value), TO_CHAR(timestamp, 'YYYY-MM-DD HH24:MI'), site_id from READINGS where timestamp>= TO_DATE( ?2, 'yyyy-MM-dd HH24:MI') and timestamp<= TO_DATE( ?3, 'yyyy-MM-dd HH24:MI') and SITE_ID= ?1 group by TO_CHAR(timestamp, 'YYYY-MM-DD HH24:MI'), site_id order by TO_CHAR(timestamp, 'YYYY-MM-DD HH24:MI')", nativeQuery = true)
    List<Object[]> findAverageDataByDate(Long siteID, String begin, String end);





/*
    @Query(value = "select avg(value), TO_CHAR(timestamp, 'YYYY-MM-DD HH24:MI'), site_id from READINGS where timestamp>='2019-09-12 00:00' and timestamp<='2019-09-13 00:00' and SITE_ID=?1 group by TO_CHAR(timestamp, 'YYYY-MM-DD-HH24:MI'), site_id order by TO_CHAR(timestamp, 'YYYY-MM-DD-HH24:MI')", nativeQuery = true)
    List<ChartDataItem> findAvarageData1(Long siteID);
*/

/*
    @Query("SELECT VALUE FROM OwReading where SITE_ID= ?1 ")
    List<String> findValuesForSite(int siteID);
*/
}
