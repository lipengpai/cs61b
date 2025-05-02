import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
public class ArrayDeque61B<T> implements Deque61B<T> {
    public int size;
    public int capacity;
    public T[] array;
    public int first;
    public int last;
    public ArrayDeque61B() {
          capacity=8;
          size=0;
          array=(T[]) new Object[8];
          first=0;
          last=0;
    }
    @Override
    public void addFirst(T x) {
        if (size < capacity) {
            first = Math.floorMod(first - 1, capacity);
            array[first] = x;
            size++;
        }
       else{
           int origincapacity=capacity;
           capacity*=2;
           T[] newarray=(T[]) new Object[capacity];
           for(int i=0;i<size;i++){
               newarray[i]=array[(i+first)%origincapacity];
           }
            first=0;
            last=size;
            first = Math.floorMod(first - 1, capacity);
            newarray[first] = x;
            array=newarray;
            size++;
        }
    }
    @Override
    public void addLast(T x) {
         if(size<capacity){
             array[last]=x;
             last=Math.floorMod(last+1,capacity);
             size++;
         }
         else{
             int origincapacity=capacity;
             capacity*=2;
             T[] newarray=(T[]) new Object[capacity];
             for(int i=0;i<size;i++){
                 newarray[i]=array[(i+first)%origincapacity];
             }
             newarray[size] = x;
             first = 0;
             last = size + 1;
             array = newarray;
             size++;
         }
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i=0;i<size;i++){
            T value= get(i);
            returnList.addLast(value);
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if(first==last){
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

        // 如果需要缩小容量
        if (capacity > 15 && 4 * size <= capacity) {
            int origincapacity=capacity;
            capacity /= 2;
            T[] newarray = (T[]) new Object[capacity];

            // 复制元素
            for (int i = 0; i < size; i++) {
                newarray[i] = array[(first + i) % origincapacity];
            }

            // 更新数组和索引
            array = newarray;
            first = 0; // 重置 first 为 0，因为新数组从头开始
        }

        // 移除第一个元素
        T value = array[first];
        array[first] = null; // 清空移除的元素

        // 更新 first 和 size
        first = (first + 1) % capacity; // 更新 first 索引
        size--; // 更新队列大小

        return value;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (capacity > 15 && 4 * size <= capacity) {
            int origincapacity=capacity;
            capacity /= 2;
            T[] newarray = (T[]) new Object[capacity];

            // 复制元素
            for (int i = 0; i < size; i++) {
                newarray[i] = array[(first + i) % origincapacity];
            }

            // 更新数组和索引
            array = newarray;
            last=size;
        }
            last = Math.floorMod(last - 1, capacity);
            T value = array[last];
            array[last] = null;
            size--;
            return value;
    }
    @Override
    public T get(int index) {
        if(index<0 || index>=size){
            return null;
        }
        else{
            return array[Math.floorMod(index+first,capacity)];
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
