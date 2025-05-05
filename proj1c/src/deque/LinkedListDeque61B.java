package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    public Node sentinel;
    public static int size;

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public class Node {
        public T value;
        public Node prev;
        public Node next;

        public Node(T x) {
            value = x;
        }
    }

    public LinkedListDeque61B() {
        size = 0;
        sentinel = new Node(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }
    private class LinkedListIterator implements Iterator<T>{
        public Node point;
        public LinkedListIterator(){
            point=sentinel.next;
        }
        @Override
        public boolean hasNext() {
              if(point.next==null){
                  return false;
              }
              return true;
        }
        @Override
        public T next() {
            T value=point.value;
            point=point.next;
            return value;
        }
    }
    @Override
    public void addFirst(T x) {
        Node node = sentinel.next;
        Node newnode = new Node(x);
        sentinel.next = newnode;
        newnode.prev = sentinel;
        newnode.next = node;
        node.prev = newnode;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node newnode = new Node(x);
        Node node = sentinel.prev;
        sentinel.prev = newnode;
        newnode.next = sentinel;
        node.next = newnode;
        newnode.prev = node;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> res = new ArrayList<>();
        Node p = sentinel.next;
        while (p != sentinel) {
            res.addLast(p.value);
            p = p.next;
        }
        //return List.of();
        return res;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        else{
            Node node=sentinel.next;
            sentinel.next=node.next;
            node.next.prev=sentinel;
            size--;
            return node.value;
        }
    }
    @Override
    public T removeLast() {
        if(size==0){
            return null;
        }
        else{
            Node node=sentinel.prev;
            sentinel.prev=node.prev;
            node.prev.next=sentinel;
            size--;
            return node.value;
        }
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            int cnt = -1;
            Node other = sentinel;
            while (cnt < index) {
                cnt++;
                other = other.next;
            }
            return other.value;
        } else {
            return null;
        }
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        } else {
            return getRecursive(index, sentinel.next);
        }
    }

    private T getRecursive(int index, Node node) {
        if (index == 0) {
            return node.value;  // 找到目标节点，返回值
        }
        return getRecursive(index - 1, node.next);  // 递归到下一个节点，index递减
    }
    @Override
    public boolean equals(Object obj) {
          if(this==obj){
              return true;
          }
          if(obj==null){
              return false;
          }
        if (!(obj instanceof LinkedListDeque61B<?>)) {
            return false;
        }
        LinkedListDeque61B<?> o = (LinkedListDeque61B<?>) obj;
        if(this.size()!=o.size()){
            return false;
        }
        Iterator<T> thisIter = this.iterator();
        Iterator<?> otherIter = o.iterator();

        while (thisIter.hasNext()) {
            T thisItem = thisIter.next();
            Object otherItem = otherIter.next();

            // 检查元素是否相等 (处理null情况)
            if (thisItem == null ? otherItem != null : !thisItem.equals(otherItem)) {
                return false;
            }
        }

        return true;
    }
    @Override
    public String toString(){
        StringBuilder returnSB = new StringBuilder("[");
        for(int i=0;i<size-1;i++){
            returnSB.append(get(i).toString());
            returnSB.append(", ");
        }
        returnSB.append(get(size-1));
        returnSB.append("]");
        return returnSB.toString();
    }
}
