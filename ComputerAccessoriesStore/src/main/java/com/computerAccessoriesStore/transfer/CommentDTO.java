package com.computerAccessoriesStore.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@Builder
@EnableJpaRepositories("com.computerAccessoriesStore.*")
public class CommentDTO {
    private Long id;

    private String message;

    private int rating;

    private Timestamp created_at;

    private Long idSeller;

    private Long idBuyer;
}
