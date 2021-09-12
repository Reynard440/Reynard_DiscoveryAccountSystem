package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberDto;

import java.util.List;

public interface MemberService {
    MemberDto newMember(MemberDto memberDto);

    void deleteMember(Integer id);

    List<MemberDto> getMembers();

    MemberDto getMemberByEmail(String email);

    MemberDto getMemberById(Integer id);
}
