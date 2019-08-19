package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
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
    public Employee addNewEmployee(RegisterNewEmployeeRequest RegisterNewEmployeeRequest) {
        Employee newEmployee = Employee.createNewMember(
                RegisterNewEmployeeRequest.getFirstName(),
                RegisterNewEmployeeRequest.getLastName(),
                RegisterNewEmployeeRequest.getEmail(),
                RegisterNewEmployeeRequest.getMobileNo(),
                RegisterNewEmployeeRequest.getPin()
        );
        repository.save(newEmployee);
        return newEmployee;
    }
}
