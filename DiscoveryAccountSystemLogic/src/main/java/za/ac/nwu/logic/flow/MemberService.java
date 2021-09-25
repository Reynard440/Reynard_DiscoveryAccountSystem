package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberDto;

import java.sql.SQLException;

public interface MemberService {
    MemberDto newMember(MemberDto memberDto) throws SQLException;
}
