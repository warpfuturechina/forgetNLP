package com.warpfuture.dictionary;

import java.util.ArrayList;

public class DictionaryBLL {


    /// <summary>
    /// 从文本中生成候选词
    /// </summary>
    /// <param name="text">文本行</param>
    /// <param name="objCharBondColl">相邻字典</param>
    /// <param name="objKeyWordColl">词库</param>
    /// <param name="bUpdateCharBondColl">是否更新相邻字典</param>
    /// <param name="bUpdateKeyWordColl">是否更新词库</param>

    public static void UpdateKeyWordColl(String text, ArrayList<String> objCharBondColl, ArrayList<String> objKeyWordColl, boolean bUpdateCharBondColl, boolean bUpdateKeyWordColl){
        if (text ==null || text.isEmpty()) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        // 存储连续字符串
        String keyHead = String.valueOf(text.charAt(0));
        // keyHead、keyTail分别存放相邻的两个字符
        stringBuilder.append(keyHead);
        for(int k = 1; k < text.length(); k++){

            //从句子中取出一个字作为相邻两个字的尾字
            String keyTail = String.valueOf(text.charAt(k));

            if(bUpdateCharBondColl){
                //更新相邻字典
                DictionaryDAL.UpdateMemoryBondColl(keyHead, keyTail, objCharBondColl);
            }
            if(bUpdateKeyWordColl){
                //判断相邻两字是否有关
                if(!DictionaryDAL.IsBondValid(keyHead, keyTail, objCharBondColl)){
                    //两字无关，把字串取出，为候选词
                    String keyword = stringBuilder.toString();
                    //将候选词添加到词库
                    DictionaryDAL.UpdateMemoryItemColl(keyword, objKeyWordColl);
                    //清空缓存
                    stringBuilder.setLength(0);
                    stringBuilder.append(keyTail);
                }else{
                    //两字相关加到缓冲
                    stringBuilder.append(keyTail);
                }
            }

            //当前字为相邻首字
            keyHead = keyTail;

        }
    }

    /// <summary>
    /// 相邻字统计
    /// </summary>
    /// <param name="text">文本行</param>
    /// <param name="objCharBondColl">存放相邻结果的字典</param>
    /// <remarks>遍历句中相邻的字，将结果存放到字典中</remarks>

    public static void UpdateCharBondColl(String text, ArrayList<String> objCharBondColl){
        if (text ==null || text.isEmpty()) {
            return;
        }
        String keyHead = String.valueOf(text.charAt(0));

        for(int k = 1; k < text.length(); k++){
            String keyTail = String.valueOf(text.charAt(k));
            //更新相邻字典
            DictionaryDAL.UpdateMemoryBondColl(keyHead, keyTail, objCharBondColl);
            keyHead = keyTail;
        }

    }


    /// <summary>
    /// 按权重排序输出词库
    /// </summary>
    /// <param name="objMemoryItemColl">词库</param>
    /// <param name="nKeyWordTopCount">输出词的数量</param>
    /// <param name="bOrderbyDesc">是否倒序</param>
    /// <param name="bIsOnlyWord">是否仅输出词</param>
    /// <returns>输出的结果</returns>

//    public static void ShowKeyWordWeightColl(ArrayList<String> objMemoryItemColl, int nKeyWordTopCount, boolean bOrderbyDesc, boolean bIsOnlyWord){
//
//    }

}
