package ada.synoptic.project.membershipsystem.rest;

import ada.synoptic.project.membershipsystem.domain.Employee;
import ada.synoptic.project.membershipsystem.domain.MemberServiceImpl;
import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.rest.resource.ChangeBalanceRequest;
import ada.synoptic.project.membershipsystem.rest.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerUTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MemberServiceImpl memberService;

    @Test
    public void testGetEmployeeEndpointIfRegistered() throws Exception {
        //setup
        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        String responseMessage = "Welcome, " + firstName + " " + lastName;
        Employee employee = Employee.createNewMember(cardId, employeeId, firstName, lastName, email, mobileNo, pin);
        EmployeeResource employeeResource = new EmployeeResource(employee, responseMessage);

        Mockito.when(memberService.getEmployeeByCardId(cardId)).thenReturn(employeeResource);

        //act
        mvc.perform(get("/employee?cardId=" + cardId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("employee.cardId", equalTo(cardId)))
                .andExpect(jsonPath("employee.employeeId", equalTo(employeeId)))
                .andExpect(jsonPath("employee.firstName", equalTo(firstName)))
                .andExpect(jsonPath("employee.lastName", equalTo(lastName)))
                .andExpect(jsonPath("employee.email", equalTo(email)))
                .andExpect(jsonPath("employee.mobileNo", equalTo(mobileNo)))
                .andExpect(jsonPath("employee.pin", equalTo(pin)))
                .andExpect(jsonPath("responseMessage", equalTo(responseMessage)));
    }

    @Test
    public void testGetEmployeeEndpointIfNotRegistered() throws Exception {
        //setup
        String cardId = "12345678abcdefgh";
        String employeeNotFoundError = "This card is not registered. Please register first to use the service";

        Mockito.when(memberService.getEmployeeByCardId(any(String.class))).thenThrow(EmployeeNotFoundException.class);

        //act
        mvc.perform(get("/employee?cardId=" + cardId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> {
                    assertEquals(employeeNotFoundError, mvcResult.getResponse().getErrorMessage());
                });
    }

    @Test
    public void testRegisterNewEmployeeEndpoint() throws Exception {
        //setup
        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "New";
        String lastName = "Guy";
        String email = "NewEmail";
        String mobileNo = "107824231";
        String pin = "3589";
        double balance = 0;

        RegisterNewEmployeeRequest registerNewEmployeeRequest = new RegisterNewEmployeeRequest(cardId, employeeId, firstName, lastName, email, mobileNo, pin, balance);
        ObjectMapper objectMapper = new ObjectMapper();
        String registerNewEmployeeRequestJson = objectMapper.writeValueAsString(registerNewEmployeeRequest);

        Employee employee = Employee.createNewMember(cardId, employeeId, firstName, lastName, email, mobileNo, pin);

        Mockito.when(memberService.registerNewEmployee(registerNewEmployeeRequest)).thenReturn(employee);

        //act
        mvc.perform(post("/register").content(registerNewEmployeeRequestJson).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    System.out.println(mvcResult.getResponse().toString());
                })
                .andExpect(jsonPath("employeeId").value(employeeId))
                .andExpect(jsonPath("cardId", equalTo(cardId)))
                .andExpect(jsonPath("firstName", equalTo(firstName)))
                .andExpect(jsonPath("lastName", equalTo(lastName)))
                .andExpect(jsonPath("email", equalTo(email)))
                .andExpect(jsonPath("mobileNo", equalTo(mobileNo)))
                .andExpect(jsonPath("pin", equalTo(pin))
                )
        ;
    }

    @Test
    public void testTopUp() throws Exception {
        //setup
        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        double initialBalance = 5.23;
        double topUpAmount = 3.70;
        double finalBalance = initialBalance + topUpAmount;

        ChangeBalanceRequest changeBalanceRequest = new ChangeBalanceRequest(cardId, topUpAmount);
        ObjectMapper objectMapper = new ObjectMapper();
        String topUpRequestJson = objectMapper.writeValueAsString(changeBalanceRequest);

        Employee employee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, pin, finalBalance);
        EmployeeResource employeeResource = new EmployeeResource(employee);
        Mockito.when(memberService.topUp(changeBalanceRequest)).thenReturn(employeeResource);

        //act
        mvc.perform(put("/topUpBalance").content(topUpRequestJson).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("employee.employeeId").value(employeeId))
                .andExpect(jsonPath("employee.cardId", equalTo(cardId)))
                .andExpect(jsonPath("employee.firstName", equalTo(firstName)))
                .andExpect(jsonPath("employee.lastName", equalTo(lastName)))
                .andExpect(jsonPath("employee.email", equalTo(email)))
                .andExpect(jsonPath("employee.mobileNo", equalTo(mobileNo)))
                .andExpect(jsonPath("employee.pin", equalTo(pin)))
                .andExpect(jsonPath("employee.balance", equalTo(finalBalance)));
    }

    @Test
    public void testBuy() throws Exception {
        //setup
        String employeeId = "1";
        String cardId = "6bb6b4c2c28b11e9";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        double initialBalance = 5.23;
        double purchaseAmount = 3.70;
        double finalBalance = initialBalance - purchaseAmount;

        ChangeBalanceRequest changeBalanceRequest = new ChangeBalanceRequest(cardId, purchaseAmount);
        ObjectMapper objectMapper = new ObjectMapper();
        String purchaseRequestJson = objectMapper.writeValueAsString(changeBalanceRequest);

        Employee employee = Employee.createNewMemberWithInitialBalance(cardId, employeeId, firstName, lastName, email, mobileNo, pin, finalBalance);
        EmployeeResource employeeResource = new EmployeeResource(employee);
        Mockito.when(memberService.buy(changeBalanceRequest)).thenReturn(employeeResource);

        //act
        mvc.perform(put("/buy").content(purchaseRequestJson).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("employee.employeeId").value(employeeId))
                .andExpect(jsonPath("employee.cardId", equalTo(cardId)))
                .andExpect(jsonPath("employee.firstName", equalTo(firstName)))
                .andExpect(jsonPath("employee.lastName", equalTo(lastName)))
                .andExpect(jsonPath("employee.email", equalTo(email)))
                .andExpect(jsonPath("employee.mobileNo", equalTo(mobileNo)))
                .andExpect(jsonPath("employee.pin", equalTo(pin)))
                .andExpect(jsonPath("employee.balance", equalTo(finalBalance)));
    }

    @Test
    public void testBuyIfInsufficientFunds() throws Exception {
        //setup
        String cardId = "6bb6b4c2c28b11e9";
        double initialBalance = 3.00;
        double purchaseAmount = 7.00;
        double finalBalance = initialBalance - purchaseAmount;
        String insufficientFundsError = "You have insufficient funds to carry out this purchase. Please top up and try again";

        ChangeBalanceRequest purchaseRequest = new ChangeBalanceRequest(cardId, purchaseAmount);
        ObjectMapper objectMapper = new ObjectMapper();
        String purchaseRequestJson = objectMapper.writeValueAsString(purchaseRequest);

        Mockito.when(memberService.buy(any(ChangeBalanceRequest.class))).thenThrow(InsufficientFundsException.class);

        //act
        mvc.perform(put("/buy").content(purchaseRequestJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest())
                .andExpect(mvcResult -> {
                    assertEquals(insufficientFundsError, mvcResult.getResponse().getErrorMessage());
                });
    }
}