package com.allen.vote.service.impl;

import com.allen.vote.common.constant.RedisKeyVoteConstant;
import com.allen.vote.common.constant.VoteConstant;
import com.allen.vote.common.entity.PmsVote;
import com.allen.vote.common.exception.BusinessException;
import com.allen.vote.common.exception.ExceptionEnum;
import com.allen.vote.common.response.BasePageParam;
import com.allen.vote.common.response.BasePageResult;
import com.allen.vote.common.util.GenerateNum;

import com.allen.vote.common.entity.PmsVoteSignup;
import com.allen.vote.common.entity.dao.VoteSignupBo;
import com.allen.vote.common.entity.vo.PmsVoteVo;
import com.allen.vote.common.entity.vo.UserVo;
import com.allen.vote.common.entity.vo.VoteVo;
import com.allen.vote.mapper.PmsVoteSignupMapper;
import com.allen.vote.service.IPmsVoteDetailService;
import com.allen.vote.service.IPmsVoteSignupService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PmsVoteSignupServiceImpl extends ServiceImpl<PmsVoteSignupMapper, PmsVoteSignup> implements IPmsVoteSignupService {

    @Autowired
    private IPmsVoteDetailService pmsVoteDetailService;
    @Autowired
    private PmsVoteSignupMapper pmsVoteSignupMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveIntendedCandidate(UserVo user) {

        this.verifyByCandidateOnly(user);
        PmsVoteSignup pmsVoteSignup = new PmsVoteSignup();
        pmsVoteSignup.setSignupIndex(GenerateNum.getInstance().GenerateOrder());
        pmsVoteSignup.setVoteId(user.getVoteId());
        pmsVoteSignup.setSignupName(user.getSignupName());
        pmsVoteSignup.setUserId(user.getUserId());
        pmsVoteSignup.setCreateTime(new Date());
        int insert = this.baseMapper.insert(pmsVoteSignup);
        if (insert > 0){
            return true;
        }
        return false;
    }

    @Override
    public BasePageResult<PmsVoteVo> listVotePage(Long voteId, BasePageParam basePageParam) {
        IPage<PmsVoteVo> page=new Page<>(basePageParam.getPageNum(),basePageParam.getPageSize());
        IPage<PmsVoteVo> pmsVoteVoIPage = pmsVoteSignupMapper.selectVotePage(page, voteId);
        return BasePageResult.newInstance(pmsVoteVoIPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean enterVote(UserVo user) {
        PmsVote pmsVote = (PmsVote)redisTemplate.opsForValue().get(RedisKeyVoteConstant.VOTE_SIGNUP + user.getVoteId().toString());
        if (Objects.isNull(pmsVote)){
            throw new BusinessException(ExceptionEnum.IS_ERROR.getCode(),"当前活动已结束");
        }
        // 获取当前用户信息
        Long signupUserId = user.getSignupUserId();

        pmsVoteDetailService.verifyByEnterVoteOnly(signupUserId,user.getVoteId());

        pmsVoteSignupMapper.enterVote(VoteConstant.ENTER_VOTE_NUMBER,user);

        //添加详情
        pmsVoteDetailService.saveUserVoteInfo(signupUserId,user);

        return true;
    }

    @Override
    public List<VoteSignupBo> voteSums(VoteVo voteVo) {
       return pmsVoteSignupMapper.filtrateVoteSums(VoteConstant.VOTE_SUM,voteVo.getId());
    }


    /**
     * 校验当前活动选举是否存该用户
     * @param user
     */
    private void verifyByCandidateOnly(UserVo user) {

        List<PmsVoteSignup> list = this.lambdaQuery()
                .eq(PmsVoteSignup::getVoteId, user.getVoteId())
                .eq(PmsVoteSignup::getUserId, user.getUserId())
                .select(PmsVoteSignup::getUserId)
                .last("LIMIT 1").list();

        if (!CollectionUtils.isEmpty(list)){
            throw new BusinessException(ExceptionEnum.IS_ERROR.getCode(),"活动已经存在用户数据");
        }

    }


}
