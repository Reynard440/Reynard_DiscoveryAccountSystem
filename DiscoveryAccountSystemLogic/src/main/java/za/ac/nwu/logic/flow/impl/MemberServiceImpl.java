package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.logic.flow.MemberService;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;

@Transactional
@Component("MemberServiceName")
public class MemberServiceImpl implements MemberService {

    private final MemberTranslator memberTranslator;
    private final ExchangeMediumTranslator exchangeMediumTranslator;

    @Autowired
    public MemberServiceImpl(MemberTranslator memberTranslator, ExchangeMediumTranslator exchangeMediumTranslator){
        this.memberTranslator = memberTranslator;
        this.exchangeMediumTranslator = exchangeMediumTranslator;
    }

    @Override
    public MemberDto newMember(MemberDto memberDto) {
        Exchange_Medium exchangeMedium = exchangeMediumTranslator.getExchangeMediumByEmID(memberDto.getMemId());

        Member member = memberDto.buildMember();

        Member addedmember = memberTranslator.newMember(member);

        if(null == memberDto.getPhoneNumber()){
            memberDto.setPhoneNumber("0000000000");
            memberDto.setFirstName("Example First Name");
            memberDto.setLastName("Example Last Name");
            memberDto.setEmail("exampleEmail@gmail.com");
        }
        return new MemberDto(memberTranslator.newMember(addedmember));
    }

    @Override
    public void deleteMember(Integer id) {
        memberTranslator.deleteMember(id);
    }
}
