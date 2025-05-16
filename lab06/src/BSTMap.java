import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V extends Comparable<V>> implements Map61B<K,V>{
    private class BSTNode{
        private K key;
        private V value;
        private BSTNode left, right;
        public BSTNode(K key,V value){
            this.key=key;
            this.value=value;
            this.left = null;
            this.right = null;
        }
    }
    private BSTNode root;
    private int size;
    public BSTMap(){
        root=null;
        size=0;
    }
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
            root = HelpPutFunc(root, key, value);
    }
    private BSTNode HelpPutFunc(BSTNode p,K key,V value){
        if (p == null) {
            size++;
            return new BSTNode(key, value);
        }
        else if(p.key.equals(key)){
            p.value=value;
        }
        else if (p.key.compareTo(key) < 0) {
            p.left=HelpPutFunc(p.left, key, value);
        }
        else{
            p.right= HelpPutFunc(p.right, key, value);
        }
            return p;
    }
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (!containsKey(key) || root==null) {
            return null;
        }
        else{
            BSTNode node=HelpGetFunc(root,key);
            return node.value;
        }
    }
    private BSTNode HelpGetFunc(BSTNode p,K key){
           if(p==null){
               return null;
           }
           if(p.key.equals(key)){
               return p;
           }
           if(p.key.compareTo(key)<0){
               return HelpGetFunc(p.left,key);
           }
               return HelpGetFunc(p.right,key);
    }
    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return HelpContainFunc(root,key);
    }
    private boolean HelpContainFunc(BSTNode p,K key){
        if(p==null){
            return false;
        }
        if(p.key.equals(key)){
            return true;
        }
        if(p.key.compareTo(key)<0){
            return HelpContainFunc(p.left,key);
        }
        else if(p.key.compareTo(key)>0){
            return HelpContainFunc(p.right,key);
        }
        return false;
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
           root=null;
    }

    public void printInOrder(BSTNode root){
           if(root==null){
               return;
           }
           printInOrder(root.left);
           System.out.println(root.value);
           printInOrder(root.right);
    }
    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        //return Set.of();
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        //return null;
        throw new UnsupportedOperationException("This operation is not supported.");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        //return null;
        throw new UnsupportedOperationException("This operation is not supported.");
    }
}
