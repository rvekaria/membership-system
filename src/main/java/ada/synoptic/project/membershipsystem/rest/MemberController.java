package ada.synoptic.project.membershipsystem.rest;

import ada.synoptic.project.membershipsystem.domain.Employee;
import ada.synoptic.project.membershipsystem.domain.MemberService;
import ada.synoptic.project.membershipsystem.rest.resource.CreateNewEmployeeRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @CrossOrigin()
    @GetMapping("/employee")
    public Employee getMember(@RequestParam("employeeId") String employeeId) {
        return memberService.getEmployee(employeeId);
    }

    @CrossOrigin()
    @PostMapping("/newEmployee")
    public Employee addNewMember(@RequestBody CreateNewEmployeeRequest CreateNewEmployeeRequest) {
        return memberService.addNewEmployee(CreateNewEmployeeRequest);
    }

}
