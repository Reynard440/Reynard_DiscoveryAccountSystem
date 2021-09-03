package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;

import java.util.List;

@Component
public interface MemberTranslator {
    //All the listing methods
    List<MemberDto> getAllMembers();

    //Get single record methods
    MemberDto getOneMemberDto(Integer id);

    //Creating methods
    MemberDto newMember(MemberDto memberDto);

//    MemberDto getMemberByEmailNativeQuery(String email);
//
    MemberDto getMemberByEmail(String email);

    void deleteMember(String phone);
//
//    MemberDto getMemberDtoByEmail(String email);
}
