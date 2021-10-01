package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.persistence.Member;

import java.sql.SQLException;

@Component
public interface MemberTranslator {
    Member getOneMember(Integer id) throws SQLException;

    Member getMemberByEmail(String email) throws SQLException;

    Member newMember(Member member) throws SQLException;
}


