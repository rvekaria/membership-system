package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.resource.ChangeBalanceRequest;
import ada.synoptic.project.membershipsystem.rest.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class MemberClientUTest {

    @MockBean
    EmployeeRepository repository;

    @Test
    public void testGetEmployee() throws EmployeeNotFoundException {
        //setup
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
        EmployeeResource actualEmployeeResource = memberClient.getEmployeeByCardId(cardId);

        //assert
        assertEquals(expectedEmployee, actualEmployeeResource.getEmployee());
    }

    @Test
    public void testRegisterNewEmployee() {
        //setup
        MemberClient memberClient = new MemberClientImpl(repository);

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

        Mockito.when(repository.save(expectedEmployee)).thenReturn(expectedEmployee);

        //act
        Employee actualEmployee = memberClient.registerNewEmployee(registerNewEmployeeRequest);

        //assert
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void testTopUp() {
        //setup
        MemberClient memberClient = new MemberClientImpl(repository);

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
        Employee initialEmployee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, pin, currentBalance);
        Employee expectedEmployee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, pin, expectedBalance);
        EmployeeResource expectedEmployeeResource = new EmployeeResource(expectedEmployee);
        ChangeBalanceRequest changeBalanceRequest = new ChangeBalanceRequest(cardId, topUpAmount);

        Mockito.when(repository.findByCardId(changeBalanceRequest.getCardId())).thenReturn(initialEmployee);
        Mockito.when(repository.save(expectedEmployee)).thenReturn(expectedEmployee);

        //act
        EmployeeResource actualEmployeeResource = memberClient.topUp(changeBalanceRequest);

        //assert
        assertEquals(expectedEmployeeResource, actualEmployeeResource);
    }

}
