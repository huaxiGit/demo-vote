package com.allen.vote.service;


import com.allen.vote.common.response.BasePageParam;
import com.allen.vote.common.response.BasePageResult;
import com.allen.vote.common.entity.PmsVoteDetail;
import com.allen.vote.common.entity.vo.UserVo;
import com.allen.vote.common.entity.vo.VoteDetailVo;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.NonNull;

public interface IPmsVoteDetailService extends IService<PmsVoteDetail> {

    /**
     * 添加详情
     * @param signupUserId
     * @param user
     * @return
     */
    Boolean saveUserVoteInfo(@NonNull Long signupUserId, UserVo user);

    /**
     * 校验一个候选人只有投一票
     * @param signupUserId
     * @param voteId
     */
    void verifyByEnterVoteOnly(Long signupUserId, Long voteId);


    BasePageResult<VoteDetailVo> voteDetail(Long voteId, BasePageParam basePageParam);
}
