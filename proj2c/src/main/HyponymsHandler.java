package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import WordNet.Graph;
import WordNet.Wordnet;
import browser.NgordnetQueryType;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.*;

public  class HyponymsHandler extends NgordnetQueryHandler {
    public Wordnet net;
    public NGramMap map;
    private class DoubleStringPair{
        public Double key;
        public String value;
         public DoubleStringPair(Double key,String value){
             this.key = key;
             this.value = value;
         }
        public static Comparator<DoubleStringPair> my_comparator = (p1, p2) -> {
            // 核心逻辑：用p2的double值减去p1的，实现从大到小
            return Double.compare(p2.key, p1.key);
        };
    }
    public ArrayList<DoubleStringPair> frequency;
    public List<String> values(){
        List<String> res = new ArrayList<>();
        for(DoubleStringPair pair : frequency){
            res.add(pair.value);
        }
        return res;
    }
    public HyponymsHandler(Wordnet wn,NGramMap np) {
        net = wn;
        map = np;
        frequency = new ArrayList<>();
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> queryWords = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int num = q.k();
        NgordnetQueryType type = q.ngordnetQueryType();
        if (type.equals(NgordnetQueryType.HYPONYMS)) {
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
            for (String word : intersection) {
                TimeSeries temp_res = map.countHistory(word, startYear, endYear);
                double temp = 0.0;
                for (double value : temp_res.values()) {
                    temp += value;
                }
                if (temp != 0.0) {
                    frequency.add(new DoubleStringPair(temp, word));
                }

            }
            frequency.sort(DoubleStringPair.my_comparator);
            Set<String> res = new TreeSet<>();
            int cnt = 0;
            for (String word : values()) {
                if (cnt < num) {
                    res.add(word);
                }
                cnt++;
            }
            // 3. 直接拼接有序结果（TreeSet的toString()是有序的，但推荐用join更整洁）
            return res.toString();
        }
        else{

        }
    }
}
