package com.computerAccessoriesStore.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comment",schema = "public", catalog = "ComputerAccessoriesStore")
public class Comment {
    @Id
    @Column(name = "idcomment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private int rating;

    private Timestamp created_at;

    @ManyToOne
    @JoinColumn(name="idseller")
    private User seller;

    @ManyToOne
    @JoinColumn(name="idbuyer")
    private User buyer;
}
