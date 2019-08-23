package ada.synoptic.project.membershipsystem.controller;

import ada.synoptic.project.membershipsystem.controller.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.controller.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.controller.resource.ChangeBalanceRequest;
import ada.synoptic.project.membershipsystem.controller.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.controller.resource.RegisterNewEmployeeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@RestController
public class MemberController {

    private MemberService memberService;
    private String sessionId;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        this.sessionId = "";
    }

    @CrossOrigin()
    @PostMapping("/login")
    public EmployeeResource login(HttpSession incomingSession, Authentication authentication) {
        String incomingSessionId = incomingSession.getId();

        if (incomingSessionId.equals(this.sessionId)) {
            incomingSession.invalidate();
            return new EmployeeResource(null, "Goodbye");
        } else {
            this.sessionId = incomingSession.getId();
        }

        String cardId = authentication.getName();
        try {
            return memberService.getEmployeeByCardId(cardId);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This card is not registered. Please register first to use the service", e);
        }
    }

    @CrossOrigin()
    @PostMapping("/register")
    public EmployeeResource registerNewEmployee(@RequestBody RegisterNewEmployeeRequest registerNewEmployeeRequest) {
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
