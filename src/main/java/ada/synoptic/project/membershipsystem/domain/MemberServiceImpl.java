package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberClient memberClient;

    public MemberServiceImpl(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    @Override
    public Employee getEmployeeByCardId(String cardId) throws EmployeeNotFoundException {
        try {
            return memberClient.getEmployeeByCardId(cardId);
        } catch (EmployeeNotFoundException e) {
            throw e;
        }
    }

    @Override
    public Employee registerNewEmployee(RegisterNewEmployeeRequest registerNewEmployeeRequest) {
        return memberClient.registerNewEmployee(registerNewEmployeeRequest);
    }
}
