package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.repo.persistence.ExchangeMediumRepository;
import za.ac.nwu.translator.ExchangeMediumTranslator;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExchangeMediumTranslatorImpl implements ExchangeMediumTranslator {
    private final ExchangeMediumRepository exchangeMediumRepository;

    @Autowired
    public ExchangeMediumTranslatorImpl(ExchangeMediumRepository exchangeMediumRepository) {
        this.exchangeMediumRepository = exchangeMediumRepository;
    }

    @Override
    public List<ExchangeMediumDto> getExchangeMediumDtos(){
        List<ExchangeMediumDto> exchangeMediumDtos = new ArrayList<>();
        try{
            for (Exchange_Medium exchange_medium : exchangeMediumRepository.findAll()){
                exchangeMediumDtos.add(new ExchangeMediumDto(exchange_medium));
            }
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
        return exchangeMediumDtos;
    }

    @Override
    public ExchangeMediumDto getExchangeMediumByEmID(Integer emid) {
        try{
            Exchange_Medium exchange_medium = exchangeMediumRepository.getAllExchangeMediumByEmID(emid);
            return new ExchangeMediumDto(exchange_medium);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public ExchangeMediumDto getExchangeMediumByTypeAndID(String type, Integer id) {
        try{
            Exchange_Medium exchange_medium = exchangeMediumRepository.getExchange_MediumByTypeAndID(type, id);
            return new ExchangeMediumDto(exchange_medium);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public ExchangeMediumDto increaseExchangeMediumTotal(Integer id, Double amount) {
        try{
            Exchange_Medium exchange_medium = exchangeMediumRepository.increaseBalance(amount, id);
            return new ExchangeMediumDto(exchange_medium);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public ExchangeMediumDto decreaseExchangeMediumTotal(Integer id, Double amount) {
        try{
            Exchange_Medium exchange_medium = exchangeMediumRepository.decreaseBalance(amount, id);
            return new ExchangeMediumDto(exchange_medium);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public Integer checkTypeExists(Integer id, String type) {
        try{
            Integer response = exchangeMediumRepository.checkTypeExist(id, type);
            return new Integer(response);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public ExchangeMediumDto newExchangeMedium(ExchangeMediumDto exchangeMediumDto) {
        try {
            Exchange_Medium exchange_medium = exchangeMediumRepository.save(exchangeMediumDto.getExchangeMedium());
            return new ExchangeMediumDto(exchange_medium);
        }catch(Exception e){
            throw new RuntimeException("Could not add member to the DB",e);
        }
    }
}
