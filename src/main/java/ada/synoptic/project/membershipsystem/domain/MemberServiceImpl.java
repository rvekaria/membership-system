package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberClient memberClient;

    public MemberServiceImpl(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    @Override
    public Employee getEmployeeByCardId(String cardId) {
        return memberClient.getEmployeeByCardId(cardId);
    }

    @Override
    public Employee registerNewEmployee(RegisterNewEmployeeRequest registerNewEmployeeRequest) {
        return memberClient.registerNewEmployee(registerNewEmployeeRequest);
    }
}
