package ada.synoptic.project.membershipsystem.rest;

import ada.synoptic.project.membershipsystem.domain.Employee;
import ada.synoptic.project.membershipsystem.domain.MemberService;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @CrossOrigin()
    @GetMapping("/employee")
    public Employee getEmployee(@RequestParam("employeeId") String employeeId) {
        return memberService.getEmployee(employeeId);
    }

    @CrossOrigin()
    @PostMapping("/newEmployee")
    public Employee registerNewEmployee(@RequestBody RegisterNewEmployeeRequest registerNewEmployeeRequest) {
        return memberService.registerNewEmployee(registerNewEmployeeRequest);
    }

}
