package ada.synoptic.project.membershipsystem.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MemberRepository extends MongoRepository<Member, String> {

    public Member findTopByOrderByMemberIdDesc();
    public Member findByMemberId(int memberId);
    public List<Member> findByFirstName(String firstName);
    public List<Member> findByLastName(String lastName);
}
