package com.warpfuture.dictionary;

import java.util.ArrayList;


public class DictionaryDAL {
    /**
     *更新相邻词库
     */
    public static void UpdateMemoryBondColl(String keyHead, String keyTail, ArrayList<String> objMemoryBondColl){
        if(!objMemoryBondColl.contains(keyHead)){
            DictionaryMDL bond = new DictionaryMDL();
            bond.getKeyItem().setKey(keyHead);
//            bond.setLinkColl(0, objMemoryBondColl);

        }
    }

    /**
     <summary>
     判断键是否为有效关联键
     </summary>
     <typeparam name="T">泛型，具体类型由调用者传入</typeparam>
     <param name="keyHead">相邻键中首项</param>
     <param name="keyTail">相邻键中尾项</param>
     <param name="objMemoryBondColl">相邻字典</param>
     <returns>返回是否判断的结果：true、相邻项有关；false、相邻项无关</returns>
     <remarks>判断标准：共享键概率 > 单字概率之积 </remarks>
     */
    public static boolean IsBondValid(String keyHead, String keyTail, MemoryBondColl objMemoryBondColl){
        // 如果相邻项任何一个不存在相邻词典，则返回false
        if (!objMemoryBondColl.Contains(keyHead) || !objMemoryBondColl.Contains(keyTail)) {return false};
        return true;
    }

     /**
      <summary>
     将候选项添加到词典中
     </summary>
     <typeparam name="T">C#中的泛型，具体类型由调用者传入</typeparam>
     <param name="keyItem">候选项</param>
     <param name="objMemoryItemColl">候选项词典</param>
      */
    public static void UpdateMemoryItemColl(String keyItem, ArrayList<String> objMemoryItemColl){
    }

}
