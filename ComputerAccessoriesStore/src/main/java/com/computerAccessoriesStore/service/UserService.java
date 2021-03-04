package com.computerAccessoriesStore.service;

import com.computerAccessoriesStore.models.User;
import com.computerAccessoriesStore.transfer.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(UserDTO dto);

    List<User> findAll();

    List<User> getSellersGeneralInfoByParam(String param);

    void deleteById(Long id);

    void edit(UserDTO dto);

    Optional<User> getById(Long id);

    User getUsername(String username);

    List<User> findAllBySeller();

    List<User> findAllBySellerInfoByParam(String param);
}
