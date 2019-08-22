package ada.synoptic.project.membershipsystem.controller;

import ada.synoptic.project.membershipsystem.controller.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.controller.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.controller.resource.ChangeBalanceRequest;
import ada.synoptic.project.membershipsystem.controller.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.controller.resource.RegisterNewEmployeeRequest;
import ada.synoptic.project.membershipsystem.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MemberServiceUTest {

    @MockBean
    private MemberClient memberClient;

    @Test
    public void testGetEmployee() throws EmployeeNotFoundException {
        //setup
        MemberService memberService = new MemberServiceImpl(memberClient);

        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        Employee expectedEmployee = Employee.createNewMember(cardId, employeeId, firstName, lastName, email, mobileNo, pin);
        EmployeeResource expectedEmployeeResource = new EmployeeResource(expectedEmployee,  "Welcome, " + firstName + " " + lastName);

        Mockito.when(memberClient.getEmployeeByCardId(cardId)).thenReturn(expectedEmployeeResource);

        //act
        EmployeeResource actualEmployeeResource = memberService.getEmployeeByCardId(cardId);

        //assert
        assertEquals(expectedEmployee, actualEmployeeResource.getEmployee());

    }

    @Test
    public void testRegisterNewEmployee() {
        //setup
        MemberService memberService = new MemberServiceImpl(memberClient);

        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        double balance = 0;
        Employee expectedEmployee = Employee.createNewMember(cardId, employeeId, firstName, lastName, email, mobileNo, pin);
        RegisterNewEmployeeRequest registerNewEmployeeRequest = new RegisterNewEmployeeRequest(cardId, employeeId, firstName, lastName, email, mobileNo, pin, balance);
        EmployeeResource employeeResource = new EmployeeResource(expectedEmployee);

        Mockito.when(memberClient.registerNewEmployee(registerNewEmployeeRequest)).thenReturn(employeeResource);

        //act
        EmployeeResource actualEmployeeResource = memberService.registerNewEmployee(registerNewEmployeeRequest);

        //assert
        assertEquals(employeeResource, actualEmployeeResource);

    }

    @Test
    public void testTopUp() {
        //setup
        MemberService memberService = new MemberServiceImpl(memberClient);

        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        double currentBalance = 5.70;
        double topUpAmount = 3.50;
        double expectedBalance = currentBalance + topUpAmount;
        Employee expectedEmployee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, pin, expectedBalance);
        EmployeeResource employeeResource = new EmployeeResource(expectedEmployee);
        ChangeBalanceRequest changeBalanceRequest = new ChangeBalanceRequest(cardId, topUpAmount);

        Mockito.when(memberClient.topUp(changeBalanceRequest)).thenReturn(employeeResource);

        //act
        EmployeeResource actualEmployeeResource = memberService.topUp(changeBalanceRequest);

        //assert
        assertEquals(employeeResource, actualEmployeeResource);

    }

    @Test
    public void testBuy() throws InsufficientFundsException {
        //setup
        MemberService memberService = new MemberServiceImpl(memberClient);

        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        double currentBalance = 5.70;
        double purchaseAmount = 3.50;
        double expectedBalance = currentBalance - purchaseAmount;
        Employee expectedEmployee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, pin, expectedBalance);
        EmployeeResource employeeResource = new EmployeeResource(expectedEmployee);
        ChangeBalanceRequest purchaseRequest = new ChangeBalanceRequest(cardId, purchaseAmount);

        Mockito.when(memberClient.buy(purchaseRequest)).thenReturn(employeeResource);

        //act
        EmployeeResource actualEmployeeResource = memberService.buy(purchaseRequest);

        //assert
        assertEquals(employeeResource, actualEmployeeResource);

    }
}
