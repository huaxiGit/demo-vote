package com.allen.vote.controller;


import com.allen.vote.common.entity.vo.*;
import com.allen.vote.common.enums.BaseBusinessEnum;
import com.allen.vote.common.response.BasePageParam;
import com.allen.vote.common.response.BasePageResult;
import com.allen.vote.common.response.BusinessResult;
import com.allen.vote.common.entity.dao.VoteSignupBo;
import com.allen.vote.service.IPmsVoteDetailService;
import com.allen.vote.service.IPmsVoteService;
import com.allen.vote.service.IPmsVoteSignupService;
import com.allen.vote.service.IVUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * 普通用户添加选举
 *
 * @author admin
 */

@Api(tags = "选举接口")
@RestController
@RequestMapping("/vote")
public class SignupController {

    @Autowired
    private IPmsVoteService signupService;
    @Autowired
    private IPmsVoteSignupService pmsVoteSignupService;
    @Autowired
    private IVUserService userService;
    @Autowired
    private IPmsVoteDetailService pmsVoteDetailService;


    /**
     * 管理员查看添加人员
     * @param user
     * @return
     */
    @ApiOperation(value = "管理员查看添加人员")
    @PostMapping("/saveCandidate")
    public BusinessResult<Boolean> saveIntendedVote(@RequestBody UserVo user) {
        signupService.verifyVoteFinish(user.getVoteId());
        return BusinessResult.ok(pmsVoteSignupService.saveIntendedCandidate(user));
    }

    /**
     * 选举活动开始
     */
    @PostMapping("/beginVote")
    @ApiOperation(value = "选举活动开始")
    public BusinessResult<?> beginOrFinishVote(@Valid @RequestBody VoteVo voteVo) {
        if (Objects.equals(BaseBusinessEnum.voteStatus.BEGIN.getStatus(), voteVo.getVoteStatus())) {
            signupService.finishVote(voteVo);
            List<VoteSignupBo> voteSignupBos = pmsVoteSignupService.voteSums(voteVo);
            return BusinessResult.ok(voteSignupBos);
        }
        return BusinessResult.ok(signupService.createVote(voteVo));
    }


    /**
     * 查询选举 分页
     */
    @ApiOperation(value = "查询选举")
    @GetMapping("/listVote")
    public BasePageResult<PmsVoteVo> listVote(Long voteId, BasePageParam basePageParam) {
        return pmsVoteSignupService.listVotePage(voteId, basePageParam);
    }



    /**
     * 普通用户填写信息
     *
     * @param userInfoVo
     * @return
     */
    @ApiOperation(value = "普通用户填写信息")
    @PostMapping("/saveUserInfo")
    public BusinessResult<Boolean> saveUser(@Valid @RequestBody UserInfoVo userInfoVo) {
        userService.saveUserInfoAndJoin(userInfoVo);
        return BusinessResult.ok(true);
    }

    /**
     * 普通用户投票选举
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "普通用户投票选举")
    @GetMapping("/candidateVote")
    public BusinessResult<?> userEnterVote(UserVo user) {
        return BusinessResult.ok(pmsVoteSignupService.enterVote(user));
    }


    /**
     * 查看详情
     * @param voteId
     * @param basePageParam
     * @return
     */
    @ApiOperation(value = "查看详情")
    @GetMapping("/candidateDetail/{voteId}")
    public BasePageResult<VoteDetailVo> candidateDetail(@PathVariable(value = "voteId") Long voteId, BasePageParam basePageParam){
       return pmsVoteDetailService.voteDetail(voteId,basePageParam);
    }



}
