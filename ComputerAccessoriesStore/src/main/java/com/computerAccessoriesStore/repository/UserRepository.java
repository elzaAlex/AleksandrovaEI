package com.computerAccessoriesStore.repository;

import com.computerAccessoriesStore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User getByUsername(String username);

    @Query("SELECT a FROM User a WHERE a.role = 'ROLE_SELLER'")
    List<User> findAllBySeller();
}
