package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.CreateNewEmployeeRequest;

public interface MemberClient {
    Employee getEmployee(String employeeId);

    Employee addNewEmployee(CreateNewEmployeeRequest CreateNewEmployeeRequest);
}
