package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;

public interface MemberClient {
    Employee getEmployee(String employeeId);

    Employee registerNewEmployee(RegisterNewEmployeeRequest RegisterNewEmployeeRequest);
}
