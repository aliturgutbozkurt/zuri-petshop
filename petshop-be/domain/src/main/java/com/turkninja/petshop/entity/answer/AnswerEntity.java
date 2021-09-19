package com.turkninja.petshop.entity.answer;


import com.turkninja.petshop.entity.base.BaseEntity;
import com.turkninja.petshop.entity.question.QuestionEntity;
import com.turkninja.petshop.entity.user.UserEntity;
import com.turkninja.petshop.entity.user.UserRoleEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ANSWER")
@Data
public class AnswerEntity extends BaseEntity {

    @JoinColumn(name = "explanation")
    private String explanation;

    @JoinColumn(name = "is_deleted")
    private boolean isDeleted;

    @JoinColumn(name = "parent_answer_id")
    private Long parentAnswerId;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;



}
