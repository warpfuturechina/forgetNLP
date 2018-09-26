package com.warpfuture;

import java.util.ArrayList;

import static com.warpfuture.dictionary.DictionaryBLL.UpdateKeyWordColl;
import static com.warpfuture.utils.readTextFile.readTxtFile;

public class Main {

    public static void main(String[] args) {
        //相邻字典
        ArrayList<String> objCharBondColl = new ArrayList<>();
        //词库
        ArrayList<String> objKeyWordColl = new ArrayList<>();
        String text = readTxtFile("/Users/benny/Coding/IdeaProjects/forgetNLP/src/com/warpfuture/text.txt");
        System.out.println(text);
        UpdateKeyWordColl(text, objCharBondColl, objKeyWordColl, true,  true);

    }

}
