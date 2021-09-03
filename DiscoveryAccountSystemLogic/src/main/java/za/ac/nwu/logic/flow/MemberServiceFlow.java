package za.ac.nwu.logic.flow;

import za.ac.nwu.domain.dto.MemberDto;

import java.util.List;

public interface MemberServiceFlow {
    List<MemberDto> getMembers();

    MemberDto getMemberByEmail(String email);

    MemberDto getMemberById(Integer id);

    /*MemberDto getMemberByEmail(String email)*/
}
