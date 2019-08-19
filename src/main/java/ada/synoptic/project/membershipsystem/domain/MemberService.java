package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    Employee getEmployee(String employeeId);

    Employee addNewEmployee(RegisterNewEmployeeRequest RegisterNewEmployeeRequest);
}
