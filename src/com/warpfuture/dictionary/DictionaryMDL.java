package com.warpfuture.dictionary;

/**
 * Demo class
 *
 * @author Benny Shi
 * @date 2018/9/19
 */
public class DictionaryMDL {
    /**
     * 主项
     */
    private MemoryItemMDL keyItem = new MemoryItemMDL();

    public MemoryItemMDL getKeyItem() {
        return keyItem;
    }

    public void setKeyItem(MemoryItemMDL keyItem) {
        this.keyItem = keyItem;
    }

    /**
     * 关联项集合
     */
    private MemoryItemColl linkColl = new MemoryItemColl();

    public MemoryItemColl getLinkColl() {
        return linkColl;
    }

    public void setLinkColl(double offSetTotalCount, double minuteOffsetSize) {
        linkColl.setOffSetTotalCount(offSetTotalCount);
        linkColl.setMinuteOffsetSize(minuteOffsetSize);
    }
}

/**
 * 记忆单元主体
 * @param <T>
 */
class MemoryItemMDL<T>{
    private T key;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}

class MemoryItemColl<T>{
    /**
     * 偏移总量
     */
    private double offSetTotalCount = 0;

    /**
     * 每分钟偏移量
     */
    private double minuteOffsetSize = 7 * 60 * 60 * 24 * 6;
    //最大记忆容量


    public double getOffSetTotalCount() {
        return offSetTotalCount;
    }

    public void setOffSetTotalCount(double offSetTotalCount) {
        this.offSetTotalCount = offSetTotalCount;
    }

    public double getMinuteOffsetSize() {
        return minuteOffsetSize;
    }

    public void setMinuteOffsetSize(double minuteOffsetSize) {
        this.minuteOffsetSize = minuteOffsetSize;
    }
}



