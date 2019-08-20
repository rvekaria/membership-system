package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MemberServiceUTest {

    @Test
    public void testGetEmployee() {
        //setup
        MemberClient memberClient = Mockito.mock(MemberClientImpl.class);
        MemberService memberService = new MemberServiceImpl(memberClient);

        String employeeId = "1";
        String cardId = "flksdunro7q4ybcor";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        Employee expectedEmployee = Employee.createNewMember(cardId, employeeId, firstName, lastName, email, mobileNo, pin);

        Mockito.when(memberClient.getEmployee(employeeId)).thenReturn(expectedEmployee);

        //act
        Employee actualEmployee = memberService.getEmployee(employeeId);

        //assert
        assertEquals(expectedEmployee, actualEmployee);

    }

    @Test
    public void testRegisterNewEmployee() {
        //setup
        MemberClient memberClient = Mockito.mock(MemberClientImpl.class);
        MemberService memberService = new MemberServiceImpl(memberClient);

        String employeeId = "1";
        String cardId = "flksdunro7q4ybcor";
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
