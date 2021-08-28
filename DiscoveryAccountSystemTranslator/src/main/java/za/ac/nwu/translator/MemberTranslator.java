package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;

import java.util.List;

@Component
public interface MemberTranslator {
    List<MemberDto> getAllMembers();

    MemberDto create(MemberDto member);

//    MemberDto getMemberByEmailNativeQuery(String email);
//
//    MemberDto getMemberByEmail(String email);
//
//    MemberDto getMemberDtoByEmail(String email);
}
