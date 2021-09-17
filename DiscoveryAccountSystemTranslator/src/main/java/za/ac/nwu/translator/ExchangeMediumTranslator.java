package za.ac.nwu.translator;

import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;

import java.util.List;

@Component
public interface ExchangeMediumTranslator {
    List<ExchangeMediumDto> getExchangeMediumDtos() ;

    Exchange_Medium getExchangeMediumByEmID(Integer emid);

    void increaseExchangeMediumTotal(Integer id, double amount);

    void decreaseExchangeMediumTotal(Integer id, double amount) ;

    boolean checkTypeExists(Integer id, String type);

    Exchange_Medium newExchangeMedium(Exchange_Medium exchange_medium) ;

    Exchange_Medium getExchangeMediumCurrentByTypeAndID(String type, Integer id);

    //void configureExchangeMedium(String type, String newType, double adjust, Integer mem, Integer id);
}