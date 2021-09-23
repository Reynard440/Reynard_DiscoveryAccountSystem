package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.repo.persistence.MemberRepository;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class MemberTranslatorImpl implements MemberTranslator {
    private final MemberRepository memberRepository;
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DiscoveryDB", "root", "King6");

    @Autowired
    public MemberTranslatorImpl(MemberRepository memberRepository) throws SQLException {
        this.memberRepository = memberRepository;
        con.setAutoCommit(false);
    }

    @Override
    public Member getOneMember(Integer id) throws SQLException {
        try{
            Member result = memberRepository.getById(id);
            con.commit();
            return result;
        }catch(Exception e){
            con.rollback();
            throw new SQLException("Rollback occurred while retrieving a member: ",e);
        }
    }

    @Override
    public Member newMember(Member member) throws SQLException {
        try {
            Member save = memberRepository.save(member);
            con.commit();
            return save;
        }catch(Exception e){
            con.rollback();
            throw new SQLException("Rollback occurred while creating a new member: ",e);
        }
    }

    @Override
    public Member getMemberByEmail(String email) throws SQLException {
        try{
            if (null == email) {
                email = "reynardengels@gmai.com";
            }
            Member result = memberRepository.getByEmail(email);
            con.commit();
            return result;
        }catch(Exception e){
            con.rollback();
            throw new SQLException("Rollback occurred while retrieving a member: ",e);
        }
    }
}
