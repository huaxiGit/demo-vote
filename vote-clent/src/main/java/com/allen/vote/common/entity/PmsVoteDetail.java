package com.allen.vote.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("pms_vote_detail")
public class PmsVoteDetail extends BaseDomain implements Serializable {


    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 活动id
     */
    private Long voteId;

    /**
     *  用户选举
     */
    private Long voteSignupId;

    /**
     * 投票用户
     */
    private Long signupUserId;

    /**
     * 选举名称
     */
    private String signupName;





}
