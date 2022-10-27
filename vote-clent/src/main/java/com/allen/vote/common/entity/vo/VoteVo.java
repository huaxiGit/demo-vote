package com.allen.vote.common.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import reactor.util.annotation.Nullable;

import java.util.Date;

@Data
public class VoteVo {

    private Long id;

    /**
     * 投票主题名称
     */
    private String voteTitle;

    /**
     * 投票介绍
     */
    private String voteIntroduce;

    /**
     * 报名开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signupBeginTime;

    /**
     * 报名结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date signupEndTime;

    /**
     * 投票开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date voteBeginTime;

    /**
     * 投票结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Nullable
    private Date voteEndTime;

    /**
     * 状态（1 ：选举结束 0 ：正在选举中）
     */
    private Integer voteStatus;
}
