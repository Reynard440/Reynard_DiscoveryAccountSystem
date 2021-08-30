package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.repo.persistence.ExchangeMediumRepository;
import za.ac.nwu.repo.persistence.MemberRepository;
import za.ac.nwu.repo.persistence.MemberTransactionRepository;
import za.ac.nwu.translator.MemberTranslator;
import za.ac.nwu.translator.config.TranslatorConfig;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberTranslatorImpl implements MemberTranslator {

    private final MemberRepository memberRepository;
    private final MemberTransactionRepository memberTransactionRepository;
    private final ExchangeMediumRepository exchangeMediumRepository;

    @Autowired
    public MemberTranslatorImpl(MemberRepository memberRepository,
                                MemberTransactionRepository memberTransactionRepository,
                                ExchangeMediumRepository exchangeMediumRepository){
        this.memberRepository = memberRepository;
        this.memberTransactionRepository = memberTransactionRepository;
        this.exchangeMediumRepository = exchangeMediumRepository;
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
    public List<ExchangeMediumDto> getExchangeMediumDtos(){
        List<ExchangeMediumDto> exchangeMediumDtos = new ArrayList<>();
        try{
            for (Exchange_Medium exchange_medium : exchangeMediumRepository.findAll()){
                exchangeMediumDtos.add(new ExchangeMediumDto(exchange_medium));
            }
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
        return exchangeMediumDtos;
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

    @Override
    public MemberTransactionDto addMemberTransaction(MemberTransactionDto memberTransactionDto) {
        try{
            Member_Transaction memberTransaction = memberTransactionRepository.save(memberTransactionDto.getMemberTransaction());
            return new MemberTransactionDto(memberTransaction);
        }catch(Exception e){
            throw new RuntimeException("Could not add member transaction to the DB",e);
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
