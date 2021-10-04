package za.ac.nwu.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.logic.flow.MemberService;
import za.ac.nwu.translator.MemberTranslator;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Component("memberServiceNameFlow")
public class MemberServiceImpl implements MemberService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final MemberTranslator memberTranslator;

    @Autowired
    public MemberServiceImpl(MemberTranslator memberTranslator){
        this.memberTranslator = memberTranslator;
    }

    @Transactional(rollbackFor = { SQLException.class, SQLIntegrityConstraintViolationException.class })
    @Override
    public MemberDto newMember(MemberDto memberDto) throws SQLException {
        try {
            if (memberDto.getEmail() == null) {
                throw new RuntimeException("Member cannot be null.");
            }
            LOGGER.info("The input object is {}", memberDto);
            Member member = memberDto.buildMember();
            Member addedMember = memberTranslator.newMember(member);
            MemberDto result = new MemberDto(memberTranslator.newMember(addedMember));
            LOGGER.info("The return object is {}", result);
            return result;
        } catch (SQLException e) {
            throw new SQLException("Member is null, rolling back the transaction.", e.getMessage());
        }
    }
}
