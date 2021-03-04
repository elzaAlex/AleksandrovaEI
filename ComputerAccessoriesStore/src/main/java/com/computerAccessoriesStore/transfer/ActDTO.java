package com.computerAccessoriesStore.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Date;

@Data
@AllArgsConstructor
@Builder
@EnableJpaRepositories("com.computerAccessoriesStore.*")
public class ActDTO {
    private Long id;

    private Long count;

    private Date created_at;

    private Long idSeller;

    private Long idBuyer;

    private Long idProduct;
}
