package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.repo.persistence.MemberTransactionRepository;
import za.ac.nwu.translator.MemberTransactionTranslator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MemberTransactionTranslatorImpl implements MemberTransactionTranslator {
    private final MemberTransactionRepository memberTransactionRepository;

    @Autowired
    public MemberTransactionTranslatorImpl(MemberTransactionRepository memberTransactionRepository) {
        this.memberTransactionRepository = memberTransactionRepository;
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
    public MemberTransactionDto addMemberTransaction(MemberTransactionDto memberTransactionDto) {
        try{
            Member_Transaction memberTransaction = memberTransactionRepository.save(memberTransactionDto.getMemberTransaction());
            return new MemberTransactionDto(memberTransaction);
        }catch(Exception e){
            throw new RuntimeException("Could not add member transaction to the DB",e);
        }
    }

    @Override
    public MemberTransactionDto getMemberTransactionID(Integer id) {
        try{
            Member_Transaction member_transaction = memberTransactionRepository.getByMtId(id);
            return new MemberTransactionDto(member_transaction);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public MemberTransactionDto getTransactionByIdAndDate(Integer id, LocalDate date) {
        try{
            Member_Transaction member_transaction = memberTransactionRepository.getByMtIdAndTransactionDate(id, date);
            return new MemberTransactionDto(member_transaction);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }
}
