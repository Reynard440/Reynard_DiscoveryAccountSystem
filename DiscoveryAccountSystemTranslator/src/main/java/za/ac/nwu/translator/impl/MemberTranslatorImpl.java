package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.repo.persistence.MemberRepository;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class MemberTranslatorImpl implements MemberTranslator {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberTranslatorImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member getOneMember(Integer id) {
        try{
            return memberRepository.getById(id);
        }catch(Exception e){
            throw new RuntimeException("An error occurred while getting the member by id.", e);
        }
    }

    @Override
    public Member newMember(Member member) {
        try {
            return memberRepository.save(member);
        }catch(Exception e){
            throw new RuntimeException("Could not add member to the DB",e);
        }
    }

    @Override
    public Member getMemberByEmail(String email) {
        try{
            if (null == email) {
                email = "reynardengels@gmai.com";
            }
            return memberRepository.getByEmail(email);
        }catch(Exception e){
            throw new RuntimeException("Could not read from the DB",e);
        }
    }
}
