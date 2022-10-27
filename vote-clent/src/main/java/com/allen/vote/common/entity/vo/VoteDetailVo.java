package com.allen.vote.common.entity.vo;


import com.allen.vote.common.entity.dao.VoteSignupBo;
import lombok.Data;

import java.util.List;

@Data
public class VoteDetailVo {

    private List<VoteSignupBo> voteSignups;

    private Long voteNum;

    private String voteStatus;

}
