package com.turkninja.petshop.entity.question;


import com.turkninja.petshop.entity.answer.AnswerEntity;
import com.turkninja.petshop.entity.base.BaseEntity;
import com.turkninja.petshop.entity.user.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "QUESTION")
@Data
public class QuestionEntity extends BaseEntity {
    @JoinColumn(name = "explanation")
    private String explanation;

    @JoinColumn(name = "is_deleted")
    private boolean isDeleted;

    @JoinColumn(name="last_answer_id")
    private Long lastAnswerId;

    @OneToMany(mappedBy = "question")
    private Set<AnswerEntity> answers;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
