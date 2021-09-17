package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;

import java.util.List;

@Component
public interface MemberTranslator {
    Member getOneMember(Integer id);

    Member getMemberByEmail(String email);

    Member newMember(Member member);

    void deleteMember(Integer id);
}


