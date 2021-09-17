package za.ac.nwu.logic.flow.impl;

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
    private final MemberTranslator memberTranslator;

    @Autowired
    public ViewMemberServiceImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Override
    public MemberDto getMemberByEmail(String email) {
        Member member = memberTranslator.getMemberByEmail(email);
        return null != member ? new MemberDto(member) : null;
    }

    @Override
    public MemberDto getMemberById(Integer id) {
        Member member = memberTranslator.getOneMember(id);
        return null != member ? new MemberDto(member) : null;
    }
}
