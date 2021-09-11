package com.turkninja.petshop.entity.question;


import com.turkninja.petshop.entity.answer.AnswerEntity;
import com.turkninja.petshop.entity.base.BaseEntity;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.enums.Gender;
import com.turkninja.petshop.enums.QuestionType;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "QUESTION")
@Data
public class QuestionEntity extends BaseEntity {
    @JoinColumn(name = "explanation")
    private String explanation;

    @Column(name = "question_type")
    @Convert(converter = QuestionType.Converter.class)
    private QuestionType questionType;

    @JoinColumn(name = "is_active")
    private boolean isActive;

    @JoinColumn(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "question")
    private Set<AnswerEntity> answers;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
