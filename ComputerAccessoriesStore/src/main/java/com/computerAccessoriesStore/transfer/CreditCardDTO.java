package com.computerAccessoriesStore.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@EnableJpaRepositories("com.computerAccessoriesStore.*")
public class CreditCardDTO {
    private Long id;

    private String firsName;

    private String lastName;

    private String cvv;

    private String month_year;

    private Float card_code;

    private Float balance;

    private Long idBuyer;
}
