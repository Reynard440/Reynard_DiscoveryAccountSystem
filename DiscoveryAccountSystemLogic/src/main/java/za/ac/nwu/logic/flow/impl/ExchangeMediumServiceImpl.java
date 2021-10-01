package za.ac.nwu.logic.flow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.dto.MemberDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.domain.persistence.Member;
import za.ac.nwu.logic.flow.ExchangeMediumService;
import za.ac.nwu.translator.ExchangeMediumTranslator;
import za.ac.nwu.translator.MemberTranslator;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.LocalDate;

@Component("exchangeMediumViewFlow")
public class ExchangeMediumServiceImpl implements ExchangeMediumService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeMediumServiceImpl.class);
    private final ExchangeMediumTranslator exchangeMediumTranslator;
    private final MemberTranslator memberTranslator;

    @Autowired
    public ExchangeMediumServiceImpl(ExchangeMediumTranslator exchangeMediumTranslator, MemberTranslator memberTranslator){
        this.exchangeMediumTranslator = exchangeMediumTranslator;
        this.memberTranslator = memberTranslator;
    }

    @Transactional
    @Override
    public void increaseExchangeMediumTotal(Integer id, double amount) throws SQLException {
        LOGGER.info("The input for id is {} and the amount is {}", id, amount);
        exchangeMediumTranslator.increaseExchangeMediumTotal(id, amount);
    }

    @Transactional
    @Override
    public void decreaseExchangeMediumTotal(Integer id, double amount) throws SQLException {
        LOGGER.info("The input for id is {} and the amount is {}", id, amount);
        exchangeMediumTranslator.decreaseExchangeMediumTotal(id, amount);
    }

    @Transactional
    @Override
    public ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto) throws SQLException {
        if (null != exchangeMediumDto.getDate() && null != exchangeMediumDto.getDescription() && null != exchangeMediumDto.getType()) {
            LOGGER.info("The input object is {}", exchangeMediumDto);
            Member member = memberTranslator.getOneMember(exchangeMediumDto.getMemID().getMemId());
            Exchange_Medium exchangeMedium = exchangeMediumDto.buildExchangeMedium(member);

            Exchange_Medium addedExchangeMedium = exchangeMediumTranslator.newExchangeMedium(exchangeMedium);
            ExchangeMediumDto result = new ExchangeMediumDto(addedExchangeMedium);
            LOGGER.info("The return object is {}", result);
            return result;
        } else {
            throw new SQLException("Exchange Medium is null, rolling back the transaction.");
        }
    }

    @Override
    public boolean checkTypeExist(Integer id, String type) throws SQLException {
        LOGGER.info("The input for type is {} and the Exchange Medium id is {}", type, id);
        return exchangeMediumTranslator.checkTypeExists(id, type);
    }

    @Transactional
    @Override
    public void configureExchangeMedium(String type, String newType, double adjust, Integer mem, Integer id) throws SQLException {
        LOGGER.info("The input for type is {}, the newType is {}, the amount to be adjusted is {}, the MemberId is {}, and the Exchange Medium id is {}", type, newType, adjust, mem, id);
        exchangeMediumTranslator.configureExchangeMedium(type, newType, adjust, mem, id);
    }
}
