package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.CreateNewEmployeeRequest;
import org.springframework.stereotype.Component;

@Component
public class MemberClientImpl implements MemberClient {

    private final EmployeeRepository repository;

    public MemberClientImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee getEmployee(String employeeId) {
        return repository.findByEmployeeId(Integer.parseInt(employeeId));
    }

    @Override
    public Employee addNewEmployee(CreateNewEmployeeRequest CreateNewEmployeeRequest) {
        Employee newEmployee = Employee.createNewMember(CreateNewEmployeeRequest.getFirstName(), CreateNewEmployeeRequest.getLastName());
        repository.save(newEmployee);
        return newEmployee;
    }
}
