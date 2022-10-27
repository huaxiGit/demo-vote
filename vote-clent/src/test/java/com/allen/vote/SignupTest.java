package com.allen.vote;

import com.allen.vote.common.BaseTest;
import com.allen.vote.common.response.BasePageParam;
import com.allen.vote.common.response.BasePageResult;
import com.allen.vote.common.entity.vo.PmsVoteVo;
import com.allen.vote.common.entity.vo.UserVo;
import com.allen.vote.common.entity.vo.VoteVo;
import com.allen.vote.service.IPmsVoteDetailService;
import com.allen.vote.service.IPmsVoteService;
import com.allen.vote.service.IPmsVoteSignupService;
import com.allen.vote.service.IVUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


import java.time.Duration;
import java.util.Date;

/**
 * @author Allen
 * @version 1.0
 * @description: 接口测试
 * @CreateTime -10-26
 * @since 2022/10/26 10:49
 */

public class SignupTest extends BaseTest {

    @Autowired
    private IPmsVoteService signupService;
    @Autowired
    private IPmsVoteSignupService pmsVoteSignupService;
    @Autowired
    private IVUserService userService;
    @Autowired
    private IPmsVoteDetailService pmsVoteDetailService;

    /**
     * @description: 创建选举活动
     * @author: Allen
     * @date: 2022/10/26 10:55
     **/
    @Test
    public void beginOrFinishVote(){
        VoteVo voteVo = new VoteVo();
        voteVo.setVoteStatus(0);
        voteVo.setVoteTitle("测试活动");
        voteVo.setVoteIntroduce("学生活动选举");
        voteVo.setVoteBeginTime(new Date());
        Boolean vote = signupService.createVote(voteVo);
        System.out.println(vote);
    }

    /**
     * @description: 查看选举候选人
     * @author: Allen
     * @date: 2022/10/26 11:07
     **/
    @Test
    public void listVote(){
        BasePageParam basePageParam = new BasePageParam();
        basePageParam.setPageNum(1);
        basePageParam.setPageSize(10);
        BasePageResult<PmsVoteVo> pmsVoteVoBasePageResult = pmsVoteSignupService.listVotePage(3L, basePageParam);
        System.out.println(pmsVoteVoBasePageResult);
    }


    /**
     * @description: 添加候选人
     * @author: Allen
     * @date: 2022/10/26 11:46
     **/
    @Test
    public void saveIntendedVote(){
        UserVo userVo = new UserVo();
        userVo.setUserId(6L);
        userVo.setVoteId(3L);
        userVo.setSignupName("测试活动");
        pmsVoteSignupService.saveIntendedCandidate(userVo);
    }



}
