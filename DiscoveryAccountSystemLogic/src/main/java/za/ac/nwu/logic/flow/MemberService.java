package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberDto;

import java.sql.SQLException;
import java.util.List;

public interface MemberService {
    MemberDto newMember(MemberDto memberDto) throws SQLException;
}
