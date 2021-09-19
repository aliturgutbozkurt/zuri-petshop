package com.turkninja.petshop.entity.user;

import com.turkninja.petshop.entity.answer.AnswerEntity;
import com.turkninja.petshop.entity.base.BaseEntity;
import com.turkninja.petshop.entity.question.QuestionEntity;
import com.turkninja.petshop.enums.Gender;
import com.turkninja.petshop.value.Address;
import com.turkninja.petshop.value.FullName;
import com.turkninja.petshop.value.Image;
import com.turkninja.petshop.value.Phone;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by aliturgut.bozkurt
 * Date: 12/22/18.
 */
@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = {"id"})
public class UserEntity extends BaseEntity {

    @Embedded
    private FullName fullName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Embedded
    private Phone phone;

    @Embedded
    private Address address;

    @Embedded
    private Image image;

    @Column(name = "gender")
    @Convert(converter = Gender.Converter.class)
    private Gender gender;

    @Column(name = "active",columnDefinition="BOOLEAN DEFAULT true", nullable=false)
    private boolean active;

    @Column(name = "last_logged_in")
    private LocalDateTime lastLoggedIn;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private UserRoleEntity role;

    @OneToMany(mappedBy = "user")
    private Set<QuestionEntity> questions;

    @OneToMany(mappedBy = "user")
    private Set<AnswerEntity> answers;


    @Override
    public String toString() {
        return "User [id=" + getId() + ", fullName= " + getFullName() + "]";
    }
}
