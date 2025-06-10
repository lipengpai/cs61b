package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
           if(!containsKey(key)){
               int index=Math.floorMod(key.hashCode(),initialCapacity);
               buckets[index].add(new Node(key,value));
               size++;
               if((double) size /initialCapacity>loadFactor){
                   //Collection<Node>[] newBuckets= new Collection[initialCapacity*2];
                   int newCapacity=2*initialCapacity;
                   Collection<Node>[] oldBuckets = this.buckets;
                   this.buckets = new Collection[newCapacity];
                   for(int i=0;i<newCapacity;i++){
                       buckets[i]=createBucket();
                   }
                   for(int i=0;i<initialCapacity;i++){
                       for(Node node: oldBuckets[i]){
                           K Key= node.key;
                           int newIndex=Math.floorMod(Key.hashCode(),newCapacity);
                           this.buckets[newIndex].add(new Node(Key,node.value));
                       }
                   }
                   initialCapacity=newCapacity;
               }
           }
           else{
               for(int i=0;i<initialCapacity;i++){
                   for(Node node : buckets[i]){//这里的运行时间为常数
                       if(node.key.equals(key)){
                           node.value=value;
                       }
                   }
               }
           }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if(!containsKey(key)){
            return null;
        }
        else{
            /*for(int i=0;i<initialCapacity;i++){
                if(!buckets[i].isEmpty()) {
                    for (Node node : buckets[i]) {
                        if (node.key.equals(key)) {
                            return node.value;
                        }
                    }
                }
            }
            return null;*/
            int index = Math.floorMod(key.hashCode(),initialCapacity);
            if(!buckets[index].isEmpty()){
                for(Node node : buckets[index]){
                    if(node.key.equals(key)){
                        return node.value;
                    }
                }
            }
            return null;
        }
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if(size==0){
            return false;
        }
        else{
            /*for(int i=0;i<initialCapacity;i++){
                if(!buckets[i].isEmpty()) {
                    for (Node node : buckets[i]) {
                        if (node.key.equals(key)) {
                            return true;
                        }
                    }
                }
            }
            return false;*/
            int index =Math.floorMod(key.hashCode(),initialCapacity);
            if(!buckets[index].isEmpty()){
                for(Node node : buckets[index]){
                    if(node.key.equals(key)){
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
          size=0;
          buckets=null;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        //return Set.of();
        throw new UnsupportedOperationException("I haven't Finished it!");
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        //return null;
        throw new UnsupportedOperationException("I haven't Finished it!");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        //return null;
        throw new UnsupportedOperationException("I haven't Finished it!");
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private double loadFactor;
    private int initialCapacity;
    private int size;
    private static final double MAX_LOAD_FACTOR = 0.75;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        size=0;
        loadFactor=MAX_LOAD_FACTOR;
        initialCapacity=16;
        buckets= new Collection[initialCapacity];
        for(int i=0;i<initialCapacity;i++){
            buckets[i]=createBucket();
        }
    }

    public MyHashMap(int initialCapacity) {
        loadFactor=MAX_LOAD_FACTOR;
        this.initialCapacity=initialCapacity;
        buckets=new Collection[initialCapacity];
        for(int i=0;i<initialCapacity;i++){
            buckets[i]=createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        this.loadFactor=loadFactor;
        this.initialCapacity=initialCapacity;
        buckets=new Collection[initialCapacity];
        for(int i=0;i<initialCapacity;i++){
            buckets[i]=createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

}
