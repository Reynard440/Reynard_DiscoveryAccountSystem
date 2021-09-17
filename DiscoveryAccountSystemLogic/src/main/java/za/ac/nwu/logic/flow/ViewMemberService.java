package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberDto;

import java.util.List;

public interface ViewMemberService {
    MemberDto getMemberByEmail(String email);

    MemberDto getMemberById(Integer id);
}
