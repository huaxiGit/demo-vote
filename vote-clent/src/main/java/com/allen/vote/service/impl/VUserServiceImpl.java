package com.allen.vote.service.impl;

import com.allen.vote.common.exception.BusinessException;
import com.allen.vote.common.exception.ExceptionEnum;
import com.allen.vote.common.entity.VUser;
import com.allen.vote.common.entity.vo.UserInfoVo;
import com.allen.vote.mapper.VUserServiceMapper;
import com.allen.vote.service.IPmsVoteSignupService;
import com.allen.vote.service.IVUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@Service
public class VUserServiceImpl extends ServiceImpl<VUserServiceMapper, VUser> implements IVUserService {


    @Autowired
    private IPmsVoteSignupService pmsVoteSignupService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserInfoAndJoin(UserInfoVo userInfoVo) {
        Long voteId = userInfoVo.getVoteId();
        VUser user = new VUser();
        List<VUser> list = this.lambdaQuery().eq(VUser::getSignupIdentity, userInfoVo.getSignupIdentity())
                .select(VUser::getSignupIdentity)
                .last("LIMIT 1").list();
        if (!CollectionUtils.isEmpty(list)){
            throw new BusinessException(ExceptionEnum.PARAM_ERROR.getCode(),"身份证已经存在");
        }
        BeanUtils.copyProperties(userInfoVo,user);
        user.setCreateTime(new Date());
        this.baseMapper.insert(user);

        if (voteId!= null && voteId > 0L){
            userInfoVo.setUserId(user.getId());
            pmsVoteSignupService.saveIntendedCandidate(userInfoVo);
        }
    }
}
