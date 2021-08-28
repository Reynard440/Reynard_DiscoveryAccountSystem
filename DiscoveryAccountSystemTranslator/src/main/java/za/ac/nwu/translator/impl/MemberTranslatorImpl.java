package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.repo.persistence.MemberRepository;
import za.ac.nwu.repo.persistence.MemberTransactionRepository;
import za.ac.nwu.translator.MemberTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberTranslatorImpl implements MemberTranslator {

    private final MemberRepository memberRepository;
    private final MemberTransactionRepository memberTransactionRepository;

    @Autowired
    public MemberTranslatorImpl(MemberRepository memberRepository, MemberTransactionRepository memberTransactionRepository){
        this.memberRepository = memberRepository;
        this.memberTransactionRepository = memberTransactionRepository;
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
    public List<MemberTransactionDto> getMemberTransactionDtos() {
        List<MemberTransactionDto> memberTransactionDtos = new ArrayList<>();
        try{
            for (Member_Transaction member_transaction : memberTransactionRepository.findAll()){
                memberTransactionDtos.add(new MemberTransactionDto(member_transaction));
            }
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
        return memberTransactionDtos;
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

//    @Override
//    public MemberDto getMemberByEmail(String email) {
//        try{
//            Member member = memberRepository.getMemberByEmail(email);
//            return new MemberDto(member);
//        }catch(Exception e){
//            throw new RuntimeException("Could not read from the DB",e);
//        }
//    }
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
