package com.allen.vote.mapper;

import com.allen.vote.common.entity.VUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VUserServiceMapper extends BaseMapper<VUser> {
}
