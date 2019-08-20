package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    Employee getEmployeeByCardId(String cardId) throws EmployeeNotFoundException;

    Employee registerNewEmployee(RegisterNewEmployeeRequest RegisterNewEmployeeRequest);
}
