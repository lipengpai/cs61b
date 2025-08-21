package main;
import WordNet.Graph;
import WordNet.Wordnet;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HyponymsHandler extends NgordnetQueryHandler {
    public Wordnet net;
   public HyponymsHandler(Wordnet wn) {
       net = wn;
   }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String handle(NgordnetQuery q) {
        /*StringBuilder res = new StringBuilder();
        for(String word :q.words()) {
            if (!res.isEmpty()) {
                String temp = net.hyponyms(word).toString();
                String[] columns = temp.split(",");
                for(String ele : columns){
                    StringBuilder new_res=new StringBuilder();

                }
            }
            else{
                res.append(net.hyponyms(word).toString());
            }
        }
        return res.toString();
    }*/
        List<String> queryWords = q.words();
        if (queryWords.isEmpty()) {
            return "";
        }

        // 1. 初始化交集为第一个单词的下位词集合，用TreeSet实现自动排序
        Set<String> intersection = new TreeSet<>(net.hyponyms(queryWords.get(0)));

        // 2. 依次与后续单词的下位词集合求交集（TreeSet会保持排序）
        for (int i = 1; i < queryWords.size(); i++) {
            Set<String> currentHyponyms = new TreeSet<>(net.hyponyms(queryWords.get(i)));
            intersection.retainAll(currentHyponyms); // 求交集，结果仍为有序

            if (intersection.isEmpty()) {
                break;
            }
        }

        // 3. 直接拼接有序结果（TreeSet的toString()是有序的，但推荐用join更整洁）
        return intersection.toString();
    }
    }



