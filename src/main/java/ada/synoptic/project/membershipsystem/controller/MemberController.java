package ada.synoptic.project.membershipsystem.controller;

import ada.synoptic.project.membershipsystem.controller.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.controller.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.controller.resource.ChangeBalanceRequest;
import ada.synoptic.project.membershipsystem.controller.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.controller.resource.RegisterNewEmployeeRequest;
import ada.synoptic.project.membershipsystem.model.Employee;
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

    @CrossOrigin()
    @PutMapping("/topUpBalance")
    public EmployeeResource topUpBalance(@RequestBody ChangeBalanceRequest changeBalanceRequest) {
        return memberService.topUp(changeBalanceRequest);
    }

    @CrossOrigin()
    @PutMapping("/buy")
    public EmployeeResource buyFood(@RequestBody ChangeBalanceRequest changeBalanceRequest) {
        try {
            return memberService.buy(changeBalanceRequest);
        } catch (InsufficientFundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have insufficient funds to carry out this purchase. Please top up and try again", e);
        }
    }

}
