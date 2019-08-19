package ada.synoptic.project.membershipsystem.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    public Employee findTopByOrderByEmployeeIdDesc();
    public Employee findByEmployeeId(int employeeId);
    public List<Employee> findByFirstName(String firstName);
    public List<Employee> findByLastName(String lastName);
}
