package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.repo.persistence.MemberTransactionRepository;
import za.ac.nwu.translator.MemberTransactionTranslator;

import java.sql.SQLException;
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

    @Transactional(rollbackFor = { SQLException.class })
    @Override
    public Member_Transaction addMemberTransaction(Member_Transaction memberTransaction) throws RuntimeException {
        return memberTransactionRepository.save(memberTransaction);
    }

    @Override
    public List<Member_Transaction> getMemberTransactionID(Integer id) throws RuntimeException  {
        return new ArrayList<>(memberTransactionRepository.getByEmId(id));
    }

    @Override
    public List<Member_Transaction> getTransactionByIdAndDate(Integer id, LocalDate date) throws RuntimeException {
        return new ArrayList<>(memberTransactionRepository.getByMtIdAndTransactionDate(id, date));
    }
}
