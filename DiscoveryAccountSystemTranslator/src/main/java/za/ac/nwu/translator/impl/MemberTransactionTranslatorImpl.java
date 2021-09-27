package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.domain.persistence.Member_Transaction;
import za.ac.nwu.repo.persistence.MemberTransactionRepository;
import za.ac.nwu.translator.MemberTransactionTranslator;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    public Member_Transaction addMemberTransaction(Member_Transaction memberTransaction) throws RuntimeException  {
        return memberTransactionRepository.save(memberTransaction);
    }

    @Override
    public Member_Transaction getMemberTransactionID(Integer id) throws RuntimeException  {
        return memberTransactionRepository.getByMtId(id);
    }

    @Override
    public Member_Transaction getTransactionByIdAndDate(Integer id, LocalDate date) throws RuntimeException {
        return memberTransactionRepository.getByMtIdAndTransactionDate(id, date);
    }
}
