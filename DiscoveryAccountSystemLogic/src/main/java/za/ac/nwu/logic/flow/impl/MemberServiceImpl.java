package za.ac.nwu.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.logic.flow.MemberService;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;

@Transactional
@Component("MemberServiceName")
public class MemberServiceImpl implements MemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final MemberTranslator memberTranslator;

    @Autowired
    public MemberServiceImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Override
    public MemberDto newMember(MemberDto memberDto) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("The input object is {}", memberDto);
        }
        if(null == memberDto.getPhoneNumber()){
            memberDto.setMemId(1);
            memberDto.setPhoneNumber("0723949955");
            memberDto.setFirstName("Reynard");
            memberDto.setLastName("Engels");
            memberDto.setEmail("reynardengels@gmail.com");
        }
        Member member = memberDto.buildMember();
        Member addedMember = memberTranslator.newMember(member);
        MemberDto result = new MemberDto(memberTranslator.newMember(addedMember));
        LOGGER.info("The return object is {}", result);
        return result;
    }
}
