package owdaqapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OwSensorsRepository extends JpaRepository<OwSensor, Long> {

    /*
    @Query("SELECT ADDRESS FROM OwSensor")
    List<String> findSensorAddresses();
    */

    @Query("select owsn from OwSensor as owsn ORDER BY ADAPTER_ID ASC")
    List<OwSensor> findAllSensors();

    @Query("select owsn from OwSensor as owsn, OwSite as owst where owsn.STATUS='A'  and owst.STATUS='A' and owst.ID=owsn.SITE_ID and owsn.ADAPTER_ID= ?1 ")
    List<OwSensor> findActiveSensorsForAdapter(Long adapterID);

}
