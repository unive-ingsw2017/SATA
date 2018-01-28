package com.example.tommik.unitax;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Alessandro on 28/01/2018.
 */

public class AnnoFreq {
    private HashMap<String,ArrayList<String>> list;
    public  void annoFreq(){
        list=new HashMap<String, ArrayList<String>>();
        ArrayList<String> anno=new ArrayList<String>();
        anno.add("1");
        anno.add("2");
        anno.add("3");
        anno.add("oltre 4");
        list.put("Laurea",anno);
        anno.add("1");
        anno.add("2");
        anno.add("3");
        anno.add("4");
        anno.add("5");
        anno.add("6");
        list.put("Laurea - Studente Part-Time",anno);
        anno.add("1");
        anno.add("2");
        anno.add("3");
        anno.add("oltre 4");
        list.put("Laurea magistrale / specialistica",anno);
        anno.add("1");
        anno.add("2");
        anno.add("3");
        anno.add("4");
        anno.add("5");
        anno.add("6");
        list.put("Laurea magistrale / specialistica - Studente Part-Time",anno);
    }

    public Collection<String> getTipo(){
        return list.keySet();
    }

    public Collection<String> getAnno(String c){
        return list.get(c);
    }
}
