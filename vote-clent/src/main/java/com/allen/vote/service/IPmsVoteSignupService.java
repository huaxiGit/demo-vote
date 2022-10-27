package com.allen.vote.service;


import com.allen.vote.common.response.BasePageParam;
import com.allen.vote.common.response.BasePageResult;

import com.allen.vote.common.entity.PmsVoteSignup;
import com.allen.vote.common.entity.dao.VoteSignupBo;
import com.allen.vote.common.entity.vo.PmsVoteVo;
import com.allen.vote.common.entity.vo.UserVo;
import com.allen.vote.common.entity.vo.VoteVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface IPmsVoteSignupService extends IService<PmsVoteSignup> {

    Boolean saveIntendedCandidate(UserVo user);

    BasePageResult<PmsVoteVo> listVotePage(Long voteId, BasePageParam basePageParam);

    /**
     * 参与投票
     * @param user
     * @return
     */
    Boolean enterVote(UserVo user);


    /**
     * @description: 查询得票最高两人
     * @author: Allen
     * @date: 2022/10/26 12:30
     * @param: voteId
     * @return:
     **/
    List<VoteSignupBo> voteSums(VoteVo voteVo);
}
