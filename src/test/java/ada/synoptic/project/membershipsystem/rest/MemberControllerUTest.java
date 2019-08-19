package ada.synoptic.project.membershipsystem.rest;

import ada.synoptic.project.membershipsystem.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerUTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private MemberServiceImpl memberService;

    @Before
    public void setUp() {

    }

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
}
