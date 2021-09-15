package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.logic.flow.ViewMemberService;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component("ViewMemberService")
public class ViewMemberServiceImpl implements ViewMemberService {
    private final MemberTranslator memberTranslator;

    @Autowired
    public ViewMemberServiceImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Override
    public List<MemberDto> getMembers(){
        List<MemberDto> memberDtos = new ArrayList<>();
        for (Member member : memberTranslator.getAllMembers()) {
            memberDtos.add(new MemberDto(member));
        }
        return memberDtos;
    }

    @Override
    public MemberDto getMemberByEmail(String email) {
        Member member = memberTranslator.getMemberByEmail(email);
        return new MemberDto(member);
        //return null != member ? new MemberDto(member) : null;
    }

    @Override
    public MemberDto getMemberById(Integer id) {
        Member member = memberTranslator.getOneMember(id);
        return null != member ? new MemberDto(member) : null;
    }
}
