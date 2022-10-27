package com.allen.vote.mapper;


import com.allen.vote.common.entity.PmsVoteDetail;
import com.allen.vote.common.entity.dao.VoteSignupBo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PmsVoteDetailMapper extends BaseMapper<PmsVoteDetail> {

    IPage<VoteSignupBo> selectVotePage(IPage<PmsVoteDetail> page, @Param("voteId") Long voteId, @Param("userId") Long userId);
}
