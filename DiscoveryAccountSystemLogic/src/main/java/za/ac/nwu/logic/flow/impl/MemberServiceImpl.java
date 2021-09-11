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
import java.util.Set;

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

        if(null == memberDto.getPhoneNumber()){
            memberDto.setPhoneNumber("0000000000");
            memberDto.setFirstName("Example First Name");
            memberDto.setLastName("Example Last Name");
            memberDto.setEmail("exampleEmail@gmail.com");
        }
        Member member = memberDto.buildMember();

        Member addedMember = memberTranslator.newMember(member);

        return new MemberDto(memberTranslator.newMember(addedMember));
    }

    @Override
    public void deleteMember(Integer id) {
        if (null == id) {
            id = 1;
        }
        memberTranslator.deleteMember(id);
    }
}
