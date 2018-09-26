package com.warpfuture;

import com.warpfuture.entity.MemoryBondColl;
import com.warpfuture.entity.MemoryItemColl;
import com.warpfuture.entity.MemoryItemMDL;

import java.util.ArrayList;

import static com.warpfuture.dictgen.WordDictBLL.UpdateCharBondColl;
import static com.warpfuture.dictgen.WordDictBLL.UpdateKeyWordColl;
import static com.warpfuture.utils.readTextFile.readTxtFile;

public class Main {

    public static void main(String[] args) {

        //相邻字典
        MemoryBondColl memoryBondColl = new MemoryBondColl();

        //词库
        MemoryItemColl objKeyWordColl = new MemoryItemColl();

        //语料
        String text = readTxtFile("/Users/benny/Coding/IdeaProjects/forgetNLP/src/com/warpfuture/text.txt");
        System.out.println(text);

        //相邻词统计
        UpdateCharBondColl(text, memoryBondColl);

        //生成候选词
        UpdateKeyWordColl(text, memoryBondColl, objKeyWordColl, true,  true);

        for(MemoryItemMDL memoryItemMDL : objKeyWordColl){
            System.out.println(memoryItemMDL.getKey());
        }
    }

}
