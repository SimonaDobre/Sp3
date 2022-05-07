package com.simona.sp3;

import java.util.ArrayList;

public class GenerateWordWithSpaces {

    private ArrayList<Cell> wordWithSpaces;

    public GenerateWordWithSpaces(int dimm){
        wordWithSpaces = new ArrayList<>();
        for (int i = 0; i< dimm; i++){
            wordWithSpaces.add(new Cell(" _ "));
        }
    }

    public Cell cellAtIndex(int x){
        return wordWithSpaces.get(x);
    }

    public ArrayList<Cell> getSirCuvantCuSpatii() {
        return wordWithSpaces;
    }


}
