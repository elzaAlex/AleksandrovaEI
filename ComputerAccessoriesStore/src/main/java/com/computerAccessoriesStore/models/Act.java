package com.computerAccessoriesStore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "act",schema = "public", catalog = "ComputerAccessoriesStore")
public class Act {
    @Id
    @Column(name = "idact")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long count;

    private Date created_at;

    @ManyToOne
    @JoinColumn(name="idseller")
    private User seller;

    @ManyToOne
    @JoinColumn(name="idbuyer")
    private User buyer;

    @ManyToOne
    @JoinColumn(name="idproduct")
    private Product product;

}
