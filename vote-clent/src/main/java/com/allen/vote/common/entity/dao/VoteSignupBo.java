package com.allen.vote.common.entity.dao;


import lombok.Data;

@Data
public class VoteSignupBo {

    /**
     * 报名编号
     */
    private String signupIndex;

    /**
     * 选举名称
     */
    private String signupName;

    /**
     * 投票数
     */
    private Long voteNum;

    /**
     * 昵称
     */
    private String nickName;

    /**
     *  状态
     */
    private Integer voteStatus;


}
