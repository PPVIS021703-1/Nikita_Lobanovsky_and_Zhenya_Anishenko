package com.secured.cbank.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Operations")
@Table(name = "operations")
@Data
public class Operations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "operation_id")
    private Long operationId;

    String operation;

    @Column(name = "card_id")
    private Long cardId;
}
