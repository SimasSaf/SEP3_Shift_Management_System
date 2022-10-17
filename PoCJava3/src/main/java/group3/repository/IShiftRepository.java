package group3.repository;
import group3.model.Shift;
import group3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface
IShiftRepository extends JpaRepository<Shift, Long> {

    @Query("select u from Shift u where u.id = :#{#shiftId}")
    Shift findShiftById(@Param("shiftId") Long shiftId);

}
