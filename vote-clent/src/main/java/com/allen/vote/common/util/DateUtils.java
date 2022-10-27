package com.allen.vote.common.util;

import lombok.extern.slf4j.Slf4j;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Allen
 * @version 1.0
 * @description: 时间工具
 * @CreateTime -10-27
 * @since 2022/10/27 12:26
 */
@Slf4j
public class DateUtils {

    String yyy_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private static final long nd = 1000 * 24 * 60 * 60;

    private static final long nh = 1000 * 60 * 60;

    private static final long nm = 1000 * 60;

    public static Long computationTime(Date startTime, Date endTime) {
        log.info("开始时间->{}, 结束时间->{}", startTime, endTime);
        try {
            long diff = endTime.getTime() - startTime.getTime();
            return diff;
        } catch (Exception e) {
            log.info("计算两个时间段时间差出错了, {}", e);
            return null;
        }
    }

}
