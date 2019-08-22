package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import ada.synoptic.project.membershipsystem.rest.resource.TopUpRequest;
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
        Employee expectedEmployee = Employee.createNewMember(cardId, employeeId, firstName, lastName, email, mobileNo, pin);
        RegisterNewEmployeeRequest registerNewEmployeeRequest = new RegisterNewEmployeeRequest(cardId, employeeId, firstName, lastName, email, mobileNo, pin);

        Mockito.when(memberClient.registerNewEmployee(registerNewEmployeeRequest)).thenReturn(expectedEmployee);

        //act
        Employee actualEmployee = memberService.registerNewEmployee(registerNewEmployeeRequest);

        //assert
        assertEquals(expectedEmployee, actualEmployee);

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
        TopUpRequest topUpRequest = new TopUpRequest(cardId, topUpAmount);

        Mockito.when(memberClient.topUp(topUpRequest)).thenReturn(employeeResource);

        //act
        EmployeeResource actualEmployeeResource = memberService.topUp(topUpRequest);

        //assert
        assertEquals(employeeResource, actualEmployeeResource);

    }
}
