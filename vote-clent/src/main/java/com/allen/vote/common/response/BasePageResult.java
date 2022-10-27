package com.allen.vote.common.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author ：LionCity
 * @date ：Created in 2022-10-25 16:55
 * @description：分页参数返回
 * @modified By：huaxi
 * @version: V1.0
 */
@Data
public class BasePageResult<T> implements Serializable {

    private List<T> data;
    private Long totalNum;

    public BasePageResult(List<T> data, Long totalNum) {
        this.data = data;
        this.totalNum = totalNum;
    }

    public static <E> BasePageResult<E> newInstance(IPage<E> page) {
        return new BasePageResult<E>(page.getRecords(), page.getTotal());
    }



    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Long totalNum) {
        this.totalNum = totalNum;
    }

    @Override
    public String toString() {
        return "BasePageResult{" +
                "data=" + data +
                ", totalNum=" + totalNum +
                '}';
    }
}
