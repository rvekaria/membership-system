package ada.synoptic.project.membershipsystem.rest;

import ada.synoptic.project.membershipsystem.domain.Employee;
import ada.synoptic.project.membershipsystem.domain.MemberService;
import ada.synoptic.project.membershipsystem.rest.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.rest.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.rest.resource.RegisterNewEmployeeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @CrossOrigin()
    @GetMapping("/employee")
    public EmployeeResource getEmployeeByCardId(@RequestParam("cardId") String cardId) {
        try {
            return memberService.getEmployeeByCardId(cardId);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This card is not registered. Please register first to use the service", e);
        }
    }

    @CrossOrigin()
    @PostMapping("/register")
    public Employee registerNewEmployee(@RequestBody RegisterNewEmployeeRequest registerNewEmployeeRequest) {
        return memberService.registerNewEmployee(registerNewEmployeeRequest);
    }

}
