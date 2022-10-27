package com.allen.vote.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 活动主题表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_vote")
public class PmsVote extends BaseDomain {


    @TableId(value = "id", type = IdType.AUTO)
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
    private Date signupBeginTime;

    /**
     * 报名结束时间
     */
    private Date signupEndTime;

    /**
     * 投票开始时间
     */
    private Date voteBeginTime;

    /**
     * 投票结束时间
     */
    private Date voteEndTime;


    /**
     * 状态（1 ：选举结束 0 ：正在选举中）
     */
    private Integer voteStatus;







}
