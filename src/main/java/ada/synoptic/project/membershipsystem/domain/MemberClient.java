package ada.synoptic.project.membershipsystem.domain;

import ada.synoptic.project.membershipsystem.rest.resource.CreateNewMemberRequest;

public interface MemberClient {
    Member getMember(String memberId);

    Member addNewMember(CreateNewMemberRequest CreateNewMemberRequest);
}
