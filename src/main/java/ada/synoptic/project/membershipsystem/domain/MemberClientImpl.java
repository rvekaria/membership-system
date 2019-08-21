package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.springframework.stereotype.Component;

@Component
public class MemberClientImpl implements MemberClient {

    private final EmployeeRepository repository;

    public MemberClientImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeResource getEmployeeByCardId(String cardId) throws EmployeeNotFoundException {
        Employee employee = repository.findByCardId(cardId);
        if (employee != null) {
            EmployeeResource employeeResource = new EmployeeResource(employee,
                    "Welcome, " + employee.getFirstName() + " " + employee.getLastName());
            return employeeResource;
        } else throw new EmployeeNotFoundException();
    }

    @Override
    public Employee registerNewEmployee(RegisterNewEmployeeRequest registerNewEmployeeRequest) {
        Employee newEmployee = Employee.createNewMember(
                registerNewEmployeeRequest.getCardId(),
                registerNewEmployeeRequest.getEmployeeId(),
                registerNewEmployeeRequest.getFirstName(),
                registerNewEmployeeRequest.getLastName(),
                registerNewEmployeeRequest.getEmail(),
                registerNewEmployeeRequest.getMobileNo(),
                registerNewEmployeeRequest.getPin()
        );
        return repository.save(newEmployee);
    }
}
