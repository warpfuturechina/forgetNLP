package com.warpfuture.entity;

/**
 * 关联词 class
 *
 * @author Benny Shi
 * @date 2018/9/26
 */
public class MemoryBondMDL {

    /**
     * 主项
     */
    private MemoryItemMDL KeyItem = new MemoryItemMDL();

    /**
     * 关联项集合
     */
    private MemoryItemColl LinkColl = new MemoryItemColl();

    public MemoryItemMDL getKeyItem() {
        return KeyItem;
    }

    public void setKeyItem(MemoryItemMDL keyItem) {
        KeyItem = keyItem;
    }

    public MemoryItemColl getLinkColl() {
        return LinkColl;
    }

    public void setLinkColl(MemoryItemColl linkColl) {
        LinkColl = linkColl;
    }
}
