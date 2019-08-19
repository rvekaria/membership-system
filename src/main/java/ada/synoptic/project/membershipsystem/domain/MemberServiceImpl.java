package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.CreateNewEmployeeRequest;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberClient memberClient;

    public MemberServiceImpl(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    @Override
    public Employee getEmployee(String employeeId) {
        return memberClient.getEmployee(employeeId);
    }

    @Override
    public Employee addNewEmployee(CreateNewEmployeeRequest CreateNewEmployeeRequest) {
        return memberClient.addNewEmployee(CreateNewEmployeeRequest);
    }
}
