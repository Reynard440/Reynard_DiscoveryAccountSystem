package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;

import java.util.List;

@Component
public interface MemberTranslator {
    //All the listing methods
    List<MemberDto> getAllMembers();
    List<MemberTransactionDto> getMemberTransactionDtos();
    List<ExchangeMediumDto> getExchangeMediumDtos();

    //Creating methods
    MemberDto create(MemberDto member);
    //MemberTransactionDto add(MemberTransactionDto memberTransactionDto);

//    MemberDto getMemberByEmailNativeQuery(String email);
//
    MemberDto getMemberByEmail(String email);
//
//    MemberDto getMemberDtoByEmail(String email);
}
