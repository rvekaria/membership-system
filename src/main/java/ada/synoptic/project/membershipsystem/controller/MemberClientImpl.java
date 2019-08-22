package ada.synoptic.project.membershipsystem.controller;

import ada.synoptic.project.membershipsystem.db.EmployeeRepository;
import ada.synoptic.project.membershipsystem.controller.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.controller.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.controller.resource.ChangeBalanceRequest;
import ada.synoptic.project.membershipsystem.controller.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.controller.resource.RegisterNewEmployeeRequest;
import ada.synoptic.project.membershipsystem.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public EmployeeResource registerNewEmployee(RegisterNewEmployeeRequest registerNewEmployeeRequest) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPin = passwordEncoder.encode(registerNewEmployeeRequest.getPin());

        Employee newEmployee = Employee.createNewMemberWithInitialBalance(
                registerNewEmployeeRequest.getCardId(),
                registerNewEmployeeRequest.getEmployeeId(),
                registerNewEmployeeRequest.getFirstName(),
                registerNewEmployeeRequest.getLastName(),
                registerNewEmployeeRequest.getEmail(),
                registerNewEmployeeRequest.getMobileNo(),
                encodedPin,
                registerNewEmployeeRequest.getBalance());
        newEmployee = repository.save(newEmployee);

        return new EmployeeResource(newEmployee);
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
