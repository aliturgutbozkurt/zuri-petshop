package com.turkninja.petshop.entity.parameter;

import com.turkninja.petshop.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PARAMETERS")
@Data
public class ParameterEntity extends BaseEntity {
    @Column(name = "parameter_key"  )
    private String parameterKey;
    @Column(name = "parameter_value")
    private String parameterValue;
    @Column(name = "parameter_type")
    private String parameterType;
}
