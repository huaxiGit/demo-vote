package com.allen.vote.common.listener;

import com.allen.vote.common.entity.vo.VoteVo;
import com.allen.vote.common.enums.BaseBusinessEnum;
import com.allen.vote.service.IPmsVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author Allen
 * @version 1.0
 * @description: redis key过期监听事件
 * @CreateTime -10-27
 * @since 2022/10/27 10:43
 */
@Component
public class RedisVoteListener extends KeyExpirationEventMessageListener {

    @Autowired
    private IPmsVoteService pmsVoteService;
    /**
     * 监听的主题(只监听redis数据库1)
     */
    public final PatternTopic topic = new PatternTopic("__keyevent@1__:expired");

    public RedisVoteListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }




    /**
     * Redis失效事件 key
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {

        String expiraKey = new String(message.getBody(), StandardCharsets.UTF_8);
        String getChannel = new String(message.getChannel(), StandardCharsets.UTF_8);
        System.out.println("-----------------------redis失效事件key---------------------------------");
        System.out.println("--------------------"+expiraKey+"------------------------------------");
        System.out.println(getChannel.equals(topic.getTopic()));
        if (!getChannel.isEmpty() && getChannel.equals(topic.getTopic())){
            String voteId = expiraKey.substring(expiraKey.lastIndexOf("_")+1);
            VoteVo voteVo = new VoteVo();
            voteVo.setVoteStatus(BaseBusinessEnum.voteStatus.BEGIN.getStatus());
            voteVo.setId(Long.valueOf(voteId));
            pmsVoteService.finishVote(voteVo);
        }
    }
}
