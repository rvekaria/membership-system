package ada.synoptic.project.membershipsystem.controller;

import ada.synoptic.project.membershipsystem.controller.exception.EmployeeNotFoundException;
import ada.synoptic.project.membershipsystem.controller.exception.InsufficientFundsException;
import ada.synoptic.project.membershipsystem.controller.resource.ChangeBalanceRequest;
import ada.synoptic.project.membershipsystem.controller.resource.EmployeeResource;
import ada.synoptic.project.membershipsystem.controller.resource.RegisterNewEmployeeRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;

@RestController
public class MemberController {

    private MemberService memberService;
    private HttpSession currentSession;
    private String sessionId;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @CrossOrigin()
    @PostMapping("/login")
    public String login(HttpSession incomingSession) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        this.currentSession = attr.getRequest().getSession(true);
        String incomingSessionId = incomingSession.getId();

        if (incomingSessionId.equals(this.sessionId)) {
            incomingSession.invalidate();
            return "Logged out";
        }
        this.sessionId = incomingSession.getId();
        return "Logged in";
    }

    @CrossOrigin()
    @PostMapping("/register")
    public EmployeeResource registerNewEmployee(@RequestBody RegisterNewEmployeeRequest registerNewEmployeeRequest) {
        System.out.println(this.currentSession.getId());

        return memberService.registerNewEmployee(registerNewEmployeeRequest);
    }

    @CrossOrigin()
    @GetMapping("/employee")
    public EmployeeResource getEmployeeByCardId(@RequestParam("cardId") String cardId) {
        System.out.println(this.currentSession.getId());

        try {
            return memberService.getEmployeeByCardId(cardId);
        } catch (EmployeeNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This card is not registered. Please register first to use the service", e);
        }
    }

    @CrossOrigin()
    @PutMapping("/topUpBalance")
    public EmployeeResource topUpBalance(@RequestBody ChangeBalanceRequest changeBalanceRequest) {
        System.out.println(this.currentSession.getId());

        return memberService.topUp(changeBalanceRequest);
    }

    @CrossOrigin()
    @PutMapping("/buy")
    public EmployeeResource buyFood(@RequestBody ChangeBalanceRequest changeBalanceRequest) {
        System.out.println(this.currentSession.getId());

        try {
            return memberService.buy(changeBalanceRequest);
        } catch (InsufficientFundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have insufficient funds to carry out this purchase. Please top up and try again", e);
        }
    }

}
