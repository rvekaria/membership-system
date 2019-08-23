package ada.synoptic.project.membershipsystem.controller;

import ada.synoptic.project.membershipsystem.controller.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.controller.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.controller.resource.ChangeBalanceRequest;
import ada.synoptic.project.membershipsystem.controller.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.controller.resource.RegisterNewEmployeeRequest;
import ada.synoptic.project.membershipsystem.model.Employee;
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
    public EmployeeResource registerNewEmployee(RegisterNewEmployeeRequest registerNewEmployeeRequest) {
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
