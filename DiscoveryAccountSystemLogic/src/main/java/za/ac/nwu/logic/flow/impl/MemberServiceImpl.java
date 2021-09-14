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
    private final ExchangeMediumTranslator exchangeMediumTranslator;

    @Autowired
    public MemberServiceImpl(MemberTranslator memberTranslator, ExchangeMediumTranslator exchangeMediumTranslator){
        this.memberTranslator = memberTranslator;
        this.exchangeMediumTranslator = exchangeMediumTranslator;
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
