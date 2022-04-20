package recs.run;

import recs.split.LudiiFileCleanup;
import recs.split.SentenceSplit;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        String w = "Hallo .//-------";
        System.out.println(w = w.substring(0,w.lastIndexOf("//")));
    }
}
