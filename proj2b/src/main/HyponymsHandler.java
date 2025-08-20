package main;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import edu.princeton.cs.algs4.In;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    public class IntStringPair {
        private Integer intKey;
        private String strValue;

        public IntStringPair(Integer intKey, String strValue) {
            this.intKey = intKey;
            this.strValue = strValue;
        }

        public Integer getIntKey() {
            return intKey;
        }

        public String getStrValue() {
            return strValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntStringPair that = (IntStringPair) o;
            return Objects.equals(intKey, that.intKey) && Objects.equals(strValue, that.strValue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(intKey, strValue);
        }
    }

    public class Helper {
        public HashMap<IntStringPair, ArrayList<IntStringPair>> sideMap;

        public void Helper() {
            sideMap = new HashMap<>();
        }

        public void insert(IntStringPair s1, IntStringPair s2) {
            if (sideMap.containsKey(s1)) {
                sideMap.get(s1).add(s2);
            } else {
                ArrayList<IntStringPair> newList = new ArrayList<>();
                newList.add(s2);
                sideMap.put(s1, newList);
            }
        }

        public void insert(IntStringPair s1) {
            if (sideMap.containsKey(s1)) {
                return;
            } else {
                sideMap.put(s1, new ArrayList<>());
            }
        }

    }

    private class Ngordent {
        public Helper my_map;

        public Ngordent(String File1, String File2) {
            my_map = new Helper();
            In in1 = new In(File1);
            In in2 = new In(File2);
            while (in1.hasNextLine()) {
                String line = in1.readLine();
                String[] columns = line.split(",");
                int id = Integer.parseInt(columns[0]);
                String word = columns[1];
                IntStringPair pair = new IntStringPair(id, word);
                my_map.insert(pair);//这里现在有n组键值对,但对应的桶都为空
            }
            while (in2.hasNextLine()) {
                String line = in2.readLine();
                String[] columns = line.split(",");
                int id1 = Integer.parseInt(columns[0]);
                for (IntStringPair map : my_map.sideMap.keySet()) {
                    if (map.getIntKey().equals(id1)) {
                        int cnt = 1;
                        while (cnt < columns.length) {
                            for (IntStringPair possible_map : my_map.sideMap.keySet()) {
                                int tempid = Integer.parseInt(columns[cnt]);
                                if (possible_map.getIntKey().equals(tempid)) {
                                    my_map.insert(map, possible_map);
                                    cnt++;
                                }
                            }
                        }
                    }
                }
            }

        }
    }
        @Override
        public String handle(NgordnetQuery q) {
            return "Hello!";
        }
    }


