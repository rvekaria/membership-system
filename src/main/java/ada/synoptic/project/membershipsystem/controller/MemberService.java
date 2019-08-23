package ada.synoptic.project.membershipsystem.controller;

import ada.synoptic.project.membershipsystem.controller.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.controller.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.controller.resource.ChangeBalanceRequest;
import ada.synoptic.project.membershipsystem.controller.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.controller.resource.RegisterNewEmployeeRequest;
import ada.synoptic.project.membershipsystem.model.Employee;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    EmployeeResource getEmployeeByCardId(String cardId) throws EmployeeNotFoundException;

    EmployeeResource registerNewEmployee(RegisterNewEmployeeRequest RegisterNewEmployeeRequest);

    EmployeeResource topUp(ChangeBalanceRequest changeBalanceRequest);

    EmployeeResource buy(ChangeBalanceRequest changeBalanceRequest) throws InsufficientFundsException;
}
