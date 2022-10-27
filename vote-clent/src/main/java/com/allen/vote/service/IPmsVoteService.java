package com.allen.vote.service;

import com.allen.vote.common.entity.PmsVote;
import com.allen.vote.common.entity.vo.VoteVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IPmsVoteService extends IService<PmsVote> {

    /**
     * 创建选举活动
     * @param voteVo
     * @return
     */
    Boolean createVote(VoteVo voteVo);

    /**
     * 结束投票
     * @param voteVo
     */
    void finishVote(VoteVo voteVo);

    /**
     * 校验投票是否结束了
     * @param voteId
     */
    void verifyVoteFinish(Long voteId);
}
