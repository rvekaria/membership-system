package ada.synoptic.project.membershipsystem.rest;

import ada.synoptic.project.membershipsystem.domain.Member;
import ada.synoptic.project.membershipsystem.domain.MemberService;
import ada.synoptic.project.membershipsystem.rest.resource.CreateNewMemberRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @CrossOrigin()
    @GetMapping("/member")
    public Member getMember(@RequestParam("memberId") String memberId) {
        return memberService.getMember(memberId);
    }

    @CrossOrigin()
    @PostMapping("/newMember")
    public Member addNewMember(@RequestBody CreateNewMemberRequest CreateNewMemberRequest) {
        return memberService.addNewMember(CreateNewMemberRequest);
    }

}
