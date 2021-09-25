package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberTransactionDto;
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
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DiscoveryDB", "root", "King6");

    @Autowired
    public MemberTransactionTranslatorImpl(MemberTransactionRepository memberTransactionRepository) throws SQLException {
        this.memberTransactionRepository = memberTransactionRepository;
        con.setAutoCommit(false);
    }

    @Override
    public Member_Transaction addMemberTransaction(Member_Transaction memberTransaction) throws SQLException {
        Member_Transaction save = memberTransactionRepository.save(memberTransaction);
        con.commit();
        return save;
    }

    @Override
    public Member_Transaction getMemberTransactionID(Integer id) throws SQLException {
        Member_Transaction result = memberTransactionRepository.getByMtId(id);
        con.commit();
        return result;
    }

    @Override
    public Member_Transaction getTransactionByIdAndDate(Integer id, LocalDate date) throws SQLException {
        Member_Transaction result = memberTransactionRepository.getByMtIdAndTransactionDate(id, date);
        con.commit();
        return result;
    }
}
