package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.rest.resource.ChangeBalanceRequest;
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
    public EmployeeResource topUp(ChangeBalanceRequest changeBalanceRequest) {
        Employee employee = repository.findByCardId(changeBalanceRequest.getCardId());
        double currentBalance = employee.getBalance();
        double finalBalance = currentBalance + changeBalanceRequest.getChangeAmount();
        employee.setBalance(finalBalance);
        employee = repository.save(employee);

        return new EmployeeResource(employee);

    }

    @Override
    public EmployeeResource buy(ChangeBalanceRequest changeBalanceRequest) throws InsufficientFundsException {
        Employee employee = repository.findByCardId(changeBalanceRequest.getCardId());
        double currentBalance = employee.getBalance();
        double finalBalance = currentBalance - changeBalanceRequest.getChangeAmount();
        if (finalBalance >= 0) {
            employee.setBalance(finalBalance);
            employee = repository.save(employee);
            return new EmployeeResource(employee);
        } else throw new InsufficientFundsException();

    }
}
