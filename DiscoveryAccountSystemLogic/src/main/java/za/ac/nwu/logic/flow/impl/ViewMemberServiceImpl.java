package za.ac.nwu.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.logic.flow.ViewMemberService;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;

@Transactional
@Component("ViewMemberService")
public class ViewMemberServiceImpl implements ViewMemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewMemberServiceImpl.class);
    private final MemberTranslator memberTranslator;

    @Autowired
    public ViewMemberServiceImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Override
    public MemberDto getMemberByEmail(String email) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("The input for Member email is {}", email);
        }
        Member member = memberTranslator.getMemberByEmail(email);
        MemberDto result = null != member ? new MemberDto(member) : null;
        LOGGER.info("The return object is {} ", result);
        return result;
    }

    @Override
    public MemberDto getMemberById(Integer id) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("The input for Member id is {}", id);
        }
        Member member = memberTranslator.getOneMember(id);
        MemberDto result = null != member ? new MemberDto(member) : null;
        LOGGER.info("The return object is {} ", result);
        return result;
    }
}
