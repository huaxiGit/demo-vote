package com.allen.vote.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 用户参与表
 *
 * @author admin
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_vote_signup")
public class PmsVoteSignup extends BaseDomain implements Serializable {


    private static final long serialVersionUID = 1L;


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 报名编号
     */
    private String signupIndex;

    /**
     * 活动id
     */
    private Long voteId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 选举名称
     */
    private String signupName;

    /**
     * 报名状态
     */
    private Integer signupStatus;

    /**
     * 投票数
     */
    private Long voteNum;


}
