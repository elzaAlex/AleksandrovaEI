package com.computerAccessoriesStore.service;

import com.computerAccessoriesStore.models.Act;
import com.computerAccessoriesStore.transfer.ActDTO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface ActService {
    List<Act> findAll();

    void add(ActDTO dto);

    void deleteById(Long id);

    Optional<Act> getById(Long id);

    void edit(ActDTO dto);

    List<Act> findAllByBuyer(Long Id);

    List<Act> findAllBySellerId(Long id);

    List<Float> findSum(Long id);
}
