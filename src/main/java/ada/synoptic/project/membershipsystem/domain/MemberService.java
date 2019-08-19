package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;

public interface MemberService {
    Employee getEmployee(String employeeId);

    Employee addNewEmployee(RegisterNewEmployeeRequest RegisterNewEmployeeRequest);
}
