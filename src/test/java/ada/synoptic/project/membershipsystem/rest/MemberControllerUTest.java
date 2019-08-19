package ada.synoptic.project.membershipsystem.rest;

import ada.synoptic.project.membershipsystem.domain.Employee;
import ada.synoptic.project.membershipsystem.domain.MemberServiceImpl;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
@EnableWebMvc
public class MemberControllerUTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MemberServiceImpl memberService;

    @Test
    public void testGetEmployeeEndpoint() throws Exception {
        //setup
        String employeeId = "1";
        String cardNumber = "flksdunro7q4ybcor";
        String firstName = "First";
        String lastName = "Last";
        String email = "Email";
        String mobileNo = "075 43875489127";
        String pin = "8628";
        Employee employee = Employee.createNewMember(cardNumber, employeeId, firstName, lastName, email, mobileNo, pin);

        Mockito.when(memberService.getEmployee(employeeId)).thenReturn(employee);

        //act
        mvc.perform(get("/employee?employeeId=" + employeeId)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("employeeId", equalTo(employeeId)))
                .andExpect(jsonPath("cardNumber", equalTo(cardNumber)))
                .andExpect(jsonPath("firstName", equalTo(firstName)))
                .andExpect(jsonPath("lastName", equalTo(lastName)))
                .andExpect(jsonPath("email", equalTo(email)))
                .andExpect(jsonPath("mobileNo", equalTo(mobileNo)))
                .andExpect(jsonPath("pin", equalTo(pin)));
    }

    @Test
    public void testRegisterNewEmployeeEndpoint() throws Exception {
        //setup
        String employeeId = "1";
        String cardNumber = "f34shdrlnvt834";
        String firstName = "New";
        String lastName = "Guy";
        String email = "NewEmail";
        String mobileNo = "107824231";
        String pin = "3589";

        RegisterNewEmployeeRequest registerNewEmployeeRequest = new RegisterNewEmployeeRequest(cardNumber, employeeId, firstName, lastName, email, mobileNo, pin);
        ObjectMapper objectMapper = new ObjectMapper();
        String registerNewEmployeeRequestJson = objectMapper.writeValueAsString(registerNewEmployeeRequest);

        Employee employee = Employee.createNewMember(cardNumber, employeeId, firstName, lastName, email, mobileNo, pin);

        Mockito.when(memberService.registerNewEmployee(registerNewEmployeeRequest)).thenReturn(employee);

        //act
        mvc.perform(post("/newEmployee").content(registerNewEmployeeRequestJson).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andDo(mvcResult -> {
                    System.out.println(mvcResult.getResponse().toString());
                })
                .andExpect(jsonPath("employeeId").value(employeeId))
                .andExpect(jsonPath("cardNumber", equalTo(cardNumber)))
                .andExpect(jsonPath("firstName", equalTo(firstName)))
                .andExpect(jsonPath("lastName", equalTo(lastName)))
                .andExpect(jsonPath("email", equalTo(email)))
                .andExpect(jsonPath("mobileNo", equalTo(mobileNo)))
                .andExpect(jsonPath("pin", equalTo(pin))
                )
        ;
    }
}