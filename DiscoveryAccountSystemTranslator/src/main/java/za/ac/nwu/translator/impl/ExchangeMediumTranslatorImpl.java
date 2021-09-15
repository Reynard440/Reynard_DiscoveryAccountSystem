package za.ac.nwu.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.domain.dto.ExchangeMediumDto;
import za.ac.nwu.domain.persistence.Exchange_Medium;
import za.ac.nwu.repo.persistence.ExchangeMediumRepository;
import za.ac.nwu.translator.ExchangeMediumTranslator;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class ExchangeMediumTranslatorImpl implements ExchangeMediumTranslator {
    private final ExchangeMediumRepository exchangeMediumRepository;

    @Autowired
    public ExchangeMediumTranslatorImpl(ExchangeMediumRepository exchangeMediumRepository) {
        this.exchangeMediumRepository = exchangeMediumRepository;
    }

    @Override
    public List<ExchangeMediumDto> getExchangeMediumDtos(){
        try{
            List<ExchangeMediumDto> exchangeMediumDtos = new ArrayList<>();
            for (Exchange_Medium exchange_medium : exchangeMediumRepository.findAll()){
                exchangeMediumDtos.add(new ExchangeMediumDto(exchange_medium));
            }
            return exchangeMediumDtos;
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public Exchange_Medium getExchangeMediumByEmID(Integer emid) {
        try{
            Exchange_Medium exchange_medium = exchangeMediumRepository.getByEM_ID(emid);
            return exchange_medium;
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public void increaseExchangeMediumTotal(Integer id, double amount) {
        try{
            exchangeMediumRepository.increaseBalance(amount, id);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public void decreaseExchangeMediumTotal(Integer id, double amount) {
        try{
            exchangeMediumRepository.decreaseBalance(amount, id);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public boolean checkTypeExists(Integer id, String type) {
        try{
            boolean response = exchangeMediumRepository.existsByTypeAndMemID_Id(type, id);
            return response;
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }

    @Override
    public Exchange_Medium newExchangeMedium(Exchange_Medium exchange_medium) {
        try {
            return exchangeMediumRepository.save(exchange_medium);
        }catch(Exception e){
            throw new RuntimeException("Could not add member to the DB",e);
        }
    }

    @Override
    public Exchange_Medium getExchangeMediumCurrentByTypeAndID(String type, Integer id) {
        try{
            return exchangeMediumRepository.getExchangeMediumCurrentByTypeAndID(type, id);
        }catch(Exception e){
            throw new RuntimeException("Unable to read from the DB", e);
        }
    }
}
