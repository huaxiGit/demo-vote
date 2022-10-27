package com.allen.vote;

import com.allen.vote.common.BaseTest;
import com.allen.vote.common.entity.vo.UserInfoVo;
import com.allen.vote.common.entity.vo.UserVo;
import com.allen.vote.service.IPmsVoteDetailService;
import com.allen.vote.service.IPmsVoteService;
import com.allen.vote.service.IPmsVoteSignupService;
import com.allen.vote.service.IVUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Allen
 * @version 1.0
 * @description: 普通用户接口测试
 * @CreateTime -10-26
 * @since 2022/10/26 11:09
 */
public class UserVoteTest extends BaseTest {


    @Autowired
    private IPmsVoteService signupService;
    @Autowired
    private IPmsVoteSignupService pmsVoteSignupService;
    @Autowired
    private IVUserService userService;
    @Autowired
    private IPmsVoteDetailService pmsVoteDetailService;

    /**
     * @description: 普通用户添加信息
     * @author: Allen
     * @date: 2022/10/26 11:15
     **/
    @Test
    public void saveUser(){
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setNickName("Allen");
        userInfoVo.setSignupIdentity("4409211994011332");
        userInfoVo.setSignupMailbox("empty_xi@163com");
        userService.saveUserInfoAndJoin(userInfoVo);
    }


   /**
    * @description: 普通用户投票, 每次只限制一票
    * @author: Allen
    * @date: 2022/10/26 14:44
    **/
    @Test
    public void userEnterVote(){
        UserVo userVo = new UserVo();
        userVo.setUserId(3L);
        userVo.setVoteId(4L);
        userVo.setSignupUserId(3L);
        pmsVoteSignupService.enterVote(userVo);
    }





}
