import java.util.List;
import java.util.ArrayList; // 导入 ArrayList 类

public class LinkedListDeque61B<T> implements Deque61B<T> {
    public Node sentinel;
    public static int size;

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
}
