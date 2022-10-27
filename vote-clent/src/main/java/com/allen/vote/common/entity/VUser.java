package com.allen.vote.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户信息
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("v_user")
public class VUser extends BaseDomain{


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 参赛邮箱
     */
    private String signupMailbox;

    /**
     * 省份证
     */
    private String signupIdentity;




}
