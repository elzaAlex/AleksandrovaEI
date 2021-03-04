package com.computerAccessoriesStore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "credit_card", schema = "public", catalog = "ComputerAccessoriesStore")
public class CreditCard {
    @Id
    @Column(name = "idcard")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firsName;

    @Column(name = "last_name")
    private String lastName;

    private String cvv;

    private String month_year;

    private Float card_code;

    private Float balance;

    @ManyToOne
    @JoinColumn(name = "idbuyer")
    private User buyer;

}
