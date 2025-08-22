package WordNet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

public class Graph {
    public static class IntStringPair {
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
        Comparator<IntStringPair> myComparator = Comparator.comparing(IntStringPair::getStrValue);
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntStringPair that = (IntStringPair) o;
            return Objects.equals(intKey, that.getIntKey()) && Objects.equals(strValue, that.getStrValue());
        }

        @Override
        public int hashCode() {
            return Objects.hash(intKey, strValue);
        }
    }

    public HashMap<IntStringPair, ArrayList<IntStringPair>> sideMap;

    public Graph() {
        sideMap = new HashMap<>();
    }

    public void insert(IntStringPair s1,IntStringPair s2) {
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

    public ArrayList<IntStringPair> find(IntStringPair s1) {
        return sideMap.getOrDefault(s1, new ArrayList<>());
    }
}
