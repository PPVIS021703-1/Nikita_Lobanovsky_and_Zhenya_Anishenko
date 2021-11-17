package com.secured.cbank.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "Role")
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "role_id",
            nullable = false,
            unique = true
    )
    private Long roleId;

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String roleName;
}