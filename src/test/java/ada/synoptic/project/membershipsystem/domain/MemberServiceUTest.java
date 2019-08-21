package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MemberServiceUTest {

    @Test
    public void testGetEmployee() throws EmployeeNotFoundException {
        //setup
        MemberClient memberClient = Mockito.mock(MemberClientImpl.class);
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
        MemberClient memberClient = Mockito.mock(MemberClientImpl.class);
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
}
