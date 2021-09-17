package com.turkninja.petshop.entity.user;

import com.turkninja.petshop.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "USER_ADDRESS")
@Data
public class UserAddressEntity extends BaseEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PROVINCE", nullable = false)
    private String province;

    @Column(name = "DISTRICT", nullable = false)
    private String district;

    @Column(name = "NEIGHBORHOOD")
    private String neighborhood;

    @Column(name = "STREET")
    private String street;

    @Column(name = "BUILDING")
    private String building;

    @Column(name = "FLOOR")
    private int floor;

    @Column(name = "APARTMENT")
    private String apartment;

    @Column(name = "EXPLANATION")
    private String explanation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    private UserEntity user;
}