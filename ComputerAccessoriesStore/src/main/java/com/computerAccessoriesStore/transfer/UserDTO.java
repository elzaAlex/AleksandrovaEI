package com.computerAccessoriesStore.transfer;

import com.computerAccessoriesStore.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@Builder
@EnableJpaRepositories("com.computerAccessoriesStore.*")
public class UserDTO {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Date created_at;

    private Role role;
}
