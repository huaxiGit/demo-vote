package com.allen.vote.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * 基础
 */
public class BaseBusinessEnum {



    @Getter
    @AllArgsConstructor
    public enum voteStatus {
        BEGIN(1, "选举结束"),
        FINISH(0, "正在选举中"),
        ERROR(2, "其他");
        private Integer status;
        private String value;


        public static voteStatus toType(Integer code) {
            if (code != 0) {
                for (voteStatus statusEnum : voteStatus.values()) {
                    if (statusEnum.getStatus().equals(code)) {
                        return statusEnum;
                    }
                }
            }
            return voteStatus.ERROR;
//            throw new IllegalArgumentException("该状态无效!");
        }
    }

}
