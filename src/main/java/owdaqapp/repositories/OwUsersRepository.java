package owdaqapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import owdaqapp.one_wire.OwUsers;

import java.util.List;


@Repository
public interface OwUsersRepository extends JpaRepository<OwUsers, Long> {

    /*
    @Query("SELECT DESCRIPTION FROM OwSite")
    List<String> findSitesDescriptions();
    */


    //@Query("select * from OwSite where ID=?1")
    //OwSite findById(Long adapterID);


    @Query("select owus from OwUsers as owus ORDER BY ID ASC")
    List<OwUsers> findAllUsers();
}
