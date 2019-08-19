package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.CreateNewMemberRequest;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberClient memberClient;

    public MemberServiceImpl(MemberClient memberClient) {
        this.memberClient = memberClient;
    }

    @Override
    public Member getMember(String memberId) {
        return memberClient.getMember(memberId);
    }

    @Override
    public Member addNewMember(CreateNewMemberRequest CreateNewMemberRequest) {
        Member member = memberClient.addNewMember(CreateNewMemberRequest);
        return member;
    }
}
