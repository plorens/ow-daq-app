package owdaqapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import owdaqapp.one_wire.OwAdapter;

import java.util.List;

@Repository
public interface OwAdaptersRepository extends JpaRepository<OwAdapter, Long> {

    @Query("select owad from OwAdapter as owad where STATUS='A'")
    List<OwAdapter> findActiveAdapters();

    @Query("select owad from OwAdapter as owad ORDER BY ID ASC")
    List<OwAdapter> findAllAdapters();



}
