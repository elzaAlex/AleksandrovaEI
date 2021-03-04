package com.computerAccessoriesStore.service;

import com.computerAccessoriesStore.models.CreditCard;
import com.computerAccessoriesStore.transfer.CreditCardDTO;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {
    List<CreditCard> findAll();

    void add(CreditCardDTO dto);

    void deleteById(Long id);

    Optional<CreditCard> getById(Long id);

    void edit(CreditCardDTO dto);

    void edit(CreditCard dto);

    List<CreditCard> findAllByBuyerId(Long id);
}
