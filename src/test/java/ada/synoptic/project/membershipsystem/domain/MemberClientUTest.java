package ada.synoptic.project.membershipsystem.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MemberClientUTest {

    @Test
    public void testGetEmployee(){
        //set
        EmployeeRepository repository = Mockito.mock(EmployeeRepository.class);
        MemberClient memberClient = new MemberClientImpl(repository);

        String employeeId = "1";
        String cardId = "flksdunro7q4ybcor";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        Employee expectedEmployee = Employee.createNewMember(cardId, employeeId, firstName, lastName, email, mobileNo, pin);

        Mockito.when(repository.findByEmployeeId(employeeId)).thenReturn(expectedEmployee);

        //act
        Employee actualEmployee = memberClient.getEmployee(employeeId);

        //assert
        assertEquals(expectedEmployee, actualEmployee);
    }

}
