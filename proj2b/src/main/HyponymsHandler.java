package main;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HyponymsHandler extends NgordnetQueryHandler{
    /*private class Node{
        private int id;
        private String word;
        public Node(int id , String word){
            this.id = id;
            this.word=word;
        }
    }*/
    private class Graphs{
        private Map<Integer,ArrayList<Integer>> array;
        //private Map<Integer,String> nodes;
       public Graphs(int id , String word){
              array.put(id, new ArrayList<>());
        }
        public ArrayList<Integer> getEdges(int v){
              return array.get(v);
        }
        public void addEdge(int v ,int w){//创建从节点1到节点2的单向边
              if(!array.containsKey(v)){
                  array.put(v,new ArrayList<>());
                  array.get(v).addLast(w);
              }
              else{
                  array.get(v).addLast(w);
              }
        }
        public ArrayList<Integer> ReachableNodes(int v){
           ArrayList<Integer> res = new ArrayList<>();
           res.add(v);

        }
        public class DepthFirstPaths{
           private boolean[] marked;
           private int[] edgeTo;
           private int s;

           public DepthFirstPaths(Graphs p,int s){

           }
           private void dfs(Graphs p,int v){
               marked[s] = true;
               for(int w : p.getEdges(v)){
                   if(!marked[w]){
                       edgeTo[w]=v;
                       dfs(p,w);
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
