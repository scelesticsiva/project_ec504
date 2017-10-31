package com.company;

import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

class Ngram {
    // Maps to each word to an ArrayList of Pairs that is a possible next word and number of occurrence
    private HashMap<String,ArrayList<Pair<String, Integer>>> prevWord;
    // Maps each word to number of its occurrence
    private HashMap<String,Integer> prevWordCount;
    private ArrayList<String> lineArrayList;
    Ngram(String path) {
        prevWord = new HashMap<>();
        prevWordCount = new HashMap<>();
        lineArrayList = new ArrayList<>();
        readFile(path);
        constructData();
    }

    // Storing file in lineArrayList
    private void readFile(String path){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = bufferedReader.readLine();
            while (line != null)
            {
                lineArrayList.add(line);
                line = bufferedReader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initializing fields
    private void constructData(){
        ArrayList<Pair<String, Integer>> pairArrayList;
        Pair<String, Integer> pair;
        for(int index = 0; index < lineArrayList.size(); index++){
            // eg. tokens = ["237", "a", "babysitter"]
            String[] tokens = lineArrayList.get(index).split("\\t");
            if (prevWordCount.containsKey(tokens[1])){
                prevWordCount.put(tokens[1], prevWordCount.get(tokens[1]) + Integer.valueOf(tokens[0]));
                pair = new Pair<>(tokens[2], Integer.valueOf(tokens[0]));
                prevWord.get(tokens[1]).add(pair);
            }
            else{
                prevWordCount.put(tokens[1], Integer.valueOf(tokens[0]));
                pairArrayList = new ArrayList<>();
                pair = new Pair<>(tokens[2], Integer.valueOf(tokens[0]));
                pairArrayList.add(pair);
                prevWord.put(tokens[1], pairArrayList);
            }
        }
        System.out.println(lineArrayList);
        System.out.println();
        System.out.println(prevWord);
        System.out.println();
        System.out.println(prevWordCount);
    }
}
