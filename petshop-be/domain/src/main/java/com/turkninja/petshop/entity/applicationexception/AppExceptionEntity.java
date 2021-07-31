package com.turkninja.petshop.entity.applicationexception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "app_exception")
@EqualsAndHashCode(of = {"id"})
public class AppExceptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private String message;
}
