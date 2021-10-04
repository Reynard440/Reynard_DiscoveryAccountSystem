package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.repo.persistence.MemberRepository;
import za.ac.nwu.translator.MemberTranslator;

import java.sql.SQLException;

@Component
public class MemberTranslatorImpl implements MemberTranslator {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberTranslatorImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member getOneMember(Integer id) throws RuntimeException {
        return memberRepository.getById(id);
    }

    @Transactional(rollbackFor = { SQLException.class })
    @Override
    public Member newMember(Member member) throws SQLException {
        return memberRepository.save(member);
    }

    @Override
    public Member getMemberByEmail(String email) throws RuntimeException {
        return memberRepository.getByEmail(email);
    }
}
