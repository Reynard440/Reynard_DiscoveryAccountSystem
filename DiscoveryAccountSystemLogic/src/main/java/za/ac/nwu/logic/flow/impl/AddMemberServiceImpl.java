package za.ac.nwu.logic.flow.impl;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.logic.flow.AddMemberService;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;

@Transactional
@Component("createMemberFlowName")
public class AddMemberServiceImpl implements AddMemberService {

    private final MemberTranslator memberTranslator;

    public AddMemberServiceImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Override
    public MemberDto create(MemberDto member){
        if(null == member.getMem_Email()){
            member.setMem_Email("rhinogiezing7@gmail.com");
        }
        return memberTranslator.create(member);
    }

}
