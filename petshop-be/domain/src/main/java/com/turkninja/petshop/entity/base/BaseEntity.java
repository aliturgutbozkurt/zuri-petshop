package com.turkninja.petshop.entity.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by aliturgut.bozkurt
 * Date: 5/18/18.
 */

/**
 * An abstract domain class that is audited upon being persisted.
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    private static final long serialVersionUID = 3171147165710059229L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(nullable = false, name = "created_at", updatable = false)
    protected LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, name = "created_by", updatable = false)
    protected String createdBy;

    @LastModifiedDate
    @Column(nullable = false, name = "revised_at")
    protected LocalDateTime revisedAt;

    @Column(name = "active")
    protected boolean active = true;
}
