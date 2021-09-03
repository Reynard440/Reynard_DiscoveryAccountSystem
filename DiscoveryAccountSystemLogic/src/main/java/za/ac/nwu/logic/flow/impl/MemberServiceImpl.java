package za.ac.nwu.logic.flow.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.logic.flow.MemberService;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;

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
        if(null == memberDto.getMem_Phone_Number()){
            memberDto.setMem_Phone_Number("0000000000");
        }
        return memberTranslator.newMember(memberDto);
    }

    @Override
    public void deleteMember(String phone) {
        memberTranslator.deleteMember(phone);
    }
}
