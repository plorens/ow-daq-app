package owdaqapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OwEventLogRepository extends JpaRepository<OwEventLog, Long> {

    @Query("select owel from OwEventLog as owel ORDER BY TIMESTAMP DESC ")
    List<OwEventLog> findOwEventLog();



}
