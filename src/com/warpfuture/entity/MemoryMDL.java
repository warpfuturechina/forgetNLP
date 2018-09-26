package com.warpfuture.entity;

/**
 * Demo class
 *
 * @author Benny Shi
 * @date 2018/9/26
 */
public class MemoryMDL {
    /**
     *遗忘累频
     */
    private double validCount = 0;
    /**
     *遗忘次数
     */
    private double totalCount = 0;
    /**
     *系统偏移量
     */
    private double updateOffsetCount = 0;
    /**
     * 有效程度
     */
    private double validDegree = 1;

    public double getValidCount() {
        return validCount;
    }

    public void setValidCount(double validCount) {
        this.validCount = validCount;
    }

    public double getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(double totalCount) {
        this.totalCount = totalCount;
    }

    public double getUpdateOffsetCount() {
        return updateOffsetCount;
    }

    public void setUpdateOffsetCount(double updateOffsetCount) {
        this.updateOffsetCount = updateOffsetCount;
    }

    public double getValidDegree() {
        return validDegree;
    }

    public void setValidDegree(double validDegree) {
        this.validDegree = validDegree;
    }
}
