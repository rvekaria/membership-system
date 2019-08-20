package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;

public interface MemberClient {
    Employee getEmployeeByCardId(String cardId) throws EmployeeNotFoundException;

    Employee registerNewEmployee(RegisterNewEmployeeRequest RegisterNewEmployeeRequest);
}
