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
    public List<Member> getAllMembers(){

        List<Member> member = new ArrayList<>();
        try{
            for (Member members : memberRepository.findAll()){
                member.add(members);
            }
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
        return member;
    }

    @Override
    public Member getOneMember(Integer id) {
        try{
            return memberRepository.getById(id);
        }catch(Exception e){
            throw new NullPointerException();
        }
    }

    @Override
    public Member newMember(Member member) {
        try {
            return memberRepository.save(member);
            //return new MemberDto(member);
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
            Member member = memberRepository.getByEmail(email);
            return member;
        }catch(Exception e){
            throw new RuntimeException("Could not read from the DB",e);
        }
    }

    @Override
    public void deleteMember(Integer id) {
        try {
            memberRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Could not delete member from the DB",e);
        }
    }
}
