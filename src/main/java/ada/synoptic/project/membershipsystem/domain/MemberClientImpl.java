package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.CreateNewMemberRequest;
import org.springframework.stereotype.Component;

@Component
public class MemberClientImpl implements MemberClient {

    private final MemberRepository repository;

    public MemberClientImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public Member getMember(String memberId) {
        return repository.findByMemberId(Integer.parseInt(memberId));
    }

    @Override
    public Member addNewMember(CreateNewMemberRequest CreateNewMemberRequest) {
        Member newMember = Member.createNewMember(CreateNewMemberRequest.getFirstName(), CreateNewMemberRequest.getLastName());
        repository.save(newMember);
        return newMember;
    }
}
