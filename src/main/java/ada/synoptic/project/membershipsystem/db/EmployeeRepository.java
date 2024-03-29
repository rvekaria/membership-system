package ada.synoptic.project.membershipsystem.db;

import ada.synoptic.project.membershipsystem.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    public Employee findByCardId(String cardNumber);

    public Employee findByEmployeeId(String employeeId);

    public List<Employee> findByFirstName(String firstName);

    public List<Employee> findByLastName(String lastName);

    public Employee findTopByOrderByEmployeeIdDesc();
}
