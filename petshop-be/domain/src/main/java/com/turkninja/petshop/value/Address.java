package com.turkninja.petshop.value;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

@Data
@ToString
public class Address {
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @Column(name = "town")
    private String town;
    @Column(name = "street")
    private String street;
    @Column(name = "external_door_no")
    private String externalDoorNo;
    @Column(name = "internal_door_no")
    private String internalDoorNo;
}
