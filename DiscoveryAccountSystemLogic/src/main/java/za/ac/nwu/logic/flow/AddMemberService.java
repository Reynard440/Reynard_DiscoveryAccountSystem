package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberDto;

public interface AddMemberService {
    MemberDto create(MemberDto memberDto);
}
