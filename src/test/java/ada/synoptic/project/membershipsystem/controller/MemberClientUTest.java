package ada.synoptic.project.membershipsystem.controller;

import ada.synoptic.project.membershipsystem.controller.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.controller.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.controller.resource.ChangeBalanceRequest;
import ada.synoptic.project.membershipsystem.controller.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.controller.resource.RegisterNewEmployeeRequest;
import ada.synoptic.project.membershipsystem.db.EmployeeRepository;
import ada.synoptic.project.membershipsystem.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPin = passwordEncoder.encode(pin);

        Employee expectedEmployee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, encodedPin, balance);
        EmployeeResource employeeResource = new EmployeeResource(expectedEmployee);
        RegisterNewEmployeeRequest registerNewEmployeeRequest = new RegisterNewEmployeeRequest(cardId, employeeId, firstName, lastName, email, mobileNo, pin, balance);

        Mockito.when(repository.save(any(Employee.class))).thenReturn(expectedEmployee);

        //act
        EmployeeResource actualEmployeeResource = memberClient.registerNewEmployee(registerNewEmployeeRequest);

        //assert
        assertEquals(employeeResource, actualEmployeeResource);
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

    @Test
    public void testBuy() throws InsufficientFundsException {
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
        double purchaseAmount = 3.50;
        double expectedBalance = currentBalance - purchaseAmount;

        Employee initialEmployee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, pin, currentBalance);
        Employee expectedEmployee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, pin, expectedBalance);

        EmployeeResource expectedEmployeeResource = new EmployeeResource(expectedEmployee);
        ChangeBalanceRequest purchaseRequest = new ChangeBalanceRequest(cardId, purchaseAmount);

        Mockito.when(repository.findByCardId(purchaseRequest.getCardId())).thenReturn(initialEmployee);
        Mockito.when(repository.save(expectedEmployee)).thenReturn(expectedEmployee);

        //act
        EmployeeResource actualEmployeeResource = memberClient.buy(purchaseRequest);

        //assert
        assertEquals(expectedEmployeeResource, actualEmployeeResource);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testBuyThrowsException() throws InsufficientFundsException {
        //setup
        MemberClient memberClient = new MemberClientImpl(repository);

        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        double currentBalance = 0;
        double purchaseAmount = 500;
        double expectedBalance = currentBalance - purchaseAmount;

        Employee initialEmployee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, pin, currentBalance);
        Employee expectedEmployee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, pin, expectedBalance);

        EmployeeResource expectedEmployeeResource = new EmployeeResource(expectedEmployee);
        ChangeBalanceRequest purchaseRequest = new ChangeBalanceRequest(cardId, purchaseAmount);

        Mockito.when(repository.findByCardId(purchaseRequest.getCardId())).thenReturn(initialEmployee);
        Mockito.when(repository.save(expectedEmployee)).thenReturn(expectedEmployee);

        //act
        EmployeeResource actualEmployeeResource = memberClient.buy(purchaseRequest);

        //assert
        assertEquals(expectedEmployeeResource, actualEmployeeResource);
    }
}
