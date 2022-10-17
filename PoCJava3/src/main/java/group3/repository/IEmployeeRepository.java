package group3.repository;
import group3.model.Employee;
import group3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface
IEmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from employee e where e.id = :#{#employeeId}")
    User findEmployeeById(@Param("employeeId") Long employeeId);

}
