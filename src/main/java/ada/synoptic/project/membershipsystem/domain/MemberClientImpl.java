package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import ada.synoptic.project.membershipsystem.rest.resource.TopUpRequest;
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
            return new EmployeeResource(employee,
                    "Welcome, " + employee.getFirstName() + " " + employee.getLastName());
        } else throw new EmployeeNotFoundException();
    }

    @Override
    public Employee registerNewEmployee(RegisterNewEmployeeRequest registerNewEmployeeRequest) {
        Employee newEmployee = Employee.createNewMemberWithInitialBalance(
                registerNewEmployeeRequest.getCardId(),
                registerNewEmployeeRequest.getEmployeeId(),
                registerNewEmployeeRequest.getFirstName(),
                registerNewEmployeeRequest.getLastName(),
                registerNewEmployeeRequest.getEmail(),
                registerNewEmployeeRequest.getMobileNo(),
                registerNewEmployeeRequest.getPin(),
                registerNewEmployeeRequest.getBalance()
        );
        return repository.save(newEmployee);
    }

    @Override
    public EmployeeResource topUp(TopUpRequest topUpRequest) {
        Employee employee = repository.findByCardId(topUpRequest.getCardId());
        double currentBalance = employee.getBalance();
        double finalBalance = currentBalance + topUpRequest.getTopUpAmount();
        employee.setBalance(finalBalance);
        employee = repository.save(employee);

        return new EmployeeResource(employee);

    }
}
