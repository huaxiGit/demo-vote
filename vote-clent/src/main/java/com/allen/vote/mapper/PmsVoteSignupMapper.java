package com.allen.vote.mapper;


import com.allen.vote.common.entity.PmsVoteSignup;
import com.allen.vote.common.entity.dao.VoteSignupBo;
import com.allen.vote.common.entity.vo.PmsVoteVo;
import com.allen.vote.common.entity.vo.UserVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PmsVoteSignupMapper extends BaseMapper<PmsVoteSignup> {


    IPage<PmsVoteVo> selectVotePage(IPage<PmsVoteVo> page, @Param("voteId") Long voteId);

    /**
     * 参与选举
     * @param user
     * @return
     */
    Boolean enterVote(long voteNumber,@Param("user") UserVo user);

    List<VoteSignupBo> filtrateVoteSums(@Param("voteSum") Integer voteSum, @Param("voteId") Long voteId);
}
