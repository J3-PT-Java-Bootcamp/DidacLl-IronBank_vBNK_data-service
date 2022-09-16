package com.ironhack.vbnk_dataservice.utils;

import java.util.ArrayList;
import java.util.List;

public class VBNKConfig {
    public static final String VBNK_ENTITY_CODE="1312";
    public static final String VBNK_INT_ENTITY_CODE="ES51";


    public static List<Character> getNumbersFromId(String userId){
        var chrLst= userId.trim().toCharArray();
        var retVal= new ArrayList<Character>();
        for(char ch : chrLst)if(Character.isDigit(ch))retVal.add(ch);
        return retVal;
    }

}
