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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Transactional
@Component("MemberServiceName")
public class MemberServiceImpl implements MemberService {

    private final MemberTranslator memberTranslator;

    @Autowired
    public MemberServiceImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Override
    public MemberDto newMember(MemberDto memberDto) {
        if(null == memberDto.getPhoneNumber()){
            memberDto.setMemId(1);
            memberDto.setPhoneNumber("0723949955");
            memberDto.setFirstName("Reynard");
            memberDto.setLastName("Engels");
            memberDto.setEmail("reynardengels@gmail.com");
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
