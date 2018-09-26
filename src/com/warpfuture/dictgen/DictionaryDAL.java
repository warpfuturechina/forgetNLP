package com.warpfuture.dictgen;

import com.warpfuture.entity.MemoryBondColl;
import com.warpfuture.entity.MemoryBondMDL;
import com.warpfuture.entity.MemoryItemColl;
import com.warpfuture.entity.MemoryItemMDL;
import com.warpfuture.memory.MemoryDAL;


public class DictionaryDAL {
    /**
     *          /// <summary>
     *         /// 将相邻两项（邻键）添加到集合中
     *         /// </summary>
     *         /// <typeparam name="T"></typeparam>
     *         /// <param name="keyHead"></param>
     *         /// <param name="keyTail"></param>
     *         /// <param name="objMemoryBondColl"></param>
     */
    public static void UpdateMemoryBondColl(String keyHead, String keyTail, MemoryBondColl objMemoryBondColl){
        if(!objMemoryBondColl.contains(keyHead)){
            MemoryBondMDL bond = new MemoryBondMDL();
            bond.getKeyItem().setKey(keyHead);
            bond.getLinkColl().setOffsetTotalCount(0);
            bond.getLinkColl().setMinuteOffsetSize(objMemoryBondColl.getMinuteOffsetSize());
            objMemoryBondColl.add(bond);
        }

        MemoryBondMDL objBondMDL = objMemoryBondColl.get(objMemoryBondColl.size()-1);

        MemoryItemMDL mdl = objBondMDL.getKeyItem();

        double dRemeberValue = MemoryDAL.CalcRemeberValue(objMemoryBondColl.getOffsetTotalCount() - mdl.getUpdateOffsetCount(), objMemoryBondColl.getMinuteOffsetSize());

        //累加总次数
        mdl.setTotalCount(mdl.getValidCount() + 1);
        //计算成熟度
        mdl.setValidDegree(mdl.getValidCount() * dRemeberValue + 1);
        //遗忘累频
        mdl.setValidCount(mdl.getValidCount() * dRemeberValue + 1);
        //更新时的偏移量
        mdl.setUpdateOffsetCount(objMemoryBondColl.getOffsetTotalCount());

        MemoryItemColl objLinkColl = objBondMDL.getLinkColl();
        objLinkColl.setOffsetTotalCount(objMemoryBondColl.getOffsetTotalCount());
        UpdateMemoryItemColl(keyTail, objLinkColl);

        objMemoryBondColl.setOffsetTotalCount(objMemoryBondColl.getOffsetTotalCount() + 1);
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
        if (!objMemoryBondColl.contains(keyHead) || !objMemoryBondColl.contains(keyTail)) {return false;}

        //分别获得相邻单项的频次
        double dHeadValidCount = CalcRemeberValue(keyHead, objMemoryBondColl);
        double dTailValidCount = CalcRemeberValue(keyHead, objMemoryBondColl);

        //获得相邻词典全库的总词频
        double dTotalValidCount = objMemoryBondColl.getMinuteOffsetSize();

        if(dTotalValidCount <= 0){ return false; }

        //获取相邻项共现频次
        MemoryItemColl objLinkColl = objMemoryBondColl.get(objMemoryBondColl.indexOf(keyHead)).getLinkColl();
        if(!objLinkColl.contains(keyTail)){return false;}
        double dShareValidCount = CalcRemeberValue(keyTail, objLinkColl);

        //返回计算结果
        return dShareValidCount/ dHeadValidCount > dTailValidCount / dTotalValidCount;


    }

