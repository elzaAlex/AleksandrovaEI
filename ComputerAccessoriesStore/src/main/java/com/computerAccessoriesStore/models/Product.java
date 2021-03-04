package com.computerAccessoriesStore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product",schema = "public", catalog = "ComputerAccessoriesStore")
public class Product {
    @Id
    @Column(name = "idproduct")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float product_cost;

    private String product_name;

    @ManyToOne
    @JoinColumn(name="idseller")
    private User seller;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "idact")
    private List<Act> acts = new ArrayList<>();
}
