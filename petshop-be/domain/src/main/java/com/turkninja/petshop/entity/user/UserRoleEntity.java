package com.turkninja.petshop.entity.user;

import com.turkninja.petshop.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "user_role")
public class UserRoleEntity extends BaseEntity implements Serializable {

    @Column(unique = true)
    private String name;

    @OneToMany
    @JoinTable
            (
                    name = "user_role_authority",
                    joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")}
            )
    private Set<UserAuthorityEntity> authorities;


    @OneToMany(mappedBy = "role")
    Set<UserEntity> users;

    @Override
    public String toString() {
        return "Role [id=" + getId() + ", name= " + getName() + "]";
    }
}
