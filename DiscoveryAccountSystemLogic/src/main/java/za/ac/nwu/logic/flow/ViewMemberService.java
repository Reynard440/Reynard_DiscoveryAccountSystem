package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberDto;

import java.sql.SQLException;

public interface ViewMemberService {
    MemberDto getMemberByEmail(String email) throws SQLException;

    MemberDto getMemberById(Integer id) throws SQLException;
}
