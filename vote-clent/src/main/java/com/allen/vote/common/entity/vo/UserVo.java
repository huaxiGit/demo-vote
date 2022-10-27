package com.allen.vote.common.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 候选人id
     */
    private Long userId;

    /**
     * 活动id
     */
    private Long voteId;

    /**
     * 选举名称
     */
    private String signupName;


    /**
     * 投票用户
     */
    private Long signupUserId;



}
