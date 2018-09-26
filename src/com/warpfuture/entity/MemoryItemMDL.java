package com.warpfuture.entity;

/**
 * 记忆单元项 class
 *
 * @author Benny Shi
 * @date 2018/9/26
 */
public class MemoryItemMDL<T>  extends  MemoryMDL {

    /**
     * 记忆单元主体项
     */
    private T Key;

    public T getKey() {
        return Key;
    }

    public void setKey(T key) {
        Key = key;
    }
}
