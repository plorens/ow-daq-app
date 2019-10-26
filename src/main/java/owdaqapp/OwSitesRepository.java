package owdaqapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OwSitesRepository extends JpaRepository<OwSite, Long> {

    /*
    @Query("SELECT DESCRIPTION FROM OwSite")
    List<String> findSitesDescriptions();
    */


    //@Query("select * from OwSite where ID=?1")
    //OwSite findById(Long adapterID);


    @Query("select DESCRIPTION from OwSite where ID=?1")
    String findDescriptionById(Long adapterID);

    @Query("select owst from OwSite as owst where owst.STATUS='A'")
    List<OwSite> findActiveSites();

    @Query("select owst from OwSite as owst ORDER BY ID ASC")
    List<OwSite> findAllSites();
}
