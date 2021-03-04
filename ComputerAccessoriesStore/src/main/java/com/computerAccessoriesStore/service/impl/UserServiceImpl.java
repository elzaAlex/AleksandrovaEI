package com.computerAccessoriesStore.service.impl;

import com.computerAccessoriesStore.models.User;
import com.computerAccessoriesStore.repository.UserRepository;
import com.computerAccessoriesStore.service.UserService;
import com.computerAccessoriesStore.transfer.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void add(UserDTO dto){
//        User user = User.builder()
//                .created_at(dto.getCreated_at())
//                .email(dto.getEmail())
//                .firstName(dto.getFirstName())
//                .lastName(dto.getLastName())
//                .password(dto.getPassword())
//                .role(dto.getRole())
//                .username(dto.getUsername())
//                .build();
        userRepository.save(getTransformedObject(dto));
    }

    public User getTransformedObject(UserDTO dto) {
        return User.builder()
                .created_at(dto.getCreated_at())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .role(dto.getRole())
                .username(dto.getUsername())
                .build();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getSellersGeneralInfoByParam(String param){
        return StreamSupport.stream(userRepository
                .findAll().spliterator(), false)
                .filter(user -> user.getFirstName().contains(param)
                        || user.getLastName().contains(param)
                        || user.getUsername().contains(param))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void edit(UserDTO dto) {
        User user = User.builder()
                .created_at(dto.getCreated_at())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .role(dto.getRole())
                .username(dto.getUsername())
                .id(dto.getId())
                .build();
        userRepository.save(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public List<User> findAllBySeller() {
        return userRepository.findAllBySeller();
    }

    @Override
    public List<User> findAllBySellerInfoByParam(String param) {
        return StreamSupport.stream(userRepository
                .findAllBySeller().spliterator(), false)
                .filter(user -> user.getFirstName().contains(param)
                        || user.getLastName().contains(param)
                        || user.getUsername().contains(param))
                .collect(Collectors.toList());
    }
}
