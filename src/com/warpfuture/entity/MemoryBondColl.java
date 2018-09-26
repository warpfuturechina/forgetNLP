package com.warpfuture.entity;

import java.util.ArrayList;

/**
 * 关联词集合 class
 *
 * @author Benny Shi
 * @date 2018/9/26
 */
public class MemoryBondColl extends ArrayList<MemoryBondMDL> {
    /**
     * 偏移总量
     */
    private double OffsetTotalCount = 0;

    /**
     * 每分钟偏移量
     */
    private double MinuteOffsetSize = 7
            /*每秒7个字符*/
            * 60 * 60 * 24
            /*一天*/
            * 6;
            //最大记忆容量

    public double getOffsetTotalCount() {
        return OffsetTotalCount;
    }

    public void setOffsetTotalCount(double offsetTotalCount) {
        OffsetTotalCount = offsetTotalCount;
    }

    public double getMinuteOffsetSize() {
        return MinuteOffsetSize;
    }

    public void setMinuteOffsetSize(double minuteOffsetSize) {
        MinuteOffsetSize = minuteOffsetSize;
    }
}
