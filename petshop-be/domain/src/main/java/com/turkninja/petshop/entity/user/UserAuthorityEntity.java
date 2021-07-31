package com.turkninja.petshop.entity.user;

import com.turkninja.petshop.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user_authority")
public class UserAuthorityEntity extends BaseEntity {

    @Column(unique = true)
    private String name;
}
