package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.repo.persistence.MemberTransactionRepository;
import za.ac.nwu.translator.MemberTransactionTranslator;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class MemberTransactionTranslatorImpl implements MemberTransactionTranslator {
    private final MemberTransactionRepository memberTransactionRepository;

    @Autowired
    public MemberTransactionTranslatorImpl(MemberTransactionRepository memberTransactionRepository) {
        this.memberTransactionRepository = memberTransactionRepository;
    }

    @Override
    public Member_Transaction addMemberTransaction(Member_Transaction memberTransaction) {
        try{
            return memberTransactionRepository.save(memberTransaction);
        }catch(Exception e){
            throw new RuntimeException("Could not add member transaction to the DB",e);
        }
    }

    @Override
    public Member_Transaction getMemberTransactionID(Integer id) {
        try{
            return memberTransactionRepository.getByMtId(id);
            //return new MemberTransactionDto(member_transaction);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public Member_Transaction getTransactionByIdAndDate(Integer id, LocalDate date) {
        try{
            return memberTransactionRepository.getByMtIdAndTransactionDate(id, date);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }
}
