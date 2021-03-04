package com.computerAccessoriesStore.service.impl;

import com.computerAccessoriesStore.models.Act;
import com.computerAccessoriesStore.models.Product;
import com.computerAccessoriesStore.models.User;
import com.computerAccessoriesStore.repository.ActRepository;
import com.computerAccessoriesStore.service.ActService;
import com.computerAccessoriesStore.transfer.ActDTO;
import com.computerAccessoriesStore.transfer.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ActServiceImpl implements ActService {

    @Autowired
    private ActRepository actRepository;

    @Override
    public List<Act> findAll() {
        return actRepository.findAll();
    }

    @Override
    public void add(ActDTO dto) {
        Act act = Act.builder()
                .count(dto.getCount())
                .created_at(new java.sql.Date(Calendar.getInstance().getTime().getTime()))
                .buyer(User.builder().id((dto.getIdBuyer())).build())
                .product(Product.builder().id(dto.getIdProduct()).build())
                .seller(User.builder().id(dto.getIdSeller()).build())
                .build();
        actRepository.save(act);
    }

    @Override
    public void deleteById(Long id) {
        actRepository.deleteById(id);
    }

    @Override
    public Optional<Act> getById(Long id) {
        return actRepository.findById(id);
    }

    @Override
    public void edit(ActDTO dto) {
//        Act act = Act.builder()
//                .id(dto.getId())
//                .count(dto.getCount())
//                .created_at(dto.getCreated_at())
//                .buyer(User.builder().id(dto.getIdBuyer()).build())
//                .seller(User.builder().id(dto.getIdSeller()).build())
//                .product(Product.builder().id(dto.getIdProduct()).build()).build();
        actRepository.save(getTransformedObject(dto));
    }

    public Act getTransformedObject(ActDTO dto) {
        return Act.builder()
                .id(dto.getId())
                .count(dto.getCount())
                .created_at(dto.getCreated_at())
                .buyer(User.builder().id(dto.getIdBuyer()).build())
                .seller(User.builder().id(dto.getIdSeller()).build())
                .product(Product.builder().id(dto.getIdProduct()).build()).build();
    }

    @Override
    public List<Act> findAllByBuyer(Long id) {
        return actRepository.findAllByBuyer(id);
    }

    @Override
    public List<Act> findAllBySellerId(Long id) {
        return actRepository.findAllBySellerId(id);
    }

    @Override
    public List<Float> findSum(Long id) {
        return actRepository.findSum(id);
    }
}
