package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import ada.synoptic.project.membershipsystem.rest.resource.TopUpRequest;

public interface MemberClient {
    EmployeeResource getEmployeeByCardId(String cardId) throws EmployeeNotFoundException;

    Employee registerNewEmployee(RegisterNewEmployeeRequest RegisterNewEmployeeRequest);

    EmployeeResource topUp(TopUpRequest topUpRequest);
}
