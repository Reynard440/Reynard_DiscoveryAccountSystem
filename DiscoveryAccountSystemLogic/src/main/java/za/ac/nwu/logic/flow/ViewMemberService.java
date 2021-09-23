package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberDto;

import java.sql.SQLException;
import java.util.List;

public interface ViewMemberService {
    MemberDto getMemberByEmail(String email) throws SQLException;

    MemberDto getMemberById(Integer id) throws SQLException;
}
