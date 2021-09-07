package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberDto;

public interface MemberService {
    MemberDto newMember(MemberDto memberDto);

    void deleteMember(Integer id);
}
