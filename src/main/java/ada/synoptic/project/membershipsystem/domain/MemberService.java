package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.CreateNewMemberRequest;

public interface MemberService {
    Member getMember(String memberId);

    Member addNewMember(CreateNewMemberRequest CreateNewMemberRequest);
}
