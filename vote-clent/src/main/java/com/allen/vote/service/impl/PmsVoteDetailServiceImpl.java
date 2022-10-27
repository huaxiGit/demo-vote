package com.allen.vote.service.impl;

import com.allen.vote.common.enums.BaseBusinessEnum;
import com.allen.vote.common.exception.BusinessException;
import com.allen.vote.common.exception.ExceptionEnum;
import com.allen.vote.common.response.BasePageParam;
import com.allen.vote.common.response.BasePageResult;
import com.allen.vote.common.entity.PmsVote;
import com.allen.vote.common.entity.PmsVoteDetail;
import com.allen.vote.common.entity.dao.VoteSignupBo;
import com.allen.vote.common.entity.vo.UserVo;
import com.allen.vote.common.entity.vo.VoteDetailVo;
import com.allen.vote.mapper.PmsVoteDetailMapper;
import com.allen.vote.mapper.PmsVoteMapper;
import com.allen.vote.service.IPmsVoteDetailService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class PmsVoteDetailServiceImpl extends ServiceImpl<PmsVoteDetailMapper, PmsVoteDetail> implements IPmsVoteDetailService {

    @Autowired
    private PmsVoteMapper pmsVoteMapper;

    @Override
    public Boolean saveUserVoteInfo(Long signupUserId, UserVo user) {

        PmsVoteDetail pmsVoteDetail = new PmsVoteDetail();
        pmsVoteDetail.setVoteId(user.getVoteId());
        pmsVoteDetail.setVoteSignupId(user.getUserId());
        pmsVoteDetail.setSignupName(user.getSignupName());
        pmsVoteDetail.setSignupUserId(signupUserId);
        this.baseMapper.insert(pmsVoteDetail);
        return true;
    }

    @Override
    public void verifyByEnterVoteOnly(Long signupUserId, Long voteId) {
        List<PmsVoteDetail> list = this.lambdaQuery()
                .eq(PmsVoteDetail::getVoteId, voteId)
                .eq(PmsVoteDetail::getSignupUserId, signupUserId)
                .select(PmsVoteDetail::getSignupUserId)
                .last("LIMIT 1").list();

        if (!CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ExceptionEnum.IS_ERROR.getCode(), "暂无票,每次选举只限一票");
        }
    }

    @Override
    public BasePageResult<VoteDetailVo> voteDetail(Long voteId, BasePageParam basePageParam) {
        if (voteId == null && voteId > 0L) {
            throw new BusinessException(ExceptionEnum.IS_NOT_NULL);
        }

        IPage<PmsVoteDetail> page=new Page<>(basePageParam.getPageNum(),basePageParam.getPageSize());
        IPage<VoteSignupBo> boPage = this.baseMapper.selectVotePage(page, voteId, null);
        List<VoteSignupBo> records = boPage.getRecords();
        if (!CollectionUtils.isEmpty(records)){
            PmsVote pmsVote = pmsVoteMapper.selectById(voteId);
            VoteDetailVo voteDetailVo = new VoteDetailVo();
            voteDetailVo.setVoteSignups(boPage.getRecords());
            voteDetailVo.setVoteNum(boPage.getRecords().get(0).getVoteNum());
            voteDetailVo.setVoteStatus(BaseBusinessEnum.voteStatus.toType(pmsVote.getVoteStatus()).getValue());
            return new BasePageResult<>(Collections.singletonList(voteDetailVo),page.getTotal());
        }
        return new BasePageResult(Collections.EMPTY_LIST,boPage.getTotal());
    }


}
