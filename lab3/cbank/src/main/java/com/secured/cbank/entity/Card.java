package com.secured.cbank.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Card")
@Table(name = "card")
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "card_id",
            nullable = false,
            unique = true
    )
    private Long cardId;

    @Column(
            name = "number",
            nullable = false,
            unique = true
    )
    private String cardNumber;

    @Column(
            name = "pin",
            nullable = false,
            unique = true
    )
    private String pin;

    @Column(name = "card_check", nullable = true)
    private Long cardCheck;

    @Column(
            name = "validation_date",
            nullable = false
    )
    private String cardValidationDate;

    @Column(
            name = "is_valid",
            nullable = false
    )
    private boolean isValid;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH
            }
    )
    @JoinTable(
            name = "bank_roles",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}