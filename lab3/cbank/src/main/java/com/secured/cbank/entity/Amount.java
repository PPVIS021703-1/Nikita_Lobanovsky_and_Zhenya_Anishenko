package com.secured.cbank.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "Amount")
@Data
public class Amount {

    @Id
    private Integer id;
    private Long amount;
    private String number;
}
