package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.rest.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import ada.synoptic.project.membershipsystem.rest.resource.ChangeBalanceRequest;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberClient memberClient;

    public MemberServiceImpl(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    @Override
    public EmployeeResource getEmployeeByCardId(String cardId) throws EmployeeNotFoundException {
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

    @Override
    public EmployeeResource topUp(ChangeBalanceRequest changeBalanceRequest) {
        return memberClient.topUp(changeBalanceRequest);
    }

    @Override
    public EmployeeResource buy(ChangeBalanceRequest changeBalanceRequest) throws InsufficientFundsException {
        try {
            return memberClient.buy(changeBalanceRequest);
        } catch (InsufficientFundsException e) {
            throw e;
        }
    }
}
