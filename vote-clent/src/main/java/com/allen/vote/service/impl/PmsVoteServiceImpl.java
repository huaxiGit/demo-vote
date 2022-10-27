package com.allen.vote.service.impl;

import com.allen.vote.common.constant.RedisKeyVoteConstant;
import com.allen.vote.common.enums.BaseBusinessEnum;
import com.allen.vote.common.exception.BusinessException;
import com.allen.vote.common.exception.ExceptionEnum;
import com.allen.vote.common.entity.PmsVote;
import com.allen.vote.common.entity.vo.VoteVo;
import com.allen.vote.common.util.DateUtils;
import com.allen.vote.mapper.PmsVoteMapper;
import com.allen.vote.service.IPmsVoteSignupService;
import com.allen.vote.service.IPmsVoteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Date;
import java.util.Objects;

/**
 * @author admin
 */
@Service
public class PmsVoteServiceImpl extends ServiceImpl<PmsVoteMapper,PmsVote> implements IPmsVoteService {

    @Autowired
    private IPmsVoteSignupService pmsVoteSignupService;

    @Autowired
    private PmsVoteMapper pmsVoteMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean createVote(VoteVo voteVo) {
        PmsVote pmsVote = new PmsVote();
        pmsVote.setVoteStatus(BaseBusinessEnum.voteStatus.BEGIN.getStatus());
        pmsVote.setVoteIntroduce(voteVo.getVoteIntroduce());
        pmsVote.setVoteTitle(voteVo.getVoteTitle());
        pmsVote.setVoteBeginTime(voteVo.getVoteBeginTime());
        pmsVote.setVoteEndTime(voteVo.getVoteEndTime());
        pmsVote.setCreateTime(new Date());
        pmsVote.setVoteStatus(BaseBusinessEnum.voteStatus.FINISH.getStatus());
        int insert = this.baseMapper.insert(pmsVote);
        if (insert > 0){
            String key = RedisKeyVoteConstant.VOTE_SIGNUP + pmsVote.getId().toString();
            redisTemplate.opsForValue().set(key,pmsVote);
            redisTemplate.expire(key, Duration.ofMillis(DateUtils.computationTime(pmsVote.getVoteBeginTime(),pmsVote.getVoteEndTime())));
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void finishVote(VoteVo voteVo) {
        Long voteId = voteVo.getId();
        if (Objects.isNull(voteId)){
            throw new BusinessException(ExceptionEnum.PARAM_ERROR);
        }
        this.verifyVoteFinish(voteId);
        redisTemplate.delete(RedisKeyVoteConstant.VOTE_SIGNUP + voteId);
        this.lambdaUpdate().set(PmsVote::getVoteStatus,voteVo.getVoteStatus()).eq(PmsVote::getId,voteId).update();

        // 发送邮箱
//        pmsVoteMapper.selectVoteDetails();

    }

    @Override
    public void verifyVoteFinish(Long voteId) {
        PmsVote pmsVote = (PmsVote)redisTemplate.opsForValue().get(RedisKeyVoteConstant.VOTE_SIGNUP + voteId.toString());
        if (Objects.nonNull(pmsVote)){
            return;
        }

        PmsVote vote = this.lambdaQuery().eq(PmsVote::getId, voteId).select(PmsVote::getVoteStatus).one();
        if (Objects.isNull(vote) || vote.getVoteStatus().equals(BaseBusinessEnum.voteStatus.FINISH.getStatus())){
            throw new BusinessException(ExceptionEnum.IS_ERROR.getCode(),"当前活动已结束");
        }
    }


}
