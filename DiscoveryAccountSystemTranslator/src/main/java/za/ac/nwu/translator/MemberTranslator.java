package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;

import java.sql.SQLException;
import java.util.List;

@Component
public interface MemberTranslator {
    Member getOneMember(Integer id) throws SQLException;

    Member getMemberByEmail(String email) throws SQLException;

    Member newMember(Member member) throws SQLException;
}


