package com.allen.vote.common.entity.vo;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserInfoVo extends UserVo{


    /**
     * 昵称
     */
    private String nickName;

    /**
     * 参赛邮箱
     */
    @Email(message = "邮箱格式错误")
    private String signupMailbox;

    /**
     * 省份证
     */

    private String signupIdentity;

}