     /**
      <summary>
     将候选项添加到词典中
     </summary>
     <param name="keyItem">候选项</param>
     <param name="objMemoryItemColl">候选项词典</param>
      */
    public static void UpdateMemoryItemColl(Object keyItem, MemoryItemColl objMemoryItemColl){
        if(!objMemoryItemColl.contains(keyItem)){
            //如果词典不存在该候选项

            //声明数据对象，存入选项以及相关数据
            MemoryItemMDL mdl = new MemoryItemMDL();
            mdl.setKey(keyItem);
            mdl.setTotalCount(1);
            mdl.setValidCount(1);
            mdl.setValidDegree(1);
            objMemoryItemColl.add(mdl);

        }else{
            //如果以及有这个候选项

            //从词典中取出该候选项
            MemoryItemMDL mdl = objMemoryItemColl.get(objMemoryItemColl.indexOf(keyItem));
            double dRemeberValue = MemoryDAL.CalcRemeberValue(objMemoryItemColl.getOffsetTotalCount() - mdl.getUpdateOffsetCount(), objMemoryItemColl.getMinuteOffsetSize());
            //累加总计数
            mdl.setTotalCount(mdl.getTotalCount()+1);
            //计算成熟度
            mdl.setValidDegree(CalcValidDegree(mdl, dRemeberValue));
            //遗忘累频 = 记忆保留量 + 1
            mdl.setValidCount(mdl.getValidCount() * dRemeberValue + 1);
            //更新时偏移量
            mdl.setUpdateOffsetCount(objMemoryItemColl.getOffsetTotalCount());

        }

        objMemoryItemColl.setOffsetTotalCount(objMemoryItemColl.getOffsetTotalCount() + 1);

    }


    /**
     *          /// <summary>
     *         /// 计算候选项记忆剩余量
     *         /// </summary>
     *         /// <typeparam name="T">泛型，具体类型由调用者传入</typeparam>
     *         /// <param name="key">候选项</param>
     *         /// <param name="objMemoryItemColl">候选项集合</param>
     *         /// <returns>返回记忆剩余量</returns>
     * @param key
     * @param objMemoryitemColl
     * @return
     */

    public static double CalcRemeberValue(Object key, MemoryItemColl objMemoryitemColl){
        if(!objMemoryitemColl.contains(key)){return 0;}
        MemoryItemMDL mdl = objMemoryitemColl.get(objMemoryitemColl.size()-1);
        double dRemeberValue = MemoryDAL.CalcRemeberValue(objMemoryitemColl.getOffsetTotalCount() - mdl.getUpdateOffsetCount(), objMemoryitemColl.getMinuteOffsetSize());
        return mdl.getValidCount() * dRemeberValue;
    }



    /** <summary>
     /// 计算邻键首项记忆剩余量
     /// </summary>
     /// <typeparam name="T">泛型，具体类别由调用者传入</typeparam>
     /// <param name="key">相邻两项的首项</param>
     /// <param name="objMemoryBondColl">邻键集合</param>
     <returns>返回记忆剩余量</returns>
     */
    public static double CalcRemeberValue(Object key, MemoryBondColl objMemoryBondColl){
        if(!objMemoryBondColl.contains(key)){return 0;}
        MemoryBondMDL objBondMDL = objMemoryBondColl.get(objMemoryBondColl.indexOf(key));
        MemoryItemMDL mdl = objBondMDL.getKeyItem();
        double dRemeberValue = MemoryDAL.CalcRemeberValue(objMemoryBondColl.getOffsetTotalCount() - mdl.getUpdateOffsetCount() , objMemoryBondColl.getMinuteOffsetSize());
        return mdl.getValidCount() * dRemeberValue;
    }

    public static double CalcRemeberValue(Object keyHead, Object keyTail, MemoryBondColl objmemoryBondColl){
        if(!objmemoryBondColl.contains(keyHead)){return 0;}
        MemoryBondMDL objBondMDL = objmemoryBondColl.get(objmemoryBondColl.size()-1);
        MemoryItemColl objLinkColl = objBondMDL.getLinkColl();
        return CalcRemeberValue(keyTail, objLinkColl);
    }

    /**
    /// <summary>
    /// 计算当前关键词的成熟度
    /// </summary>
    /// <typeparam name="T">泛型，具体类别由调用者传入</typeparam>
    /// <param name="mdl">待计算的对象</param>
    /// <param name="dRemeberValue">记忆剩余量系数</param>
    /// <returns>当前成熟度</returns>
    /// <remarks>
    /// 1、成熟度这里用对象遗忘与增加的量的残差累和来表征；
    /// 2、已经累计的残差之和会随时间衰减；
    /// 3、公式的意思是： 成熟度 = 成熟度衰减剩余量 + 本次遗忘与增加量的残差的绝对值
    /// </remarks>
     */
    public static double CalcValidDegree(MemoryItemMDL mdl, double dRemeberValue)
    {
        return mdl.getValidDegree() * dRemeberValue + Math.abs(1 - mdl.getValidCount() * (1 - dRemeberValue));
    }

}
