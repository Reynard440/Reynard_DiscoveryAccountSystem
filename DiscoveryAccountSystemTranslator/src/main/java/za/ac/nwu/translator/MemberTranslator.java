package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;

import java.util.List;

@Component
public interface MemberTranslator {
    //All the listing methods
    List<Member> getAllMembers();

    //Get single record methods
    Member getOneMember(Integer id);
    Member getMemberByEmail(String email);

    //Creating methods
    Member newMember(Member member);

    void deleteMember(Integer id);
}
