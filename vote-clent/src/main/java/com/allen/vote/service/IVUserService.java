package com.allen.vote.service;

import com.allen.vote.common.entity.VUser;
import com.allen.vote.common.entity.vo.UserInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IVUserService extends IService<VUser> {



    void saveUserInfoAndJoin(UserInfoVo userInfoVo);

}
