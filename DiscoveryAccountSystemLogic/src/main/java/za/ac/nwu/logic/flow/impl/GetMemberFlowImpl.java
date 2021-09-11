package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.logic.flow.MemberServiceFlow;
import za.ac.nwu.translator.MemberTranslator;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component("GetMemberFlow")
public class GetMemberFlowImpl implements MemberServiceFlow {

    private final MemberTranslator memberTranslator;

    @Autowired
    public GetMemberFlowImpl(MemberTranslator memberTranslator){
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
        return null != member ? new MemberDto(member) : null;
    }

    @Override
    public MemberDto getMemberById(Integer id) {
        Member member = memberTranslator.getOneMember(id);
        return null != member ? new MemberDto(member) : null;
    }
}
