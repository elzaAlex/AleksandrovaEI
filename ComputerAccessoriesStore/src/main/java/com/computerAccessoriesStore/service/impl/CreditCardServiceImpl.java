package com.computerAccessoriesStore.service.impl;

import com.computerAccessoriesStore.models.CreditCard;
import com.computerAccessoriesStore.models.User;
import com.computerAccessoriesStore.repository.CreditCardRepository;
import com.computerAccessoriesStore.service.CreditCardService;
import com.computerAccessoriesStore.transfer.CreditCardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public List<CreditCard> findAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public void add(CreditCardDTO dto) {
        CreditCard creditCard = CreditCard.builder()
                .firsName(dto.getFirsName())
                .lastName(dto.getLastName())
                .card_code(dto.getCard_code())
                .balance(dto.getBalance())
                .cvv(dto.getCvv())
                .month_year(dto.getMonth_year())
                .buyer(User.builder().id(dto.getIdBuyer()).build()).build();
        creditCardRepository.save(creditCard);
    }

    @Override
    public void deleteById(Long id) {
        creditCardRepository.deleteById(id);
    }

    @Override
    public Optional<CreditCard> getById(Long id) {
        return creditCardRepository.findById(id);
    }

    @Override
    public void edit(CreditCardDTO dto) {
        CreditCard creditCard = CreditCard.builder()
                .id(dto.getId())
                .firsName(dto.getFirsName())
                .lastName(dto.getLastName())
                .card_code(dto.getCard_code())
                .balance(dto.getBalance())
                .cvv(dto.getCvv())
                .month_year(dto.getMonth_year())
                .buyer(User.builder().id(dto.getId()).build()).build();
        creditCardRepository.save(creditCard);
    }

    @Override
    public void edit(CreditCard dto) {
        creditCardRepository.save(dto);
    }

    @Override
    public List<CreditCard> findAllByBuyerId(Long id) {
        return creditCardRepository.findAllByBuyerId(id);
    }
}
