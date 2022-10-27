package com.allen.vote.common.entity.vo;

import lombok.Data;

@Data
public class PmsVoteVo {

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

    private String nickName;

}
