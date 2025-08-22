package WordNet;

import edu.princeton.cs.algs4.In;

import java.util.*;
import WordNet.Graph;
public class Wordnet {
    public Graph my_map;

    public Wordnet(String File1, String File2) {
        my_map = new Graph();
        In in1 = new In(File1);
        In in2 = new In(File2);
        while (in1.hasNextLine()) {
            String line = in1.readLine();
            String[] columns = line.split(",");
            int id = Integer.parseInt(columns[0]);
            String word = columns[1];
            Graph.IntStringPair pair = new Graph.IntStringPair(id, word);
            my_map.insert(pair);//这里现在有n组键值对,但对应的桶都为空
        }
        while (in2.hasNextLine()) {
            String line = in2.readLine();
            String[] columns = line.split(",");
            int id1 = Integer.parseInt(columns[0]);
            for (Graph.IntStringPair map : my_map.sideMap.keySet()) {
                if (map.getIntKey().equals(id1)) {
                    int cnt = 1;
                    while (cnt < columns.length) {
                        for (Graph.IntStringPair possible_map : my_map.sideMap.keySet()) {
                            int tempid = Integer.parseInt(columns[cnt]);
                            if (possible_map.getIntKey().equals(tempid)) {
                                my_map.insert(map, possible_map);
                                cnt++;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public TreeSet<String> dfs(Graph map, ArrayList<Graph.IntStringPair> starts) {
        Set<Graph.IntStringPair> marked = new HashSet<>();
        Deque<Graph.IntStringPair> fringe = new ArrayDeque<>();
        TreeSet<String> res = new TreeSet<>();
        for (Graph.IntStringPair start : starts) {
            fringe.push(start);
            while (!fringe.isEmpty()) {
                Graph.IntStringPair vertex = fringe.pop();
                if (!marked.contains(vertex)) {
                    marked.add(vertex);
                    String[] columns = vertex.getStrValue().split(" ");
                    Collections.addAll(res, columns);
                }
                ArrayList<Graph.IntStringPair> neighbors = map.find(vertex);
                if (neighbors != null) {
                    for (int i = neighbors.size() - 1; i >= 0; i--) {
                        Graph.IntStringPair neighbor = neighbors.get(i);
                        if (!marked.contains((neighbor))) {
                            fringe.add(neighbor);
                        }
                    }
                }
            }
        }

        return res;
    }

    public TreeSet<String> hyponyms(String word) {
        ArrayList<Graph.IntStringPair> union = new ArrayList<>();
        for (Graph.IntStringPair pair : my_map.sideMap.keySet()) {
            String[] columns = pair.getStrValue().split(" ");
            for (String column : columns) {
                if (column.equals(word)) {
                    union.add(pair);
                }
            }
        }
        return dfs(my_map, union);
    }
}
