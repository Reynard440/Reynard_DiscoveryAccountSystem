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
    MemberDto create(MemberDto member);

//    MemberDto getMemberByEmailNativeQuery(String email);
//
    MemberDto getMemberByEmail(String email);
//
//    MemberDto getMemberDtoByEmail(String email);
}
