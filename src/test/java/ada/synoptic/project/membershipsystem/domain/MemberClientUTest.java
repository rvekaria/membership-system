package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MemberClientUTest {

    @Test
    public void testGetEmployee() {
        //setup
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        MemberClient memberClient = new MemberClientImpl(repository);

        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        Employee expectedEmployee = Employee.createNewMember(cardId, employeeId, firstName, lastName, email, mobileNo, pin);

        Mockito.when(repository.findByCardId(cardId)).thenReturn(expectedEmployee);

        //act
        Employee actualEmployee = memberClient.getEmployeeByCardId(cardId);

        //assert
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void testRegisterNewEmployee() {
        //setup
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        MemberClient memberClient = new MemberClientImpl(repository);

        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        Employee expectedEmployee = Employee.createNewMember(cardId, employeeId, firstName, lastName, email, mobileNo, pin);
        RegisterNewEmployeeRequest registerNewEmployeeRequest = new RegisterNewEmployeeRequest(cardId, employeeId, firstName, lastName, email, mobileNo, pin);

        Mockito.when(repository.save(expectedEmployee)).thenReturn(expectedEmployee);

        //act
        Employee actualEmployee = memberClient.registerNewEmployee(registerNewEmployeeRequest);

        //assert
        assertEquals(expectedEmployee, actualEmployee);
    }

}
