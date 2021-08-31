package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.repo.persistence.MemberRepository;
import za.ac.nwu.translator.MemberTranslator;

import java.util.ArrayList;
import java.util.List;


public class MemberTranslatorImpl implements MemberTranslator {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberTranslatorImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MemberDto> getAllMembers(){

        List<MemberDto> memberDtos = new ArrayList<>();
        try{
            for (Member member : memberRepository.findAll()){
                memberDtos.add(new MemberDto(member));
            }
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
        return memberDtos;
    }

    @Override
    public MemberDto getOneMemberDto(Integer id) {
        try{
            Member member = memberRepository.getMemberById(id);
            return new MemberDto(member);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public MemberDto create(MemberDto memberDto) {
        try {
            Member member = memberRepository.save(memberDto.getMember());
            return new MemberDto(member);
        }catch(Exception e){
            throw new RuntimeException("Could not add member to the DB",e);
        }
    }

//    @Override
//    public MemberDto getMemberByEmailNativeQuery(String email) {
//        try{
//            Member member = memberRepository.getMemberByEmailNativeQuery(email);
//            return new MemberDto(member);
//        }catch(Exception e){
//            throw new RuntimeException("Could not read from the DB",e);
//        }
//    }

    @Override
    public MemberDto getMemberByEmail(String email) {
        try{
            Member member = memberRepository.getMemberByEmail(email);
            return new MemberDto(member);
        }catch(Exception e){
            throw new RuntimeException("Could not read from the DB",e);
        }
    }
//
//    @Override
//    public MemberDto getMemberDtoByEmail(String email) {
//        try{
//            Member member = memberRepository.getMemberDtoByEmail(email);
//            return new MemberDto(member);
//        }catch(Exception e){
//            throw new RuntimeException("Could not read from the DB",e);
//        }
//    }
}
